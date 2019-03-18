package me.gilo.side.repo;

import android.graphics.Bitmap;
import android.net.Uri;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.Query;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;

import me.gilo.side.common.CompletionDocLiveData;
import me.gilo.side.common.CompletionLiveData;
import me.gilo.side.common.DocumentLiveData;
import me.gilo.side.common.PhotoQueryLiveData;
import me.gilo.side.models.Photo;
import me.gilo.side.models.User;

public class UserRepository extends FirestoreRepository{

    private final CollectionReference users;
    private final StorageReference storage;

    @Inject
    public UserRepository(@Named("users") CollectionReference users, @Named("storage") StorageReference storage) {
        super(User.class, "users");
        this.users = users;
        this.storage = storage;
    }

    public DocumentLiveData<User> user(final String id) {
        if (id == null) {
            return null;
        }
        final DocumentReference userRef = users.document(id);
        DocumentLiveData<User> data = new DocumentLiveData<>(userRef, User.class);
        userRef.addSnapshotListener(data);
        return data;
    }


    public CompletionLiveData addUser(User user, OnSuccessListener successListener, OnFailureListener failureListener) {
        final CompletionLiveData completion = new CompletionLiveData();
        users.add(user).addOnSuccessListener(successListener).addOnFailureListener(failureListener).addOnCompleteListener(completion);

        return completion;
    }

    public CompletionLiveData update(User user) {
        final CompletionLiveData completion = new CompletionLiveData();
        users.document(user.getId()).set(user).addOnCompleteListener(completion);
        return completion;
    }

    public PhotoQueryLiveData<Photo> photos(String user_id) {
        Query query = users.document(user_id).collection("photos");
        query.orderBy("date_created", Query.Direction.ASCENDING);

        return new PhotoQueryLiveData(query, Photo.class);
    }

    public PhotoQueryLiveData<Photo> post_photos(String user_id) {
        Query query = users.document(user_id).collection("post_photos");
        query.orderBy("date_created", Query.Direction.ASCENDING);

        return new PhotoQueryLiveData(query, Photo.class);
    }

    public CompletionDocLiveData addPhoto(String user_id, Photo photo) {
        final CompletionDocLiveData completion = new CompletionDocLiveData();
        users.document(user_id)
                .collection("photos")
                .add(photo)
                .addOnCompleteListener(completion);

        return completion;
    }

    public CompletionDocLiveData addPostPhoto(String user_id, Photo photo) {
        final CompletionDocLiveData completion = new CompletionDocLiveData();
        users.document(user_id)
                .collection("post_photos")
                .add(photo)
                .addOnCompleteListener(completion);

        return completion;
    }

    public CompletionLiveData deletePhoto(String user_id, Photo photo) {
        final CompletionLiveData completion = new CompletionLiveData();
        users.document(user_id)
                .collection("photos")
                .document(photo.getId())
                .delete()
                .addOnCompleteListener(completion);

        return completion;
    }

    public CompletionLiveData deletePostPhotos(String user_id, Photo photo) {
        final CompletionLiveData completion = new CompletionLiveData();
        users.document(user_id)
                .collection("post_photos")
                .document(photo.getId())
                .delete()
                .addOnCompleteListener(completion);

        return completion;
    }

    public CompletionLiveData uploadPhoto(String user_id, String file_name, Bitmap bitmap) {
        final CompletionLiveData completion = new CompletionLiveData();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] data = stream.toByteArray();
        uploadUserPhoto(user_id, data, file_name).addOnCompleteListener(completion);

        return completion;

    }

    public CompletionLiveData uploadPhotos(String user_id, ArrayList<String> filenames, ArrayList<Bitmap> bitmaps) {
        final CompletionLiveData completion = new CompletionLiveData();
        uploadUserPhotos(user_id, filenames, bitmaps).addOnCompleteListener(completion);

        return completion;
    }

    private Task<Void> uploadUserPhotos(String user_id, ArrayList<String> filenames, ArrayList<Bitmap> bitmaps) {
        return users.getFirestore().runTransaction(transaction -> {
            for (int i =0; i < bitmaps.size(); i++) {
                Bitmap bitmap = bitmaps.get(i);
                String file_name = filenames.get(i);

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] data = stream.toByteArray();

                StorageReference photoRef = storage.child("images/users/" + user_id + "/" + file_name);
                photoRef.putBytes(data).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        photoRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Photo photo = new Photo();
                                photo.setUrl(uri.toString());
                                addPhoto(user_id, photo);
                            }
                        });
                    }
                });
            }

            return null;
        });
    }


    public CompletionLiveData uploadPostPhotos(String user_id, ArrayList<String> filenames, ArrayList<Bitmap> bitmaps) {
        final CompletionLiveData completion = new CompletionLiveData();
        uploadUserPostPhotos(user_id, filenames, bitmaps).addOnCompleteListener(completion);

        return completion;
    }

    private Task<Void> uploadUserPostPhotos(String user_id, ArrayList<String> filenames, ArrayList<Bitmap> bitmaps) {
        return users.getFirestore().runTransaction(transaction -> {
            for (int i =0; i < bitmaps.size(); i++) {
                Bitmap bitmap = bitmaps.get(i);
                String file_name = filenames.get(i);

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] data = stream.toByteArray();

                StorageReference photoRef = storage.child("images/users/" + user_id + "/posts/" + file_name);
                photoRef.putBytes(data).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        photoRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Photo photo = new Photo();
                                photo.setUrl(uri.toString());
                                addPostPhoto(user_id, photo);
                            }
                        });
                    }
                });
            }

            return null;
        });
    }

    private Task<Void> uploadUserPhoto(String user_id, byte[] image, String file_name) {
        StorageReference photoRef = storage.child("images/users/" + user_id + "/" + file_name);

        return users.getFirestore().runTransaction(transaction -> {
            photoRef.putBytes(image).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    photoRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Photo photo = new Photo();
                            photo.setUrl(uri.toString());
                            addPhoto(user_id, photo);
                        }
                    });
                }
            });

            return null;
        });
    }

}
