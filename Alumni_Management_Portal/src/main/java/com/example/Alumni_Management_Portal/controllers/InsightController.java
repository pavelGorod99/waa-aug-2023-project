package com.example.Alumni_Management_Portal.controllers;


import com.example.Alumni_Management_Portal.dto.InsightDto;
import com.example.Alumni_Management_Portal.services.InsightService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/insights")

public class InsightController {

    private InsightService insightService;

    public InsightController(InsightService insightService){
        this.insightService = insightService;
    }

    @PostMapping
    public void create(@RequestBody InsightDto insight){
        insightService.create(insight);
    }

    @GetMapping("/{insightId}")
    public InsightDto getById (@PathVariable Integer insightId){
        return insightService.getById(insightId);
    }

    @GetMapping
    public List<InsightDto> getAll(){
        return insightService.getAll();
    }
    
}
