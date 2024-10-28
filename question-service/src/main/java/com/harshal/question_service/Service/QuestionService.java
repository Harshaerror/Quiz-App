package com.harshal.question_service.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.harshal.question_service.Model.Question;
import com.harshal.question_service.Model.QuestionWrapper;
import com.harshal.question_service.Model.Response;
import com.harshal.question_service.Repo.QuestionRepo;


//As per the user requirement we can make changes here in service layer
@Service
public class QuestionService {

    @Autowired
    QuestionRepo questionRepo;


//add Exception Handling for give coorect meassage to the user 
    public ResponseEntity<List<Question>> getAllQuestions() {
      try
        {return new ResponseEntity<>(questionRepo.findAll(),HttpStatus.OK);}
        catch(Exception e){
            e.printStackTrace();
        }
         return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }


//add Exception Handling for give coorect meassage to the user
    public ResponseEntity< List<Question>> findBySub(String sub) {
        try {
            return new ResponseEntity<>(questionRepo.findBySub(sub),HttpStatus.OK);
        } catch (Exception e) {
           e.printStackTrace();
        }
       return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public String addQuestions(Question question) {
        questionRepo.save(question);
        return "success";
    }

    public void deleteQuestion(Integer id) {
        questionRepo.deleteById(id);
       
    }


    public ResponseEntity<List<Integer>> getQuestionForQuiz
    (String categoryName, Integer numQuestions) {
    List<Integer> questions = questionRepo.findRandomQuestionBySub(categoryName, numQuestions);
      return new ResponseEntity<>(questions,HttpStatus.OK); 
    }


    public ResponseEntity<List<QuestionWrapper>> getQuestionFromID(List<Integer> questionID) {
        List<QuestionWrapper> wrappers = new ArrayList<>();
        
        for (Integer id : questionID) {
            Optional<Question> optionalQuestion = questionRepo.findById(id);
            
            // Check if the question exists
            if (optionalQuestion.isPresent()) {
                Question question = optionalQuestion.get();
                QuestionWrapper wrapper = new QuestionWrapper();
                
                // Populate the QuestionWrapper object
                wrapper.setId(question.getId());
                wrapper.setQuestionTitle(question.getQuestionTitle());
                wrapper.setOption1(question.getOption1());
                wrapper.setOption2(question.getOption2());
                wrapper.setOption3(question.getOption3());
                wrapper.setOption4(question.getOption4());
                
                // Add the wrapper to the list
                wrappers.add(wrapper);
            } else {
                // Optionally, log or handle the case where a question is not found
                System.out.println("Question with ID " + id + " not found.");
            }
        }
        
        // Return the list of QuestionWrappers
        return new ResponseEntity<>(wrappers, HttpStatus.OK);
    }
    


    public ResponseEntity<Integer> getScore(List<Response> responses) {
    int right = 0;

    for (Response response : responses) {
        Optional<Question> optionalQuestion = questionRepo.findById(response.getId());

        // Check if the question is present
        if (optionalQuestion.isPresent()) {
            Question question = optionalQuestion.get();
            if (response.getResponse().equals(question.getRightAnswer())) {
                right++;
            }
        } else {
            // Handle the case when the question is not found (optional)
            // You can throw an exception, return a different response, or log an error.
            // Example:
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Question not found with ID: " + response.getId());
        }
    }

    return new ResponseEntity<>(right, HttpStatus.OK);
}


}
