package com.harshal.question_service.Repo;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.harshal.question_service.Model.Question;

@Repository
public interface QuestionRepo extends JpaRepository<Question,Integer>{

    List<Question> findBySub(String sub);

   @Query(value = "select q.id FROM question q where q.sub=:sub order by RAND() LIMIT :limit",nativeQuery=true)
     List<Integer> findRandomQuestionBySub(@Param("sub") String sub, @Param("limit") Integer noOfQuestion);
      
   
}