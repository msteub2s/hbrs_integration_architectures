package com.example.integrationproject;

import com.example.integrationproject.command.EvaluationRecord;
import com.example.integrationproject.command.EvaluationRecordDao;
import com.example.integrationproject.command.SalesMan;
import com.example.integrationproject.command.SalesManDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;


class TestEvaluationRecordDao {

    private EvaluationRecordDao dao = null;

    @BeforeEach
    public void setUp() {

        dao = new EvaluationRecordDao();
    }

    @Test void createAndReadTest() {
        EvaluationRecord er = new EvaluationRecord(10);

        dao.create(er);

        EvaluationRecord er2 = dao.get(0);

        assertEquals(er, er2);

        dao.delete(er);
    }
}
