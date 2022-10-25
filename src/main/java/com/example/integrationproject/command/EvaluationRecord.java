package com.example.integrationproject.command;

import org.bson.Document;

public class EvaluationRecord {
    private int overallRating;
    public EvaluationRecord(int rating){
        this.overallRating = rating;
    }

    public int getRating() {
        return overallRating;
    }

    public void setRating(int rating) {
        this.overallRating = rating;
    }

    public Document toDocument() {
        org.bson.Document document = new Document();
        document.append("rating" , this.overallRating );
        return document;
    }

    @Override
    public String toString() {
        return "rating: " + getRating();
    }
}
