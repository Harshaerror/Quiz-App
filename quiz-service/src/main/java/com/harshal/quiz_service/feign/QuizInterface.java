package com.harshal.quiz_service.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.harshal.quiz_service.Model.QuestionWrapper;
import com.harshal.quiz_service.Model.Response;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {
        @GetMapping("question/generate")
    public ResponseEntity<List<Integer>> getQuestionForQuiz
    (@RequestParam String categoryName,@RequestParam Integer numQuestions);

@PostMapping("question/getQuestions")
public ResponseEntity<List<QuestionWrapper>> getQuestionFromID(@RequestBody List<Integer> questionID);

@PostMapping("question/getscore")
public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);

}
