package com.example.integrationproject.service.impl;

import com.example.integrationproject.dto.SalesMan;
import com.example.integrationproject.service.CRUDService;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

@Service
public class SalesManServiceImpl implements CRUDService<SalesMan> {
    public static MongoClientURI connectionString = new MongoClientURI("mongodb://localhost:27017");
    public static MongoClient mongoClient = new MongoClient(connectionString);
    public static MongoDatabase database = mongoClient.getDatabase("IntegrationArchitectures");
    public static MongoCollection<Document> salesmenColl = database.getCollection("Salesman");

    @Override
    public void create(SalesMan T) {
        salesmenColl.insertOne(T.toDocument());
    }

    @Override
    public List<Document> readAll() {
        return salesmenColl.find().into(new ArrayList<>());
    }

    @Override
    public Document read(int id) {
        return salesmenColl.find(eq("id", id)).first();
    }

    @Override
    public void update(int id, SalesMan T) {
        salesmenColl.replaceOne(eq("id", id), T.toDocument());
    }

    @Override
    public void delete(int id) {
        salesmenColl.deleteOne(eq("id", id));
    }
}
