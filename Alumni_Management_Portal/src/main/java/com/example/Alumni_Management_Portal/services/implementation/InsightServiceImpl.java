package com.example.Alumni_Management_Portal.services.implementation;
import com.example.Alumni_Management_Portal.dto.InsightDto;
import com.example.Alumni_Management_Portal.entities.Insight;
import com.example.Alumni_Management_Portal.repositories.InsightRepository;
import com.example.Alumni_Management_Portal.services.InsightService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class

InsightServiceImpl implements InsightService {

    private InsightRepository insightRepository;
    private ModelMapper modelMapper;

    @Autowired
    public InsightServiceImpl(InsightRepository insightRepository) {
        this.insightRepository = insightRepository;
        this.modelMapper = new ModelMapper();
    }
    @Override
    public void create(InsightDto insightDto) {
        Insight insight = modelMapper.map(insightDto, Insight.class);
        insightRepository.save(insight);
    }

    @Override
    public InsightDto getById(Integer insightId) {
        Optional<Insight> insightOptional = insightRepository.findById(insightId);
        if (insightOptional.isPresent()) {
            InsightDto insightDto = modelMapper.map(insightOptional.get(), InsightDto.class);
            return insightDto;
        }
        throw new RuntimeException("The insight was not found");
    }

    @Override
    public List<InsightDto> getAll() {

        List<Insight> insightServiceList = insightRepository.findAll();
        List<InsightDto> insightDtoList = new ArrayList<>();
        insightServiceList.forEach(insight -> {
            InsightDto insightDto = modelMapper.map(insight, InsightDto.class);
            insightDtoList.add(insightDto);
        });

        return insightDtoList;
    }

}
