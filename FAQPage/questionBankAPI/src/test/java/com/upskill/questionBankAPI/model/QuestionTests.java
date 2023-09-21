package com.upskill.questionBankAPI.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class QuestionTests {
    
    Question questions;

    @BeforeEach
    void setUp() throws Exception {
        questions = new Question();
    }

    /**
     * test get and set id for question
     */
    @Test
    void testGetAndSetQuestionID() {
        int expectedQuestionId = 1;
        questions.setQuestionID(expectedQuestionId);
        assertEquals(expectedQuestionId, questions.getQuestionID());
    }

    //testing get and set for Leagues
    @Test
    void testGetAndSetForLeagues() {
        String expectedLeague = "Leagues1";
        questions.setLeague(expectedLeague);
        assertEquals(expectedLeague, questions.getLeague());
    }

    //test get and set for Football Team
    @Test
    void testGetAndSetForFootballTeam(){
        String expectedFootballTeam = "Arsenal";
        questions.setFootballTeam(expectedFootballTeam);
        assertEquals(expectedFootballTeam, questions.getFootballTeam());
    }

    //test get and set for AskedBy
    @Test
    void testGetAndSetForAskedBy(){
        String expectedAskedBy = "AskedBy1";
        questions.setAskedBy(expectedAskedBy);
        assertEquals(expectedAskedBy, questions.getAskedBy());
    }

    //test get and set for team user who asked supports
    @Test
    void testGetAndSetForTeamUserWhoAskedSupports(){
        String expectedTeamUserWhoAskedSupports = "TeamUserWhoAskedSupports1";
        questions.setTeamUserWhoAskedSupports(expectedTeamUserWhoAskedSupports);
        assertEquals(expectedTeamUserWhoAskedSupports, questions.getTeamUserWhoAskedSupports());
    }

    //test get and set for question asked
    @Test
    void testGetAndSetForQuestionAsked(){
        String expectedQuestionAsked = "Who Scored?";
        questions.setQuestionAsked(expectedQuestionAsked);
        assertEquals(expectedQuestionAsked, questions.getQuestionAsked());
    }

    //test get and set for question answer
    @Test
    void testGetAndSetForQuestionAnswer(){
        String expectedQuestionAnswer = "Haaland";
        questions.setQuestionAnswer(expectedQuestionAnswer);
        assertEquals(expectedQuestionAnswer, questions.getQuestionAnswer());
    }

    //test get and set for checking if the answer is answered.
    @Test
    void testGetAndSetForAnswered(){
        String expectedAnswered = "Yes";
        questions.setAnswered(expectedAnswered);
        assertEquals(expectedAnswered, questions.getAnswered());
    }

    //test get and set for keywords
    @Test
    void testGetAndSetForKeywords(){
        String expectedKeyword = "Java";
        questions.setKeywords(expectedKeyword);
        assertEquals(expectedKeyword, questions.getKeywords());
    }
}

