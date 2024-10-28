package com.harshal.quiz_service.Service;



import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.harshal.quiz_service.Model.Quiz;
import com.harshal.quiz_service.Model.Quizdto;
import com.harshal.quiz_service.Model.Response;

import com.harshal.quiz_service.Model.QuestionWrapper;

import com.harshal.quiz_service.Repo.QuizRepo;
import com.harshal.quiz_service.feign.QuizInterface;

@Service
public class QuizService {

@Autowired
QuizRepo quizRepo;

@Autowired
QuizInterface quizInterface;

public ResponseEntity<String> createQuiz(String category, int noOfQuestion, String title) {
   
    
    List<Integer> questions = quizInterface.getQuestionForQuiz(category, noOfQuestion).getBody();
   
   // Create and save the quiz
   Quiz quiz = new Quiz();
   quiz.setTitle(title);
   quiz.setQuestionID(questions);
   // Save the quiz in the database
   quizRepo.save(quiz);
   
   return new ResponseEntity<>("Quiz created successfully", HttpStatus.CREATED);
}


public ResponseEntity<List<QuestionWrapper>> getQuiz(Integer id) {

Quiz quiz = quizRepo.findById(id).get();
List<Integer> questionIDs = quiz.getQuestionID();
ResponseEntity<List<QuestionWrapper>> questions = quizInterface.getQuestionFromID(questionIDs);
return questions;

}

public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        ResponseEntity<Integer> score =  quizInterface.getScore(responses);

return  score;
}


}


