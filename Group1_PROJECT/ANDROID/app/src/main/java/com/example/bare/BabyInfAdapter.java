package com.example.bare;

public class BabyInfAdapter {
    private String id,Baby_Name,Birthday,Blood_Type,Gender,Weight,Height,Birth_Place;
    public BabyInfAdapter(){

    }

    public BabyInfAdapter(String id, String Baby_Name, String Birthday, String Blood_Type, String Gender, String Weight, String Height, String Birth_Place) {
        this.id = id;
        this.Baby_Name = Baby_Name;
        this.Birthday = Birthday;
        this.Blood_Type = Blood_Type;
        this.Gender = Gender;
        this.Weight = Weight;
        this.Height = Height;
        this.Birth_Place = Birth_Place;
}
    public String getid() {
        return id;
    }

    public void setid(String id) {
        this.id = id;
    }
    public String getname() {
        return Baby_Name;
    }

    public void setname(String Baby_Name) {
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
    public void setheight(String Height) {
        this.Height = Height;
    }
    public String getbplace() {
        return Birth_Place;
    }
    public void setbplace(String Birth_Place) {
        this.Birth_Place = Birth_Place;
    }

}
