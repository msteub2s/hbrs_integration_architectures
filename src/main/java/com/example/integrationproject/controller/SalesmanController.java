package com.example.integrationproject.controller;

import com.example.integrationproject.dto.SalesMan;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.web.bind.annotation.*;
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
    MongoCursor<Document> cursor = salesmenColl.find().iterator();

    @GetMapping
    public List<Document> getAll(){
        List<Document> docs = new ArrayList<>();
        while(cursor.hasNext()){
            docs.add(cursor.next());
        }
        return docs;
    }
    // @PostConstruct

    @PostMapping
    public void createSalesman(@RequestBody(required = true) SalesMan sm){
        salesmenColl.insertOne(sm.toDocument());
    }
    @PutMapping
    public void updateSalesman(@PathVariable(required = true) int id, @RequestBody(required = true) SalesMan sm){
        Bson filter = eq("id", 1);
        Bson update = sm.toDocument();
        database.getCollection("Salesman").findOneAndUpdate(filter, update);
    }

    @DeleteMapping("/(id)")
    public void deleteSalesman(@PathVariable(required = true) int id){
        Bson query  = eq("id", id);
        DeleteResult result = salesmenColl.deleteOne(query);
        System.out.println("Deleted Document count: " + result.getDeletedCount());
    }
}
