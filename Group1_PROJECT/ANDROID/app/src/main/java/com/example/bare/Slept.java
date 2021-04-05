package com.example.bare;

public class Slept {
    private String id,Date,Shift;

    public Slept() {
    }

    public Slept(String id, String Date, String Shift) {
        this.id = id;
        this.Date = Date;
        this.Shift = Shift;
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
    public String getShift() {
        return Shift;
    }

    public void setShift(String Shift) {
        this.Shift = Shift;
    }

}
