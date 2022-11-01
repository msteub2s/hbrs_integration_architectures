package com.example.integrationproject.command;

import java.util.ArrayList;
import java.util.List;

public class EvaluationRecordDao implements Dao<EvaluationRecord>{

    // Implementation Dao missing. Check inherited update method for arguments


    private List<EvaluationRecord> evaluationRecordList = new ArrayList<>();

    public EvaluationRecordDao() {
    }
    @Override
    public EvaluationRecord get(int id) {
        return evaluationRecordList.get((int)id);
    }

    @Override
    public List<EvaluationRecord> getAll() {
        return evaluationRecordList;
    }

    @Override
    public void create(EvaluationRecord salesMan) {
        evaluationRecordList.add(salesMan);
    }

    @Override
    public void update(int sid, String[] record) {
        evaluationRecordList.get(sid).setRating(Integer.valueOf(record[0]));
    }

    @Override
    public void delete(EvaluationRecord evaluationRecord) {
        evaluationRecordList.remove(evaluationRecord);
    }
}
