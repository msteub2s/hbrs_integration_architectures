package com.example.integrationproject.controller;

import com.example.integrationproject.command.SalesMan;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import static com.mongodb.client.model.Filters.eq;

@RestController
@RequestMapping("/salesman")
public class SalesmanController {

    public static MongoClientURI connectionString = new MongoClientURI("mongodb://localhost:27017");
    public static MongoClient mongoClient = new MongoClient(connectionString);
    public static MongoDatabase database = mongoClient.getDatabase("IntegrationArchitectures");
    public static MongoCollection<Document> salesmenColl = database.getCollection("Salesman");
    public static MongoCollection<Document> recordsColl = database.getCollection("PerformanceRecords");

    /* @PostConstruct
    public void postConstruct () {
        SalesMan s = new SalesMan();
    } */

    @PostMapping
    public void createSalesman(@RequestBody(required = true) SalesMan sm){
        salesmenColl.insertOne(sm.toDocument());
    }
}
