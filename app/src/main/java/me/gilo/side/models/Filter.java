package me.gilo.side.models;

import android.text.TextUtils;

import com.google.firebase.firestore.Query;

import java.io.Serializable;

public class Filter extends Model implements Serializable {



    Boolean hasImage;
    String category = "";
    String subcategory = "";
    String country = "";
    String city = "";
    String keyword = "";

    Integer min_age;
    Integer max_age;

    String body_type = "";

    public Query applyFilter(Query query){

        //query.orderBy("date_created", Query.Direction.DESCENDING);

        if (hasImage != null){
            query.whereEqualTo(Post.FIELD_HAS_IMAGE, hasImage);
            query.orderBy(Post.FIELD_HAS_IMAGE, Query.Direction.DESCENDING);
        }

        if (!TextUtils.isEmpty(subcategory)){
            query.whereEqualTo(Post.FIELD_SUBCATEGORY, subcategory);
            query.orderBy(Post.FIELD_SUBCATEGORY, Query.Direction.DESCENDING);
        }

        if (!TextUtils.isEmpty(country)){
            query.whereEqualTo(Post.FIELD_COUNTRY, country);
            query.orderBy(Post.FIELD_COUNTRY, Query.Direction.DESCENDING);
        }

        if (!TextUtils.isEmpty(city)){
            query.whereEqualTo(Post.FIELD_CITY, city);
            query.orderBy(Post.FIELD_CITY, Query.Direction.DESCENDING);
        }

        if (max_age != null){
            query.whereLessThanOrEqualTo(Post.FIELD_MAX_AGE, max_age);
            query.orderBy(Post.FIELD_MAX_AGE, Query.Direction.DESCENDING);
        }

        if (min_age != null){
            query.whereGreaterThanOrEqualTo(Post.FIELD_MIN_AGE, min_age);
            //query.orderBy(Post.FIELD_MIN_AGE, Query.Direction.DESCENDING);
        }

        query.orderBy("date_created", Query.Direction.DESCENDING);


        return query;
    }


    public boolean isHasImage() {
        return hasImage;
    }

    public void setHasImage(boolean hasImage) {
        this.hasImage = hasImage;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getMin_age() {
        return min_age;
    }

    public void setMin_age(int min_age) {
        this.min_age = min_age;
    }

    public int getMax_age() {
        return max_age;
    }

    public void setMax_age(int max_age) {
        this.max_age = max_age;
    }

    public String getBody_type() {
        return body_type;
    }

    public void setBody_type(String body_type) {
        this.body_type = body_type;
    }


}
