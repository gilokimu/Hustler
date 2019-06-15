package me.gilo.side.models;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.PrimaryKey;

import com.google.firebase.firestore.IgnoreExtraProperties;

import java.io.Serializable;

/**
 * A Base Model to be extended by other models to add ids.
 */

@IgnoreExtraProperties
public class PhotoModel implements Serializable {

    @PrimaryKey
    @ColumnInfo(name = "id")
    public String id;

    @Embedded
    User created_by;

    public <T extends PhotoModel> T withId(@NonNull final String id) {
        this.id = id;
        return (T) this;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getCreated_by() {
        return created_by;
    }

    public void setCreated_by(User created_by) {
        this.created_by = created_by;
    }

}
