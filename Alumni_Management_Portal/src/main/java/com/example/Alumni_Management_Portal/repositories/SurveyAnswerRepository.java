package com.example.Alumni_Management_Portal.repositories;

import com.example.Alumni_Management_Portal.entities.SurveyAnswer;
import com.example.Alumni_Management_Portal.entities.SurveyQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurveyAnswerRepository extends JpaRepository<SurveyAnswer, Integer> {

}
