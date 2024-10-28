package com.harshal.question_service.Controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.harshal.question_service.Model.Question;
import com.harshal.question_service.Model.QuestionWrapper;
import com.harshal.question_service.Model.Response;
import com.harshal.question_service.Service.QuestionService;

@RestController
@RequestMapping("question")
public class QuestionController {

@Autowired
QuestionService questionService;


    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
    return questionService.getAllQuestions();
    }

   @GetMapping("category/{sub}") 
   public ResponseEntity< List<Question> >getQuestionsBySub(@PathVariable  String sub)
    {
      return questionService.findBySub(sub);
    }

    @PostMapping("add")
    public String addQuestions(@RequestBody Question question){
    return questionService.addQuestions(question);
    }


    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Integer id){
        questionService.deleteQuestion(id);
        return new ResponseEntity<>("deleted successfully",HttpStatus.OK);
    }

    @GetMapping("generate")
    public ResponseEntity<List<Integer>> getQuestionForQuiz
    (@RequestParam String categoryName,@RequestParam Integer numQuestions)
    {
      return questionService.getQuestionForQuiz(categoryName,numQuestions);
    }

@PostMapping("getQuestions")
public ResponseEntity<List<QuestionWrapper>> getQuestionFromID(@RequestBody List<Integer> questionID){
  if (questionID == null || questionID.isEmpty()) {
    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
}
  return questionService.getQuestionFromID(questionID);
}

@PostMapping("getscore")
public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
  return questionService.getScore(responses);
} 

}


