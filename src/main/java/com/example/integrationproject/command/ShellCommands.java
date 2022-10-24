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
    public void addPerformanceReord(int record, int sid) {
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
        String[][] Data = jsonToArray(recordsColl.find(eq("salesManID", sid)).first());
        return new EvaluationRecord(Integer.parseInt(Data[1][1]));
    }

    @Override
    public void updateSalesMan(int id, SalesMan updatedSalesMan) {//String[] params

    }

    @Override
    public void updateEvaluationRecord(int salesManID, EvaluationRecord updatedRecord) {//String[] params

    }

    @Override
    public void deleteSalesMan(int id) {

    }

    @Override
    public void deleteEvaluationRecord(int id) {

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
