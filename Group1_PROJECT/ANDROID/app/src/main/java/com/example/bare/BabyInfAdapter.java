package com.example.bare;

public class BabyInfAdapter {
    public BabyInfAdapter(){

    }
    private String Baby_Name,Birthday,Blood_Type,Gender,Weight,Height,Birth_Place;
    public BabyInfAdapter(String Baby_Name, String Birthday, String Blood_Type, String Gender, String Weight, String Height, String Birth_Place) {
        this.Baby_Name = Baby_Name;
        this.Birthday = Birthday;
        this.Blood_Type = Blood_Type;
        this.Gender = Gender;
        this.Weight = Weight;
        this.Height = Height;
        this.Birth_Place = Birth_Place;
}
    public String getname() {
        return Baby_Name;
    }

    public void setname(String name) {
        this.Baby_Name = Baby_Name;
    }
    public String getbday() {
        return Birthday;
    }

    public void setbday(String Birthday) {
        this.Birthday = Birthday;
    }
    public String getBtype() {
        return Blood_Type;
    }

    public void setbtype(String Blood_Type) {
        this.Blood_Type = Blood_Type;
    }

    public String getgender() {
        return Gender;
    }

    public void setgender(String Gender) {
        this.Gender = Gender;
    }

    public String getweight() {
        return Weight;
    }
    public void setweight(String Weight) {
        this.Weight = Weight;
    }
    public String getheight() {
        return Height;
    }
    public void setheight(String height) {
        this.Height = Height;
    }
    public String getbplace() {
        return Birth_Place;
    }
    public void setbplace(String Birth_Place) {
        this.Birth_Place = Birth_Place;
    }

}
