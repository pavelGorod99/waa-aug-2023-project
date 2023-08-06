package com.example.Alumni_Management_Portal.controllers;

import com.example.Alumni_Management_Portal.dto.SurveyQuestionDto;
import com.example.Alumni_Management_Portal.services.SurveyQuestionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/surveyQuestions")
public class SurveyQuestionController {

    private SurveyQuestionService surveyQuestionService;

    public SurveyQuestionController (SurveyQuestionService surveyQuestionService){
        this.surveyQuestionService=surveyQuestionService;
    }

    @PostMapping
    public void save(@RequestBody SurveyQuestionDto surveyQuestion){
        surveyQuestionService.create(surveyQuestion);
    }

    @PutMapping("/surveyQuestion")
    public void update(@RequestBody SurveyQuestionDto surveyQuestion){
        surveyQuestionService.update(surveyQuestion);
    }

    @GetMapping("/{surveyQuestionId}")
    public SurveyQuestionDto getById (@PathVariable Integer surveyQuestionId){
        return surveyQuestionService.getById(surveyQuestionId);
    }

    @GetMapping
    public List<SurveyQuestionDto> getAll(){
        return surveyQuestionService.getAll();
    }
}
