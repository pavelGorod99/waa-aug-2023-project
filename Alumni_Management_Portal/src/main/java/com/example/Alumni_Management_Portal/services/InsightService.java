package com.example.Alumni_Management_Portal.services;

import com.example.Alumni_Management_Portal.dto.InsightDto;
import com.example.Alumni_Management_Portal.dto.SurveyAnswerDto;
import com.example.Alumni_Management_Portal.dto.SurveyQuestionDto;


import java.util.List;

public interface InsightService {

    public void create(InsightDto insightDto);

    public InsightDto getById (Integer insightId);

    public List<InsightDto> getAll();
}

