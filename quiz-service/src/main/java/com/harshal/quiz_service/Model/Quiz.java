package com.harshal.quiz_service.Model;





import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;
import lombok.Data;



@Entity
@Table(name = "quiz")
@Data
public class Quiz {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;
private String title;


@ElementCollection
private List<Integer> questionID;







}
