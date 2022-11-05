package com.example.integrationproject.controller;

import com.example.integrationproject.dto.SalesMan;
import com.example.integrationproject.service.impl.RecordServiceImpl;
import com.example.integrationproject.service.impl.SalesManServiceImpl;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/salesman")
public class SalesManController {

    @Autowired
    private SalesManServiceImpl salesManService;

    @GetMapping
    public List<Document> getAll(){
        return salesManService.readAll();
    }

    @GetMapping("/{id}")
    public Document get(@PathVariable int id){
        return salesManService.read(id);
    }

    @PostMapping
    public void createSalesMan(@RequestBody SalesMan salesman){
        salesManService.create(salesman);
    }

    @PutMapping("/{id}")
    public void updateSalesMan(@PathVariable int id, @RequestBody SalesMan salesman){
        salesManService.update(id, salesman);
    }

    @DeleteMapping("/{id}")
    public void deleteSalesMan(@PathVariable int id){
        salesManService.delete(id);
    }
}
