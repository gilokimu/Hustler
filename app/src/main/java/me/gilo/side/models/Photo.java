package me.gilo.side.models;


import android.graphics.Bitmap;

import androidx.room.Entity;
import androidx.room.Ignore;

import com.google.firebase.storage.StorageReference;

import java.io.Serializable;

@Entity(tableName = "photo")
public class Photo extends PhotoModel implements Serializable {

    String url;

    boolean isAddPhoto = false;
    boolean isUploading = false;

    boolean profile_pic;

    @Ignore
    transient Bitmap bitmap;

    String filename;

    @Ignore
    transient StorageReference reference;

    public Photo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Ignore
    public Photo(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public boolean isUploading() {
        return isUploading;
    }

    public void setUploading(boolean uploading) {
        isUploading = uploading;
    }

    public boolean isAddPhoto() {
        return isAddPhoto;
    }

    public void setAddPhoto(boolean addPhoto) {
        isAddPhoto = addPhoto;
    }

    public StorageReference getReference() {
        return reference;
    }

    public void setReference(StorageReference reference) {
        this.reference = reference;
    }

    public boolean isProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(boolean profile_pic) {
        this.profile_pic = profile_pic;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
