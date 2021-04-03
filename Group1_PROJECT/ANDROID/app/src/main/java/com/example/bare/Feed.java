package com.example.bare;



public class Feed {
    private String id,Date,Time,Type_Of_Food,Quantity;

    public Feed() {
    }

    public Feed(String id, String Date , String Time, String Type_Of_Food, String Quantity) {
        this.id = id;
        this.Date = Date;
        this.Time = Time;
        this.Type_Of_Food = Type_Of_Food;
        this.Quantity = Quantity;
    }

    public String getid() {
        return id;
    }

    public void setid(String id) {
        this.id = id;
    }
    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }
    public String getTime() {
        return Time;
    }

    public void setTime(String Time) {
        this.Time = Time;
    }

    public String getType_Of_Food() {
        return Type_Of_Food;
    }

    public void setType_Of_Food(String Type_Of_Food) {
        this.Type_Of_Food = Type_Of_Food;
    }

    public String getQuantity() {
        return Quantity;
    }
    public void setQuantity(String Quantity) {
        this.Quantity = Quantity;
    }

}
