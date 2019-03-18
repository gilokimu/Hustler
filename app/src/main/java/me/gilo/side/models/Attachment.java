package me.gilo.side.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "attachment")
public class Attachment implements Serializable {

    @PrimaryKey
    @ColumnInfo(name = "id")
    int attachmentId;

    public enum TYPE {
        POST, PHOTO, LINK;

        TYPE() {
        }
    }

    @Embedded
    TYPE type;
    @Embedded
    Post post;
    @Embedded
    Photo photo;

    public int getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(int attachmentId) {
        this.attachmentId = attachmentId;
    }

    public TYPE getType() {
        return type;
    }

    public void setType(TYPE type) {
        this.type = type;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }
}
