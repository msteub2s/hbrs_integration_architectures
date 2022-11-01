package com.example.integrationproject.command;

import java.util.List;

public interface Dao<T>{

    T get(int id);

    List<T> getAll();

    void create (T t);
    void update (int sid, String[] params);
    void delete (T t);

}
