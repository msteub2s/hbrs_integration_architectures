package com.example.integrationproject.dto;

import org.bson.Document;

public class EvaluationRecord {
    private int overallRating;
    private int salesManID;
    public EvaluationRecord(int rating, int salesManID){
        this.overallRating = rating;
        this.salesManID = salesManID;
    }

    public int getRating() {
        return overallRating;
    }

    public void setRating(int rating) {
        this.overallRating = rating;
    }
    public int getSalesManID() {
        return salesManID;
    }
    public void setSalesManID(int id) {
        this.salesManID = id;
    }

    public Document toDocument() {
        org.bson.Document document = new Document();
        document.append("rating" , this.overallRating )
                .append("salesManID", this.salesManID);
        return document;
    }

    @Override
    public String toString() {
        return "rating: " + getRating();
    }
}
