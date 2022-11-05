package com.example.integrationproject.controller;

import com.example.integrationproject.dto.EvaluationRecord;
import com.example.integrationproject.dto.SalesMan;
import com.example.integrationproject.service.impl.RecordServiceImpl;
import com.example.integrationproject.service.impl.SalesManServiceImpl;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/record")
public class RecordController {

    @Autowired
    private RecordServiceImpl recordService;

    @GetMapping
    public List<Document> getAll(){
        return recordService.readAll();
    }

    @GetMapping("/{id}")
    public Document get(@PathVariable int id){
        return recordService.read(id);
    }

    @PostMapping
    public void createSalesMan(@RequestBody EvaluationRecord record){
        recordService.create(record);
    }

    @PutMapping("/{id}")
    public void updateSalesMan(@PathVariable int id, @RequestBody EvaluationRecord record){
        recordService.update(id, record);
    }

    @DeleteMapping("/{id}")
    public void deleteSalesMan(@PathVariable int id){
        recordService.delete(id);
    }
}
