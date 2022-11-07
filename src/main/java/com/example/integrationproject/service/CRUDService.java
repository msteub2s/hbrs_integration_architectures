package com.example.integrationproject.service;


import org.bson.Document;

import java.util.List;

public interface CRUDService<T> {
    void create(T T);
    List<Document> readAll();
    Document read(int id);
    void update(int id, T T);
    void delete(int id);
}
