package me.gilo.side.repo;

import android.util.Log;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;

import me.gilo.side.common.CompletionDocLiveData;
import me.gilo.side.common.CompletionLiveData;
import me.gilo.side.common.QueryLiveData;
import me.gilo.side.models.Filter;
import me.gilo.side.models.Post;
import me.gilo.side.models.User;

public class PostRepository extends FirestoreRepository{

    private final FirebaseFirestore firestore;
    private final CollectionReference posts;
    private final CollectionReference users;


    @Inject
    public PostRepository(
            FirebaseFirestore firestore,
            @Named("posts") CollectionReference posts,
            @Named("users") CollectionReference users
    ) {
        super(Post.class, "posts");
        this.firestore = firestore;
        this.posts = posts;
        this.users = users;
    }


    public QueryLiveData<Post> posts() {
        return new QueryLiveData<>(toQuery(), Post.class);
    }

    public QueryLiveData<Post> getPosts() {
        Query query = posts.orderBy("date_created", Query.Direction.DESCENDING)
                .limit(10);


        return new QueryLiveData<>(query, Post.class);
    }

    public QueryLiveData<Post> getUsersPosts(String user_id, int page_size) {
        Query query = posts.whereEqualTo("user_id", user_id)
                .orderBy("date_created", Query.Direction.DESCENDING)
                .limit(page_size);

        return new QueryLiveData<>(query, Post.class);
    }

    public QueryLiveData<Post> getUsersPosts(String user_id, int page_size, Post last_post) {
        Query query = posts.whereEqualTo("user_id", user_id)
                .orderBy("date_created", Query.Direction.DESCENDING)
                .startAfter(last_post.getDate_created())
                .limit(page_size);

        return new QueryLiveData<>(query, Post.class);
    }

    public QueryLiveData<Post> getPosts(int page_size) {
        Query query = posts.orderBy("date_created", Query.Direction.DESCENDING)
                .limit(page_size);

        return new QueryLiveData<>(query, Post.class);
    }

    public QueryLiveData<Post> getPosts(int page_size, Post last_post) {
        Query query = posts.orderBy("date_created", Query.Direction.DESCENDING)
                .startAfter(last_post.getDate_created())
                .limit(page_size);

        return new QueryLiveData<>(query, Post.class);
    }

    public QueryLiveData<Post> getPosts(Filter filter, int page_size) {
        Query query = filter.applyFilter(posts);
        query.orderBy("date_created", Query.Direction.DESCENDING)
                .limit(page_size);

        return new QueryLiveData<>(query, Post.class);
    }

    public QueryLiveData<Post> getPosts(Filter filter, int page_size, Post last_post) {
        Query query = filter.applyFilter(posts);
        query.orderBy("date_created", Query.Direction.DESCENDING)
                .startAfter(last_post.getDate_created())
                .limit(page_size);

        return new QueryLiveData<>(query, Post.class);
    }

    public QueryLiveData<Post> favorites(String user_id) {
        Query query = users
                .document(user_id)
                .collection("favorites")
                .orderBy("date_created", Query.Direction.DESCENDING);
        return new QueryLiveData<>(query, Post.class);
    }

    public CompletionDocLiveData addToFavorite(String user_id, Post post) {
        final CompletionDocLiveData completion = new CompletionDocLiveData();
        users.document(user_id)
                .collection("favorites")
                .add(post)
                .addOnCompleteListener(completion);

        ArrayList<String> favorites = post.getFavorited();
        favorites.add(user_id);
        post.setFavorited(favorites);

        posts.document(post.id).set(post);

        return completion;
    }

    public CompletionDocLiveData flagPost(String user_id, Post post) {
        final CompletionDocLiveData completion = new CompletionDocLiveData();
        users.document(user_id)
                .collection("flagged")
                .add(post)
                .addOnCompleteListener(completion);

        ArrayList<String> flagged = post.getFlagged();
        flagged.add(user_id);
        post.setFlagged(flagged);

        posts.document(post.id).set(post);

        return completion;
    }

    public CompletionDocLiveData post(Post post) {
        final CompletionDocLiveData completion = new CompletionDocLiveData();
        posts.add(post).addOnCompleteListener(completion);

        return completion;
    }

    public CompletionLiveData claimPost(User user, Post post) {
        final CompletionLiveData completion = new CompletionLiveData();
        posts.document(post.id).update("user", user).addOnCompleteListener(completion);

        return completion;
    }

    public CompletionLiveData updatePost(Post post) {
        final CompletionLiveData completion = new CompletionLiveData();
        posts.document(post.id).set(post).addOnCompleteListener(completion);

        return completion;
    }

    public CompletionLiveData removeFromFavorite(String user_id, String post_id) {
        final CompletionLiveData completion = new CompletionLiveData();
        users.document(user_id)
                .collection("favorites")
                .document(post_id)
                .delete()
                .addOnCompleteListener(completion);

        return completion;
    }

    public CompletionLiveData batchUploadDummy(ArrayList<Post> posts) {
        final CompletionLiveData completion = new CompletionLiveData();
        uploadDummyPosts(posts).addOnCompleteListener(completion);

        return completion;
    }

    private Query toQuery() {
        Query query = firestore.collection("posts");
        query.orderBy("date_created", Query.Direction.DESCENDING).limit(50);

        return query;
    }

    public Query getLikesQuery(String user_id) {
        return users.document(user_id).collection("favorites").orderBy("date_created", Query.Direction.DESCENDING);
    }

    public Query getPostsQuery() {
        Query query = firestore.collection("posts");
        query.orderBy("date_created", Query.Direction.DESCENDING).limit(20);

        return query;
    }

    int done = 0;

    private Task<Void> uploadDummyPosts(ArrayList<Post> ps) {
        return posts.getFirestore().runTransaction(transaction -> {
            int total = ps.size();

            for (int i =0; i < ps.size(); i++) {
                Post post = ps.get(i);
                posts.add(post).addOnCompleteListener(task -> {
                    Log.d("dummy upload", "" + (done++) + "/" + total);
                });
            }

            return null;
        });
    }
}
