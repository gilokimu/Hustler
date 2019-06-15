package me.gilo.side.models;


import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.firebase.firestore.ServerTimestamp;

import java.io.Serializable;
import java.util.Date;

@Entity(tableName = "chat_record")
public class ChatRecord extends Model implements Serializable {

    @PrimaryKey
    @ColumnInfo(name = "id")
    int chatMessageId;

    String chat_id;

    @Embedded
    User started_by;

    @Embedded
    User recipient;

    Date recipient_last_read;
    Date started_by_last_read;

    @Embedded
    ChatMessage last_message;

    @ServerTimestamp
    Date date_modified;

    boolean mine = false;

    public String getChat_id() {
        return chat_id;
    }

    public void setChat_id(String chat_id) {
        this.chat_id = chat_id;
    }

    public User getStarted_by() {
        return started_by;
    }

    public void setStarted_by(User started_by) {
        this.started_by = started_by;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public ChatMessage getLast_message() {
        return last_message;
    }

    public void setLast_message(ChatMessage last_message) {
        this.last_message = last_message;
    }

    public boolean isMine() {
        return mine;
    }

    public void setMine(boolean mine) {
        this.mine = mine;
    }

    public Date getDate_modified() {
        return date_modified;
    }

    public void setDate_modified(Date date_modified) {
        this.date_modified = date_modified;
    }

    public Date getRecipient_last_read() {
        return recipient_last_read;
    }

    public void setRecipient_last_read(Date recipient_last_read) {
        this.recipient_last_read = recipient_last_read;
    }

    public Date getStarted_by_last_read() {
        return started_by_last_read;
    }

    public void setStarted_by_last_read(Date started_by_last_read) {
        this.started_by_last_read = started_by_last_read;
    }
}
