package me.gilo.side.models;



import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Entity(tableName = "user")
public class User implements Serializable {

    @PrimaryKey
    @ColumnInfo(name = "userId")
    String id;

    String full_names;
    Date date_of_birth;

    Date last_sign_in;
    Date last_opened_chats;


    int age;
    String gender;

    String country;
    String zipcode;
    String city;

    String location;

    ArrayList<String> connection_type;
    ArrayList<String> connection_with;

    Boolean only_straight_gender = false;

    int age_filter_floor = 22;
    int age_filter_ceil = 67;

    @Ignore
    ArrayList<Photo> photos;

    @Embedded
    Photo profile_pic;

    String introduction;

    String email;

    String token;

    String details_ethnicity = "";
    String details_diet = "";
    String details_smoking = "";
    String details_drinking = "";
    String details_drugs = "";
    String details_religion = "";
    String details_religious_seriousness = "";
    String details_sign = "";
    String details_student = "";
    String details_education = "";
    String details_offspring = "";


    String basics_gender = "";
    String basics_orientation = "";
    String basics_relationship = "";
    String basics_height = "";
    String basics_body_type = "";

    LookingFor lookingFor = new LookingFor();

    String user_details = "";
    String user_basics = "";
    String user_looking_for = "";


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFull_names() {
        return full_names;
    }

    public void setFull_names(String full_names) {
        this.full_names = full_names;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public int getAge() {

        if (getDate_of_birth() != null){
            Date dob = getDate_of_birth();

            Calendar calendar = new GregorianCalendar();
            Date now = calendar.getTime();

            return now.getYear() - dob.getYear();

        }

        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public ArrayList<String> getConnection_type() {
        return connection_type;
    }

    public void setConnection_type(ArrayList<String> connection_type) {
        this.connection_type = connection_type;
    }

    public ArrayList<String> getConnection_with() {
        return connection_with;
    }

    public void setConnection_with(ArrayList<String> connection_with) {
        this.connection_with = connection_with;
    }

    public Boolean getOnly_straight_gender() {
        return only_straight_gender;
    }

    public void setOnly_straight_gender(Boolean only_straight_gender) {
        this.only_straight_gender = only_straight_gender;
    }

    public int getAge_filter_floor() {
        return age_filter_floor;
    }

    public void setAge_filter_floor(int age_filter_floor) {
        this.age_filter_floor = age_filter_floor;
    }

    public int getAge_filter_ceil() {
        return age_filter_ceil;
    }

    public void setAge_filter_ceil(int age_filter_ceil) {
        this.age_filter_ceil = age_filter_ceil;
    }

    public ArrayList<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<Photo> photos) {
        this.photos = photos;
    }

    public Photo getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(Photo profile_pic) {
        this.profile_pic = profile_pic;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getLocation() {
        if (getCountry() != null && getCity() != null){
            return getCity() + ", " + getCountry();
        }else if (getCountry() != null){
            return getCountry();
        }else if (getCity() != null){
            return getCity();
        }


        return "";
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getDetails_ethnicity() {
        return details_ethnicity;
    }

    public void setDetails_ethnicity(String details_ethnicity) {
        this.details_ethnicity = details_ethnicity;
    }

    public String getDetails_diet() {
        return details_diet;
    }

    public void setDetails_diet(String details_diet) {
        this.details_diet = details_diet;
    }

    public String getDetails_smoking() {
        return details_smoking;
    }

    public void setDetails_smoking(String details_smoking) {
        this.details_smoking = details_smoking;
    }

    public String getDetails_drinking() {
        return details_drinking;
    }

    public void setDetails_drinking(String details_drinking) {
        this.details_drinking = details_drinking;
    }

    public String getDetails_drugs() {
        return details_drugs;
    }

    public void setDetails_drugs(String details_drugs) {
        this.details_drugs = details_drugs;
    }

    public String getDetails_religion() {
        return details_religion;
    }

    public void setDetails_religion(String details_religion) {
        this.details_religion = details_religion;
    }

    public String getDetails_religious_seriousness() {
        return details_religious_seriousness;
    }

    public void setDetails_religious_seriousness(String details_religious_seriousness) {
        this.details_religious_seriousness = details_religious_seriousness;
    }

    public String getDetails_sign() {
        return details_sign;
    }

    public void setDetails_sign(String details_sign) {
        this.details_sign = details_sign;
    }

    public String getDetails_student() {
        return details_student;
    }

    public void setDetails_student(String details_student) {
        this.details_student = details_student;
    }

    public String getDetails_education() {
        return details_education;
    }

    public void setDetails_education(String details_education) {
        this.details_education = details_education;
    }

    public String getDetails_offspring() {
        return details_offspring;
    }

    public void setDetails_offspring(String details_offspring) {
        this.details_offspring = details_offspring;
    }

    public String getUser_details() {
        String user_details = "";
        if (!getDetails_ethnicity().isEmpty()){
            user_details += getDetails_ethnicity();
        }

        if (!getDetails_diet().isEmpty()){
            user_details += ", " + getDetails_diet();
        }

        if (!getDetails_smoking().isEmpty()){
            user_details += ", " + getDetails_smoking();
        }

        if (!getDetails_drinking().isEmpty()){
            user_details += ", " + getDetails_drinking();
        }

        if (!getDetails_drugs().isEmpty()){
            user_details += ", " + getDetails_drugs();
        }

        if (!getDetails_religion().isEmpty()){
            user_details += ", " + getDetails_religion();
        }

        if (!getDetails_religious_seriousness().isEmpty()){
            user_details += ", " + getDetails_religious_seriousness();
        }

        if (!getDetails_sign().isEmpty()){
            user_details += ", " + getDetails_sign();
        }

        if (!getDetails_student().isEmpty()){
            user_details += ", " + getDetails_student();
        }

        if (!getDetails_education().isEmpty()){
            user_details += ", " + getDetails_education();
        }

        if (!getDetails_offspring().isEmpty()){
            user_details += ", " + getDetails_offspring();
        }

        return user_details;
    }

    public String getBasics_gender() {
        return basics_gender;
    }

    public void setBasics_gender(String basics_gender) {
        this.basics_gender = basics_gender;
    }

    public String getBasics_orientation() {
        return basics_orientation;
    }

    public void setBasics_orientation(String basics_orientation) {
        this.basics_orientation = basics_orientation;
    }

    public String getBasics_relationship() {
        return basics_relationship;
    }

    public void setBasics_relationship(String basics_relationship) {
        this.basics_relationship = basics_relationship;
    }

    public String getBasics_height() {
        return basics_height;
    }

    public void setBasics_height(String basics_height) {
        this.basics_height = basics_height;
    }

    public String getBasics_body_type() {
        return basics_body_type;
    }

    public void setBasics_body_type(String basics_body_type) {
        this.basics_body_type = basics_body_type;
    }

    public String getUser_basics() {
        String user_basics = "";
        if (!getBasics_gender().isEmpty()){
            user_basics += getBasics_gender();
        }

        if (!getBasics_orientation().isEmpty()){
            user_basics += ", " + getBasics_orientation();
        }

        if (!getBasics_relationship().isEmpty()){
            user_basics += ", " + getBasics_relationship();
        }

        if (!getBasics_height().isEmpty()){
            user_basics += ", " + getBasics_height();
        }

        if (!getBasics_body_type().isEmpty()){
            user_basics += ", " + getBasics_body_type();
        }


        return user_basics;
    }

    public LookingFor getLookingFor() {
        return lookingFor;
    }

    public void setLookingFor(LookingFor lookingFor) {
        this.lookingFor = lookingFor;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
