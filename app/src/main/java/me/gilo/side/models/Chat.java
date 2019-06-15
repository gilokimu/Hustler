package me.gilo.side.models;


import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.firebase.firestore.ServerTimestamp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

@Entity(tableName = "chat")
public class Chat extends Model implements Serializable {

    @PrimaryKey
    @ColumnInfo(name = "id")
    int chatId;

    public String userId;
    public String userName;

    @Embedded
    User recipient;
    ArrayList<User> members;
    @Embedded
    Post originating_post;
    @Embedded
    ChatMessage last_message;


    public @ServerTimestamp
    Date timestamp;

    public Chat() {
    }

    public Chat(User user) {
        setCreated_by(user);

    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
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

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public ArrayList<User> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<User> members) {
        this.members = members;
    }

    public Post getOriginating_post() {
        return originating_post;
    }

    public void setOriginating_post(Post originating_post) {
        this.originating_post = originating_post;
    }

    public ChatMessage getLast_message() {
        return last_message;
    }

    public void setLast_message(ChatMessage last_message) {
        this.last_message = last_message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
