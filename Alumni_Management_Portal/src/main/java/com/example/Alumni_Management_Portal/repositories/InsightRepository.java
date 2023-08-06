package com.example.Alumni_Management_Portal.repositories;

import com.example.Alumni_Management_Portal.entities.Insight;
import com.example.Alumni_Management_Portal.entities.SurveyAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InsightRepository extends JpaRepository<Insight, Integer> {

}
