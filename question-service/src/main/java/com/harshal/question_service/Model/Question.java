package com.harshal.question_service.Model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
 
@Data
@Entity
@Table(name  = "question")

 
public class Question {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String sub;
    private String difficulty;
    private String option1;
    private String option2;
    private String option3;
    @Column(name = "questionTitle")
    private String QuestionTitle;
    @Column(name = "rightAnswer")
    private String RightAnswer;
    private String option4;
    
   

}
