package me.gilo.side.models;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

@Entity(tableName = "post")
public class Post extends Model implements Serializable {

    public static final String FIELD_HAS_IMAGE = "has_image";
    public static final String FIELD_CATEGORY = "subcategory";
    public static final String FIELD_SUBCATEGORY = "subcategory";
    public static final String FIELD_COUNTRY = "country";
    public static final String FIELD_CITY = "city";
    public static final String FIELD_BODY_TYPE = "body_type";
    public static final String FIELD_MIN_AGE = "min_age";
    public static final String FIELD_MAX_AGE = "max_age";

    boolean hasImage;
    int age;

    String body_type;

    String name;
    String description;
    String location;
    String category;
    String subcategory;
    String country;
    String city;

    int post_id;

    boolean dummy = false;
    String status = "pending";

    @Embedded
    User created_by;

    String user_id;

    public String getId() {
        return super.id;
    }

    ArrayList<Photo> photos;

    ArrayList<String> flagged = new ArrayList<>();
    ArrayList<String> favorited = new ArrayList<>();


    transient boolean isLiked = false;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        if (getCountry() != null && getCity() != null){
            return getCity() + ", " + getCountry();
        }

        if (getCountry() != null){
            return getCountry();
        }

        if (getCity() != null){
            return getCity();
        }

        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ArrayList<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<Photo> photos) {

        if (photos != null && photos.size() > 1){
            hasImage = true;
        }else{
            hasImage = false;
        }

        this.photos = photos;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    @Override
    public User getCreated_by() {
        return created_by;
    }

    @Override
    public void setCreated_by(User created_by) {
        this.user_id = created_by.id;
        this.age = created_by.getAge();
        this.created_by = created_by;
        this.body_type = created_by.basics_body_type;
    }


    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(post_id, post.post_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    public boolean isDummy() {
        return dummy;
    }

    public void setDummy(boolean dummy) {
        this.dummy = dummy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public ArrayList<String> getFlagged() {
        return flagged;
    }

    public void setFlagged(ArrayList<String> flagged) {
        this.flagged = flagged;
    }

    public ArrayList<String> getFavorited() {
        return favorited;
    }

    public void setFavorited(ArrayList<String> favorited) {
        this.favorited = favorited;
    }
}
