package me.gilo.side.models;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.PrimaryKey;

import com.google.firebase.firestore.IgnoreExtraProperties;
import com.google.firebase.firestore.ServerTimestamp;

import java.io.Serializable;
import java.util.Date;

import me.gilo.side.utils.DateUtils;

/**
 * A Base Model to be extended by other models to add ids.
 */

@IgnoreExtraProperties
public class Model implements Serializable {

    @PrimaryKey
    @ColumnInfo(name = "id")
    public String id;

    @ServerTimestamp
    private Date date_created = null;

    @Embedded
    User created_by;

    public <T extends Model> T withId(@NonNull final String id) {
        this.id = id;
        return (T) this;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate_created() {
        return date_created;
    }

    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }

    public String getWellFormatedDateCreated(){
        if (getDate_created() != null) {
            return  DateUtils.getDateString_shortAndSmart(getDate_created());
        }

        return "";
    }

    public User getCreated_by() {
        return created_by;
    }

    public void setCreated_by(User created_by) {
        this.created_by = created_by;
    }

}
