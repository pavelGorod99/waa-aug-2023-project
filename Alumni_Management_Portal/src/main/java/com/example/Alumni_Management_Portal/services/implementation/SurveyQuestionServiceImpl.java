package com.example.Alumni_Management_Portal.services.implementation;

import com.example.Alumni_Management_Portal.dto.InsightDto;
import com.example.Alumni_Management_Portal.dto.SurveyQuestionDto;
import com.example.Alumni_Management_Portal.entities.SurveyQuestion;
import com.example.Alumni_Management_Portal.repositories.SurveyQuestionRepository;
import com.example.Alumni_Management_Portal.services.SurveyQuestionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SurveyQuestionServiceImpl implements SurveyQuestionService {

    private SurveyQuestionRepository surveyQuestionRepository;

    private ModelMapper modelMapper;

    @Autowired
    public SurveyQuestionServiceImpl(SurveyQuestionRepository surveyQuestionRepository) {
        this.surveyQuestionRepository = surveyQuestionRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public void create(SurveyQuestionDto surveyQuestionDto) {
        SurveyQuestion surveyQuestion = modelMapper.map(surveyQuestionDto, SurveyQuestion.class);
        surveyQuestionRepository.save(surveyQuestion);
    }

    @Override
    public void update(SurveyQuestionDto uSurveyQuestionDto) {

        Optional<SurveyQuestion> surveyQuestionOptional = surveyQuestionRepository.findById(uSurveyQuestionDto.getId());

        if (surveyQuestionOptional.isPresent()) {
            SurveyQuestion surveyQuestion = surveyQuestionOptional.get();
            surveyQuestion.setQuestions(uSurveyQuestionDto.getQuestions());
            surveyQuestionRepository.save(surveyQuestion);
        } else throw new RuntimeException("Survey Question does not exist");
    }

//    @Override
//    public void delete(Integer surveyQuestionId) {
//
//    }

    @Override
    public SurveyQuestionDto getById(Integer surveyQuestionId) {

        Optional<SurveyQuestion> surveyQuestionOptional = surveyQuestionRepository.findById(surveyQuestionId);
        if (surveyQuestionOptional.isPresent()) {
            SurveyQuestionDto surveyQuestionDto = modelMapper.map(surveyQuestionOptional.get(), SurveyQuestionDto.class);
            return surveyQuestionDto;
        }
        throw new RuntimeException("The survey question was not found");

    }

    @Override
    public List<SurveyQuestionDto> getAll() {

        List<SurveyQuestion> surveyQuestionList = surveyQuestionRepository.findAll();

        List<SurveyQuestionDto> surveyQuestionDtoList = new ArrayList<>();

        surveyQuestionList.forEach(surveyQuestion -> {

            SurveyQuestionDto surveyQuestionDto = modelMapper.map(surveyQuestion, SurveyQuestionDto.class);

            surveyQuestionDtoList.add(surveyQuestionDto);

        });

        return surveyQuestionDtoList;
    }

    @Override
    public List<SurveyQuestionDto> getSurveyQuestionByInsight(InsightDto insightDto) {
        return null;
    }
}
