package com.example.bare;

public class Vaccined {
    private String id,Date,Name_Of_Vaccine,Kind;

    public Vaccined() {
    }

    public Vaccined(String id, String Date, String Name_Of_Vaccine, String Kind) {
        this.id = id;
        this.Date = Date;
        this.Name_Of_Vaccine = Name_Of_Vaccine;
        this.Kind = Kind;
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
    public String getKind() {
        return Kind;
    }

    public void setKind(String Kind) {
        this.Kind = Kind;
    }

    public String getName_Of_Vaccine() {
        return Name_Of_Vaccine;
    }

    public void setName_Of_Vaccine(String Name_Of_Vaccine) {
        this.Name_Of_Vaccine = Name_Of_Vaccine;
    }

}
