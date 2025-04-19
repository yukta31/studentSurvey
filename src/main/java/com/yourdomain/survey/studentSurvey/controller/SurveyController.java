package com.yourdomain.survey.studentSurvey.controller;

import com.yourdomain.survey.studentSurvey.entity.Survey;
import com.yourdomain.survey.studentSurvey.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class SurveyController {

    @Autowired
    private SurveyRepository surveyRepository;

    // Create a new survey
    @PostMapping
    public Survey createSurvey(@RequestBody Survey survey) {
        return surveyRepository.save(survey);
    }

    // Retrieve all surveys
    @GetMapping
    public List<Survey> getAllSurveys() {
        return surveyRepository.findAll();
    }

    // Retrieve survey by id
    @GetMapping("/{id}")
    public ResponseEntity<Survey> getSurveyById(@PathVariable Long id) {
        Survey survey = surveyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Survey not found with id: " + id));
        return ResponseEntity.ok(survey);
    }

    // Update a survey
    @PutMapping("/{id}")
    public ResponseEntity<Survey> updateSurvey(@PathVariable Long id, @RequestBody Survey surveyDetails) {
        Survey survey = surveyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Survey not found with id: " + id));
        
        // Example: Update fields (you should update all that are needed)
        survey.setFirstName(surveyDetails.getFirstName());
        survey.setLastName(surveyDetails.getLastName());
        // Continue updating other fields...
        
        Survey updatedSurvey = surveyRepository.save(survey);
        return ResponseEntity.ok(updatedSurvey);
    }

    // Delete a survey
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSurvey(@PathVariable Long id) {
        Survey survey = surveyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Survey not found with id: " + id));
        surveyRepository.delete(survey);
        return ResponseEntity.noContent().build();
    }
}
