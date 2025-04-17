package com.yourdomain.survey.studentSurvey.repository;


import com.yourdomain.survey.studentSurvey.entity.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Long> {
    // You can add custom query methods here if needed
}
