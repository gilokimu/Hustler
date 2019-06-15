package me.gilo.side.common;

import androidx.lifecycle.LiveData;
import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.List;


public final class CompletionLiveListData<T> extends LiveData<Resource<List<T>>> implements OnCompleteListener<List<T>> {


    public CompletionLiveListData() {
        setValue(new Resource<>(Status.LOADING));
    }

    @Override
    public void onComplete(@NonNull Task<List<T>> task) {
        if (task.isSuccessful()) {
            setValue(new Resource<>(task.getResult()));
        } else {
            setValue(new Resource<>(task.getException()));
        }
    }
}
