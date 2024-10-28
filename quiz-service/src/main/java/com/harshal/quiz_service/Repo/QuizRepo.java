package com.harshal.quiz_service.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.harshal.quiz_service.Model.Quiz;

@Repository
public interface QuizRepo extends JpaRepository<Quiz,Integer> {

    
}
