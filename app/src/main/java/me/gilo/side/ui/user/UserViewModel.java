package me.gilo.side.ui.user;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.graphics.Bitmap;

import me.gilo.side.common.CompletionDocLiveData;
import me.gilo.side.common.CompletionLiveData;
import me.gilo.side.common.DocumentLiveData;
import me.gilo.side.common.Resource;
import me.gilo.side.models.Photo;
import me.gilo.side.models.User;
import me.gilo.side.repo.UserRepository;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public final class UserViewModel extends ViewModel {
    private final UserRepository repository;
    private final MutableLiveData<String> id = new MutableLiveData<>();

    @Inject
    UserViewModel(UserRepository repository) {
        this.repository = repository;
    }

    public UserRepository getRepository() {
        return repository;
    }

    public CompletionLiveData addUser(User user, OnSuccessListener successListener, OnFailureListener failureListener) {
        return repository.addUser(user, successListener, failureListener);
    }

    public DocumentLiveData<User> user(String user_id) {
        return repository.user(user_id);
    }

    public CompletionLiveData updateUser(User user) {
        return repository.update(user);
    }

    public LiveData<Resource<List<Photo>>> userPhotos(String user_id) {
        return repository.photos(user_id);
    }

    public CompletionDocLiveData addPhoto(String user_id, Photo photo) {
        return repository.addPhoto(user_id, photo);
    }

    public CompletionLiveData deletePhoto(String user_id, Photo photo) {
        return repository.deletePhoto(user_id, photo);
    }


    public CompletionLiveData uploadPhoto(String user_id, String file_name, Bitmap photo) {
        return repository.uploadPhoto(user_id, file_name, photo);
    }

    public CompletionLiveData uploadPhotos(String user_id, ArrayList<String> filenames, ArrayList<Bitmap> bitmaps) {
        return repository.uploadPhotos(user_id, filenames, bitmaps);
    }

}
