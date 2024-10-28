package com.harshal.quiz_service.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.harshal.quiz_service.Model.*;
import com.harshal.quiz_service.Service.QuizService;

@RestController
@RequestMapping("quiz")
public class QuizController {


    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody Quizdto Quizdto){

         if(Quizdto.getNoOfQuestion() == null){
        return new ResponseEntity<>( " number of questions cant be null",HttpStatus.BAD_REQUEST);
    }
        return quizService.createQuiz(Quizdto.getCategory(), Quizdto.getNoOfQuestion(),Quizdto.getTitle());
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable Integer id){
        return quizService.getQuiz(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id,@RequestBody List<Response> response){
return quizService.calculateResult(id,response);
    }
 
}
