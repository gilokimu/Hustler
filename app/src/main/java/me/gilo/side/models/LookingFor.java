package me.gilo.side.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "looking_for")
public class LookingFor extends Model implements Serializable {

    static boolean open_to_hookup = true;
    static boolean new_friends = true;
    static boolean short_term_dating = true;
    static boolean long_term_dating = true;

    static boolean open_to_non_monogamy = false;

    static String gender = "anyone";
    static boolean showStraightsOnly = false;

    static int min_age = 22;
    static int max_age = 60;

    static String country = "in any country";
    static String city = "any city";

    static String distance = "within the city";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isOpen_to_hookup() {
        return open_to_hookup;
    }

    public void setOpen_to_hookup(boolean open_to_hookup) {
        this.open_to_hookup = open_to_hookup;
    }

    public boolean isNew_friends() {
        return new_friends;
    }

    public void setNew_friends(boolean new_friends) {
        this.new_friends = new_friends;
    }

    public boolean isShort_term_dating() {
        return short_term_dating;
    }

    public void setShort_term_dating(boolean short_term_dating) {
        this.short_term_dating = short_term_dating;
    }

    public boolean isLong_term_dating() {
        return long_term_dating;
    }

    public void setLong_term_dating(boolean long_term_dating) {
        this.long_term_dating = long_term_dating;
    }

    public boolean isOpen_to_non_monogamy() {
        return open_to_non_monogamy;
    }

    public void setOpen_to_non_monogamy(boolean open_to_non_monogamy) {
        this.open_to_non_monogamy = open_to_non_monogamy;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean isShowStraightsOnly() {
        return showStraightsOnly;
    }

    public void setShowStraightsOnly(boolean showStraightsOnly) {
        this.showStraightsOnly = showStraightsOnly;
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

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        LookingFor.distance = distance;
    }

    public String lookingForString(){

        String looking_for = "Looking for " + gender.toLowerCase();
        looking_for += ", " + distance;
        looking_for += " in " + city + " " + country;
        looking_for += ", ages " + min_age + "-" + max_age;

        String purpose = "";

        if (isOpen_to_hookup()){
            purpose += "hookup";
        }

        if (new_friends){
            purpose += ", new friends";
        }
        if (short_term_dating){
            purpose += ", short term dating";
        }
        if (long_term_dating){
            purpose += ", long term dating";
        }

        if (!purpose.isEmpty()){
            if (purpose.charAt(0) == ','){
                purpose = purpose.substring(2);
            }

            if ((purpose.length() - purpose.replace(",", "").length() > 1)){
                purpose = replaceLast(purpose, ",", " and");
            }

        }

        looking_for += ", for " + purpose  + ".";


        return looking_for;
    }

    public static String replaceLast(String text, String regex, String replacement) {
        return text.replaceFirst("(?s)"+regex+"(?!.*?"+regex+")", replacement);
    }
}
