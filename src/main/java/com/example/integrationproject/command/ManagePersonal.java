package com.example.integrationproject.command;

import java.util.List;

public interface ManagePersonal {
    // Create
    public void addSalesMan( String firstname, String lastname, int id );
    public void addPerformanceReord( int record , int sid );

    // Read
    public SalesMan readSalesMan( int sid );
    public List<SalesMan> querySalesMan(String attribute , String key );
    public EvaluationRecord readEvaluationRecords( int sid );

    // Update
    public void updateSalesMan( int id, SalesMan updatedSalesMan);
    public void updateEvaluationRecord( int salesManID, EvaluationRecord updatedRecord);

    // Delete
    public void deleteSalesMan(int id);
    public void deleteEvaluationRecord(int id);
}
