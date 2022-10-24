package com.example.integrationproject.command;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static com.mongodb.client.model.Filters.eq;

@ShellComponent
public class ShellCommands implements ManagePersonal{
    public static MongoClientURI connectionString = new MongoClientURI("mongodb://localhost:27017");
    public static MongoClient mongoClient = new MongoClient(connectionString);
    public static MongoDatabase database = mongoClient.getDatabase("IntegrationArchitectures");
    public static MongoCollection<Document> salesmenColl = database.getCollection("Salesman");
    public static MongoCollection<Document> recordsColl = database.getCollection("PerformanceRecords");

    @Override
    @ShellMethod(value = "Add Salesman.", key={"add salesman", "addSM"})
    public void addSalesMan(String firstname, String lastname, int id) {
        SalesMan tmp = new SalesMan(firstname, lastname, id);
        salesmenColl.insertOne(tmp.toDocument());
    }

    @Override
    @ShellMethod(value = "Add Record.", key={"add record", "addR"})
    public void addPerformanceRecord(int record, int sid) {
        EvaluationRecord rec = new EvaluationRecord(record);
        recordsColl.insertOne(rec.toDocument().append("SalesManID", sid));
    }

    @Override
    @ShellMethod(value = "read SalesMan", key={"read salesman", "readSM"})
    public SalesMan readSalesMan(int sid) {
        String[][] Data = jsonToArray(salesmenColl.find(eq("id", sid)).first());
        return new SalesMan(Data[1][1], Data[2][1], Integer.parseInt(Data[3][1]));
    }

    @Override
    @ShellMethod(value = "query SalesMan", key={"query salesman", "querySM"})
    public List<SalesMan> querySalesMan(String attribute, String key) {
     /*   List<SalesMan> salesManList = new ArrayList<>();
        Block<Document> blockToList = new Block<Document>() {
            @Override
            public void apply(final Document document) {
                String[][] Data = jsonToArray(document);
                salesManList.add(new SalesMan(Data[1][1], Data[2][1], Integer.parseInt(Data[3][1])));
            }
        };
        salesmenColl.find(eq(attribute, key)).forEach((Consumer<? super Document>) blockToList);
        return salesManList;*/
        return null;
    }

    @Override
    @ShellMethod(value = "read Evaluation", key={"read evaluation", "readE"})
    public EvaluationRecord readEvaluationRecords(int sid) {
        String[][] Data = jsonToArray(recordsColl.find(eq("SalesManID", sid)).first());
        return new EvaluationRecord(Integer.parseInt(Data[1][1]));
    }

    @Override
    @ShellMethod(value = "update Salesman", key={"update salesman", "updateSM"})
    public void updateSalesMan(int id, String updateFirstname, String updateLastname, int updateId) {
        salesmenColl.replaceOne(eq("id", id),
                new SalesMan(updateFirstname, updateLastname, updateId).toDocument());
    }

    @Override
    @ShellMethod(value = "update Evaluation", key={"update evaluation", "updateE"})
    public void updateEvaluationRecord(int salesManID, int record, int updateId) {
        recordsColl.replaceOne(eq("SalesManID", salesManID),
                new EvaluationRecord(record).toDocument().append("SalesManID", updateId));
    }

    @Override
    @ShellMethod(value = "delete Salesman", key={"delete salesman", "deleteSM"})
    public void deleteSalesMan(int id) {
        salesmenColl.deleteOne(eq("id", id));
    }

    @Override
    @ShellMethod(value = "delete Evaluation", key={"delete evaluation", "deleteE"})
    public void deleteEvaluationRecord(int id) {
        recordsColl.deleteOne(eq("SalesManID", id));
    }

    public String[][] jsonToArray(Document myDoc){
        //clear data
        String clearedJson = myDoc.toJson().replace("{\"_id\": {", "");
        clearedJson = clearedJson.replaceAll("\\}","");
        clearedJson = clearedJson.replaceAll(" ","");

        // use data
        String tmp[] = clearedJson.split(",");
        String Data[][] = new String[tmp.length][2];
        for(int i = 0; i < tmp.length; i++){
            String values[] = tmp[i].split(":");
            Data[i][0] = values[0];
            Data[i][1] = values[1];
        }
        return Data;
    }
}
