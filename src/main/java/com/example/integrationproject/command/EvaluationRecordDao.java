package com.example.integrationproject.command;

import java.util.ArrayList;
import java.util.List;

public class EvaluationRecordDao {

    // Implementation Dao missing. Check inherited update method for arguments

    private List<EvaluationRecord> evaluationRecordList = new ArrayList<>();

    public EvaluationRecordDao() {
    }

    public EvaluationRecord get(int id) {
        return evaluationRecordList.get((int)id);
    }


    public List<EvaluationRecord> getAll() {
        return evaluationRecordList;
    }


    public void create(EvaluationRecord salesMan) {
        evaluationRecordList.add(salesMan);
    }

    public void update(int sid, int record) {
        evaluationRecordList.get(sid).setRating(record);
    }


    public void delete(EvaluationRecord evaluationRecord) {
        evaluationRecordList.remove(evaluationRecord);
    }
}
