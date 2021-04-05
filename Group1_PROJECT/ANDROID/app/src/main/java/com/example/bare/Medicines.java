package com.example.bare;

public class Medicines {
    private String id,Date,Prescription,Kind,Quantity;
    public Medicines() {

    }
    public Medicines(String id, String Date, String Prescription, String Kind,String Quantity) {
        this.id = id;
        this.Date = Date;
        this.Prescription = Prescription;
        this.Kind=Kind;
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


    public String getPrescription() {
        return Prescription;
    }

    public void setPrescription(String Prescription) {
        this.Prescription = Prescription;
    }

    public String getQuantity() {
        return Quantity;
    }
    public void setQuantity(String Quantity) {
        this.Quantity = Quantity;
    }
    public String getKind() {
        return Kind;
    }
    public void setKind(String Kind) {
        this.Kind = Kind;
    }
}
