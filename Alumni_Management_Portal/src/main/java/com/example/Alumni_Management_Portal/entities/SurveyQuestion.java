package com.example.Alumni_Management_Portal.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity

public class SurveyQuestion {

    @Id
    private int id;

    private String questions;

    @ManyToOne
    @JoinColumn(name = "insight_id")
    private Insight insight;

    @OneToMany
    @JoinColumn(name = "question_id")
    private List<SurveyAnswer> surveyAnswerList;
}
