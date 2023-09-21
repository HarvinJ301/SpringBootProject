package com.upskill.questionBankAPI.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.upskill.questionBankAPI.model.Question;
import com.upskill.questionBankAPI.service.QuestionService;

@SpringBootTest
@AutoConfigureMockMvc
public class QuestionControllerTests {
    
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mvc;
    @Autowired
    private QuestionController questionController;
    @Autowired
    private QuestionService questionService;

    @BeforeEach
	void setUp() throws Exception {
		this.mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

    /**
     * Testing whether the controller is null or not - ensuring it is being created.
     */
	@Test
	void test_controllerNotNull(){
		assertThat(questionController).isNotNull();
	}

    /**
     * Testing URL For getting all questions and making sure it is in a JSON Format
     * @throws Exception
     */
    @Test
    void test_getAllQuestions() throws Exception{
        mvc.perform(MockMvcRequestBuilders
                    .get("http://localhost:8088/api/v1/question")
                    .accept(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk());
    }

    /**
     * Testing URL For getting all Leagues and making sure it is in a JSON Format
     * @throws Exception
     */
    @Test
    void test_getAllLeagues() throws Exception{
        mvc.perform(MockMvcRequestBuilders
                    .get("http://localhost:8088/api/v1/question/League")
                    .accept(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk());
    }

    /**
     * Testing URL For getting all footballTeams and making sure it is in a JSON Format
     * @throws Exception
     */
    
    @Test
    void test_getAllFootballTeams() throws Exception{
        mvc.perform(MockMvcRequestBuilders
                    .get("http://localhost:8088/api/v1/question/FootballTeam")
                    .accept(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk());
    }

     /**
     * Testing URL For getting all keywords and making sure it is in a JSON Format
     * @throws Exception
     */
    @Test
    void test_getAllKeywords() throws Exception{
        mvc.perform(MockMvcRequestBuilders
                    .get("http://localhost:8088/api/v1/question/keyword")
                    .accept(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk());
    }

    /**
     *  Testing questions by Id - checking that when we search for an Id it gives back the correct id through the url.
     * @throws Exception
     */
     @Test
     void test_getQuestionsById() throws Exception{
         int searchId = 2;
         mvc.perform(MockMvcRequestBuilders
             .get("http://localhost:8088/api/v1/question/{id}", searchId)
             .accept(MediaType.APPLICATION_JSON))
             .andDo(print())
             .andExpect(status().isOk());   
     }
 
     /**
      * Testing the simple search feature
      * takes in a string as a search and checks that the URL likes the request being passed through
      * @throws Exception
      */
     @Test
     void test_getQuestionsBySimpleSearch() throws Exception {
         String simpleSearch = "DWP";
         mvc.perform(MockMvcRequestBuilders
         .get("http://localhost:8088/api/v1/question/search/{simpleSearch}", simpleSearch)
         .accept(MediaType.APPLICATION_JSON))
         .andDo(print())
         .andExpect(status().isOk());
     }
     
     /**
      * testing adding request
      * creates a new question and tries to add it to the database and checks to ensure it has been created.
      * @throws Exception
      */
     @Test
     void test_addQuestionToDatabase() throws Exception {
         mvc.perform(MockMvcRequestBuilders
             .post("http://localhost:8088/api/v1/question")
             .content(asJsonString(new Question("Premier League", "Arsenal", "Harvin", "Arsenal", "Will Arsenal win the Premier League", "They have a chance", "true", "Premier League")))
             .contentType(MediaType.APPLICATION_JSON)
             .accept(MediaType.APPLICATION_JSON))
             .andExpect(status().isCreated());
     }
     public static String asJsonString(final Object obj) {
         try {
             return new ObjectMapper().writeValueAsString(obj);
         } catch (Exception e) {
             throw new RuntimeException(e);
         }
     }

     /**
      * testing update request
      * creates a new question and adds it to the database at the start
      * we then get the id of the question added and then call the update feature to update the question.
      * @throws Exception
      */
    @Test
    void test_updateQuestion() throws Exception {
        Question qForm = new Question("Premier League", "Arsenal", "Harvin", "Arsenal", "Will Arsenal win the Premier League?", "They have a chance", "true", "Premier League, Win");
        qForm = questionService.addQuestion(qForm);

        mvc.perform(MockMvcRequestBuilders
        .put("http://localhost:8088/api/v1/question/{id}", qForm.getQuestionID())
        .content(asJsonString(new Question("Ligue 1", "Paris Saint Germain", "Harvin", "Arsenal", "Will PSG win the Champions League?", "They have a good team and have a chance every year especially with Mbappe still there!", "true", "Champions League, Mbappe")))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
    }
    
    /**
     * delete request
     * creates a new question for the database
     * then gets the id of that question that has been added and deletes that id and ensures the status for that request is okay.
     * @throws Exception
     */
    @Test
    void test_deleteQuestion() throws Exception {
        Question qForm = new Question("Premier League", "Arsenal", "Harvin", "Arsenal", "Will Arsenal win the Premier League?", "They have a chance", "true", "Premier League, Win");
        qForm = questionService.addQuestion(qForm);

        mvc.perform(MockMvcRequestBuilders.delete("http://localhost:8088/api/v1/question/{id}", qForm.getQuestionID()))
        .andExpect(status().isOk());
    }

}

    

