package com.example.demo.model.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/data")
public class DataController {

    private final DataService dataService;

    @Autowired
    public DataController(DataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping
    public List<Data> getData()
    {
        return dataService.getData();
    }

    @PostMapping
    void InsertData(@RequestBody PostData postData)
    {
        dataService.insertData(postData);
    }
}
