package me.gilo.side.repo;

import android.graphics.Bitmap;
import android.net.Uri;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.Query;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

import javax.inject.Inject;
import javax.inject.Named;

import me.gilo.side.common.CompletionDocLiveData;
import me.gilo.side.common.CompletionLiveData;
import me.gilo.side.common.DocumentLiveData;
import me.gilo.side.common.QueryLiveData;
import me.gilo.side.models.Attachment;
import me.gilo.side.models.Chat;
import me.gilo.side.models.ChatMessage;
import me.gilo.side.models.ChatRecord;
import me.gilo.side.models.Photo;
import me.gilo.side.models.User;

public class ChatRepository extends FirestoreRepository{

    private final CollectionReference chats;
    private final CollectionReference users;
    private final StorageReference storage;

    @Inject
    public ChatRepository(@Named("chats") CollectionReference chats, @Named("users") CollectionReference users, @Named("storage") StorageReference storage) {
        super(Chat.class, "chats");
        this.chats = chats;
        this.users = users;
        this.storage = storage;
    }

    public CompletionDocLiveData addChatMessage(String chat_id, ChatMessage chatMessage) {
        final CompletionDocLiveData completion = new CompletionDocLiveData();
        chats.document(chat_id)
                .collection("messages")
                .add(chatMessage)
                .addOnCompleteListener(completion);

        return completion;
    }


    public CompletionLiveData addChatMessage(String user_id, String chat_id, ChatMessage chatMessage, Attachment attachment) {
        final CompletionLiveData completion = new CompletionLiveData();
        uploadPhotoAndCreateChat(user_id, chat_id, chatMessage, attachment).addOnCompleteListener(completion);

        return completion;
    }

    private Task<Void> uploadPhotoAndCreateChat(String user_id, String chat_id, ChatMessage chatMessage, Attachment attachment) {
        return chats.getFirestore().runTransaction(transaction -> {

                Bitmap bitmap = attachment.getPhoto().getBitmap();
                String file_name = attachment.getPhoto().getFilename();

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] data = stream.toByteArray();

                StorageReference photoRef = storage.child("images/users/" + user_id + "/chats/" +chat_id+"/"+ file_name);
                photoRef.putBytes(data).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        photoRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Photo photo = new Photo();
                                photo.setUrl(uri.toString());

                                Attachment attachment1 = new Attachment();
                                attachment1.setType(Attachment.TYPE.PHOTO);
                                attachment1.setPhoto(photo);

                                chatMessage.setAttachment(attachment1);

                                addChatMessage(chat_id, chatMessage);
                            }
                        });
                    }
                });

            return null;
        });
    }

    public CompletionLiveData addChatRecord(ChatRecord record, User started_by, String chat_id, User recipient) {
        final CompletionLiveData completion = new CompletionLiveData();
        createChatRecord(record, started_by, chat_id, recipient).addOnCompleteListener(completion);

        return completion;
    }

    private Task<Void> createChatRecord(ChatRecord record, User started_by, String chat_id, User recipient) {
        return chats.getFirestore().runTransaction(transaction -> {

            users.document(started_by.getId())
                    .collection("chats")
                    .document(recipient.getId())
                    .set(record);

            users.document(recipient.getId())
                    .collection("chats")
                    .document(started_by.getId())
                    .set(record);

            return null;
        });
    }

    public DocumentLiveData<ChatRecord> getChatWithUser(String current_user, String recipient) {
        final DocumentReference chatRecordRef =
                users.document(current_user)
                .collection("chats")
                .document(recipient);
        DocumentLiveData<ChatRecord> data = new DocumentLiveData<>(chatRecordRef, ChatRecord.class);
        chatRecordRef.addSnapshotListener(data);
        return data;

    }


    public CompletionLiveData createChat(Chat chat, ChatMessage message, User created_by, User recipient) {
        final CompletionLiveData completion = new CompletionLiveData();
        addChat(chat, message, created_by, recipient).addOnCompleteListener(completion);

        return completion;
    }


    public QueryLiveData<ChatMessage> messages(final String chat_id) {
        Query query = chats.document(chat_id).collection("messages").orderBy("timestamp", Query.Direction.ASCENDING);
        return new QueryLiveData<>(query, ChatMessage.class);
    }


    public QueryLiveData<ChatRecord> chats(final String user_id) {
        Query query = users.document(user_id).collection("chats");
        return new QueryLiveData<>(query, ChatRecord.class);
    }


    private Task<Void> addChat(Chat chat, ChatMessage message, User created_by, User recipient) {
        return chats.getFirestore().runTransaction(transaction -> {
            chats.add(chat)
                    .addOnSuccessListener(documentReference -> {
                        String chat_id = documentReference.getId();

                        addChatMessage(chat_id, message);

                        ChatRecord record = new ChatRecord();
                        record.setChat_id(chat_id);
                        record.setStarted_by(created_by);
                        record.setRecipient(recipient);
                        record.setLast_message(message);

                        addChatRecord(record, created_by, documentReference.getId(), recipient);
                    });

            return null;
        });
    }

}
