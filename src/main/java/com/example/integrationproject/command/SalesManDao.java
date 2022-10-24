package com.example.integrationproject.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SalesManDao implements Dao<SalesMan>{

    private List<SalesMan> salesManList = new ArrayList<>();

    public SalesManDao() {
    }

    @Override
    public Optional<SalesMan> get(int id) {
        return Optional.ofNullable(salesManList.get((int)id));
    }

    @Override
    public List<SalesMan> getAll() {
        return salesManList;
    }

    @Override
    public void create(SalesMan salesMan) {
        salesManList.add(salesMan);
    }

    @Override
    public void update(int sid, String[] params) {
        for(int i = 0; i < salesManList.size(); i++) {
            if(salesManList.get(i).getId() == Integer.valueOf(sid)) {
                salesManList.get(i).setFirstname(params[0]);
                salesManList.get(i).setLastname(params[1]);
                break;
            }
        }
        System.out.println("id not found: " + sid);
    }

    @Override
    public void delete(SalesMan salesMan) {
        salesManList.remove(salesMan);
    }
}
