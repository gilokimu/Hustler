package me.gilo.side.models;


import androidx.room.Entity;

import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

@Entity(tableName = "message")
public class Message extends Model{

    public String userId;
    public String userName;
    public String message;
    public @ServerTimestamp
    Date timestamp;


    public Message() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

}
