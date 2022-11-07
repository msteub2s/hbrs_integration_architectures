package com.example.integrationproject.service.impl;

import com.example.integrationproject.dto.EvaluationRecord;
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
public class RecordServiceImpl implements CRUDService<EvaluationRecord> {
    public static MongoClientURI connectionString = new MongoClientURI("mongodb://localhost:27017");
    public static MongoClient mongoClient = new MongoClient(connectionString);
    public static MongoDatabase database = mongoClient.getDatabase("IntegrationArchitectures");

    public static MongoCollection<Document> recordsColl = database.getCollection("PerformanceRecords");

    @Override
    public void create(EvaluationRecord T) {
        recordsColl.insertOne(T.toDocument());
    }

    @Override
    public List<Document> readAll() {
        return recordsColl.find().into(new ArrayList<>());
    }

    @Override
    public Document read(int id) {
        return recordsColl.find(eq("salesManID", id)).first();
    }

    @Override
    public void update(int id, EvaluationRecord T) {
        recordsColl.replaceOne(eq("salesManID", id), T.toDocument());
    }

    @Override
    public void delete(int id) {
        recordsColl.deleteOne(eq("salesManID", id));
    }
}
