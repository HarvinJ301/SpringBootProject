package com.upskill.questionBankAPI.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.upskill.questionBankAPI.exception.NoQuestionFoundException;
import com.upskill.questionBankAPI.model.Question;
import com.upskill.questionBankAPI.repository.QuestionRepo;

@SpringBootTest
public class QuestionServiceTests {
    
    @Autowired
    QuestionService questionService;

    @Autowired
    QuestionRepo questionRepo;

    /**
     * test that there is atleast 5 things being returned when find all questions is run.
     */
    @Test
    void testFindAllQuestionsCallsFindAllMethodOnRepo() {
        assertTrue(questionService.findAllQuestions().size() >= 5);
    }

    /*
    *test as a check to make sure the size we expect is correct and the amount we expect from the method is correct. 
    */ 
    @Test
    void testFindAllQuestionsFailsWhenAGreaterNumberThanIsInTheDatabaseIsPassed(){
        assertFalse(questionService.findAllQuestions().size() >= 25);  
    }
    /**
     * test search By ID functionality
     */
    @Test
    void testSearchQuestionByIDFunction(){
        int searchId = 4;

        assertNotNull(questionService.getQuestionById(searchId));
    }

    /**
     * tests simple search functionality with a FootballTeam passed through 
     * test done to match the first question on the import sql - why foundSearchList is getting 0
     */
   
    @Test
    void testGetCorrectInformationFromSimpleSearchFootballTeam(){
        String simpleSearch = "Arsen";
        List<Question> foundSearchList = questionService.getQuestionsBySimpleSearch(simpleSearch);

        assertNotNull(foundSearchList);

        Question qForm = foundSearchList.get(0);
        assertEquals("Arsenal", qForm.getFootballTeam());
    }

    /**
     * same test as above, tests simple search functionality with League passed through
     * test done to match the first question on the import sql - why foundSearchList is getting 0
     */
    
    @Test
    void testGetCorrectInformationFromSimpleSearchLeague(){
        String simpleSearch = "Premier";
        List<Question> foundSearchList = questionService.getQuestionsBySimpleSearch(simpleSearch);

        assertNotNull(foundSearchList);

        Question qForm = foundSearchList.get(0);
        assertEquals("Premier League", qForm.getLeague());
    }

    /**
     * Checking adding functionality
    * Create a new question and add it to the database 
    * then do a simple search cross checking the id's match indicating it has been added to the database
     */
    @Test
    void testAddingQuestionToDatabase (){
        Question newQuestion = new Question("Premier League", "Arsenal", "Bob", "Arsenal", "Will Arsenal win the Premier League", "They have a chance", "true", "Premier League");
        questionService.addQuestion(newQuestion);

        Question qSearch = questionService.getQuestionsBySimpleSearch("Bob").get(0);
        assertEquals(newQuestion.getQuestionID(), qSearch.getQuestionID());
    }

    /**
     * checking update functionality
     * get the id of a question and updates the FootballTeam within that id to something different.
     * then checks to ensure that the new FootballTeam is the value we passed through.
     */
    @Test
    void testUpdateQuestionDatabaseFunctionality(){
        Question updateQ = questionService.getQuestionById(6);
        updateQ.setFootballTeam("Brighton");
        questionService.updateQuestion(updateQ, 6);
        assertTrue(questionService.getQuestionById(6).getFootballTeam().equals("Brighton"));
    }


    /**
     * Tests the delete functionality by calling it to delete id 1
     * We then call the exception we would expect if there is no id relating to 1 
     * anymore because the delete has been successful
     * if the correct error is outputted and is true the deletion of id 1 has worked. 
     */
    @Test
    void testDeleteQuestionById(){
        questionService.deleteQuestionById(1);
        Exception exception = assertThrows(NoQuestionFoundException.class, () -> {
            questionService.getQuestionById(1);
        });
        String expectedMessage = "There is no question associated with this id: " + 1;
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    
    /**
     * testing that all FootballTeams are found by ensuring something is within the findings.
     */
    @Test
    void test_findAllFootballTeamsFunctionality() {
        List<String> allFootballTeams = questionRepo.findFootballTeams();
        assertTrue(allFootballTeams.size() >= 1); 
    }

    /**
     * testing that all Leagues are found by ensuring something is within the findings.
     */
    @Test
    void test_findAllLeaguesFunctionality() {
        List<String> allLeagues = questionRepo.findLeagues();
        assertTrue(allLeagues.size() >= 1); 
    }

    /**
     * testing that all keywords are found by ensuring something is within the findings.
     */
    @Test
    void test_findAllKeywordsFunctionality() {
        List<String> allKeywords = questionRepo.findKeywords();
        assertTrue(allKeywords.size() >= 1); 
    }
}

