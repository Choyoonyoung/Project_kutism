package com.example.yy.loading_1;

public class FoodItem {
    String gender;
    String place;
    String time;
    String set_time;
    int resId;
    public FoodItem(String gender, String place, String set_time,String time,int resId) {
        this.gender = gender;
        this.place = place;
        this.time = time;
        this.set_time=set_time;
        this.resId = resId;
    }

    public void setSet_time(String set_time) {
        this.set_time = set_time;
    }
    public String getSet_time() {
        return set_time;
    }
    public int getResId() {
        return resId;
    }
    public String getGender() {
        return gender;
    }

    public String getPlace() {
        return place;
    }

    public String getTime() {
        return time;
    }
    public void setResId(int resId) {
        this.resId = resId;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setTime(String time) {
        this.time = time;
    }
}


