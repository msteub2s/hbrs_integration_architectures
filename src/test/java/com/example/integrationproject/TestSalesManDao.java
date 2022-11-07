package com.example.integrationproject;

import com.example.integrationproject.dto.SalesMan;
import com.example.integrationproject.dao.SalesManDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class TestSalesManDao {

    private SalesManDao dao = null;

    @BeforeEach
    public void setUp() {
        dao = new SalesManDao();
    }

    @Test void createAndReadTest() {
        SalesMan sm = new SalesMan("Malte", "Steubing", 1);

        dao.create(sm);

        SalesMan sm2 = dao.get(0);

        assertEquals(sm, sm2);

        dao.delete(sm);
    }
}
