package com.example.Alumni_Management_Portal.services.implementation;

import com.example.Alumni_Management_Portal.dto.SurveyAnswerDto;
import com.example.Alumni_Management_Portal.entities.SurveyAnswer;
import com.example.Alumni_Management_Portal.repositories.SurveyAnswerRepository;
import com.example.Alumni_Management_Portal.services.SurveyAnswerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SurveyAnswerServiceImpl implements SurveyAnswerService {

    private SurveyAnswerRepository surveyAnswerRepository;

    private ModelMapper modelMapper;

    @Autowired
    public SurveyAnswerServiceImpl(SurveyAnswerRepository surveyAnswerRepository) {
        this.surveyAnswerRepository = surveyAnswerRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public List<SurveyAnswerDto> getAll() {

        List<SurveyAnswer> surveyAnswerList = surveyAnswerRepository.findAll();

        List<SurveyAnswerDto> surveyAnswerDtoList = new ArrayList<>();
        surveyAnswerList.forEach(surveyAnswer -> {
            SurveyAnswerDto surveyAnswerDto = modelMapper.map(surveyAnswer, SurveyAnswerDto.class);
            surveyAnswerDtoList.add(surveyAnswerDto);
        });
        return surveyAnswerDtoList;
    }

    @Override
    public SurveyAnswerDto getById(int surveyAnswerId) {
        Optional<SurveyAnswer> surveyAnswerOptional = surveyAnswerRepository.findById(surveyAnswerId);
        if (surveyAnswerOptional.isPresent()) {
            SurveyAnswerDto surveyAnswerDto = modelMapper.map(surveyAnswerOptional.get(), SurveyAnswerDto.class);
            return surveyAnswerDto;
        }
        throw new RuntimeException("The survey answer was not found");
    }
}

