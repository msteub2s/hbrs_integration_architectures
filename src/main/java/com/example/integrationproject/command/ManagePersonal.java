package com.example.integrationproject.command;

import com.example.integrationproject.dto.EvaluationRecord;
import com.example.integrationproject.dto.SalesMan;

import java.util.List;

public interface ManagePersonal {
    // Create
    public void addSalesMan( String firstname, String lastname, int id );
    public void addPerformanceRecord( int record , int sid );
    public SalesMan readSalesMan(int sid );
    public List<SalesMan> querySalesMan(String attribute , String key );
    public EvaluationRecord readEvaluationRecords(int sid );

    // Update
    public void updateSalesMan( int id, String updateFirstname, String updateLastname, int updateId);
    public void updateEvaluationRecord( int salesManID, int record, int updateId);

    // Delete
    public void deleteSalesMan(int id);
    public void deleteEvaluationRecord(int id);
}
