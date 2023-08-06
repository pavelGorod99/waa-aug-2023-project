package com.example.Alumni_Management_Portal.controllers;

import com.example.Alumni_Management_Portal.dto.SurveyAnswerDto;
import com.example.Alumni_Management_Portal.services.SurveyAnswerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/surveyAnswers")

public class SurveyAnswerController {

    private SurveyAnswerService surveyAnswerService;

    public SurveyAnswerController(SurveyAnswerService surveyAnswerService) {
        this.surveyAnswerService = surveyAnswerService;
    }

    @GetMapping("/{surveyAnswerId}")
    public SurveyAnswerDto getById(@PathVariable Integer surveyAnswerId) {
        return surveyAnswerService.getById(surveyAnswerId);
    }

    @GetMapping
    public List<SurveyAnswerDto> getAll() {
        return surveyAnswerService.getAll();
    }
}
