package com.upskill.questionBankAPI.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.upskill.questionBankAPI.model.Question;
import com.upskill.questionBankAPI.service.QuestionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

/**
 * controller that holds all the http requests and the url's that correspond to
 * each method.
 */
@RestController
@RequestMapping("api/v1/question")
@CrossOrigin(origins = "http://localhost:3000")

public class QuestionController {

	/**
	 * retrieving the information from question service class.
	 */
	@Autowired
	private QuestionService questionService;

	@Operation(summary = "Find and returns all the questions in the database.", description = "Looks through the database for all the questions inside it and returning them as a response.", method = "GET", responses = {
			@ApiResponse(responseCode = "200", description = "All questions are retrieved that are expected and outputted as a JSON in the body of the response.", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE) }),
			@ApiResponse(responseCode = "404", description = "Questions are not retrieved from the database and nothing is returned.", content = {
					@Content(mediaType = MediaType.TEXT_PLAIN_VALUE) }),
	})
	/**
	 * returns a list of all questions, this is the landing page. Uses the method
	 * find all questions from the question service.
	 * 
	 * @return
	 */
	@GetMapping
	public List<Question> getQuestions() {
		return questionService.findAllQuestions();
	}

	@Operation(summary = "Finds and returns a list of questions by id", description = "Looks through the database to find the question matched with the id value passed through.", method = "GET", responses = {
			@ApiResponse(responseCode = "200", description = "Question is retrieved successfully and returned as a JSON object in the body of the response.", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE) }),
			@ApiResponse(responseCode = "404", description = "Question is not found with the given id value.", content = {
					@Content(mediaType = MediaType.TEXT_PLAIN_VALUE) }),
	})
	/**
	 * get question by id method from question service taking in the id specifed.
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("{id}")
	public ResponseEntity<Question> getQuestionById(@PathVariable int id) {
		return ResponseEntity.status(HttpStatus.OK).body(questionService.getQuestionById(id));
	}

	@Operation(summary = "Searches and returns a list of questions by the value the user inputs.", description = "Looks through the database through all the fields within the database to find the questions matching with the value passed through.", method = "GET", responses = {
			@ApiResponse(responseCode = "200", description = "Questions are retrieved successfully and returned as a JSON object in the body of the response.", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE) }),
			@ApiResponse(responseCode = "404", description = "Question is not found with the given value.", content = {
					@Content(mediaType = MediaType.TEXT_PLAIN_VALUE) }),
	})
	/**
	 * simple search functionality following the url provided, takes in the
	 * parameters and returns matching values.
	 * 
	 * @param simpleSearch
	 * @return
	 */
	@GetMapping("/search/{simpleSearch}")
	public ResponseEntity<List<Question>> getQuestionsBySimpleSearch(@PathVariable String simpleSearch) {
		return ResponseEntity.status(HttpStatus.OK).body(questionService.getQuestionsBySimpleSearch(simpleSearch));
	}

	@Operation(summary = "Creates a new question in the database", description = "Accepts and validates a question object as the body of the request and then saves the question to the database", method = "POST", responses = {
			@ApiResponse(responseCode = "201", description = "Question is created and added to the database.", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE) }),
			@ApiResponse(responseCode = "409", description = "Question already present in the database.", content = {
					@Content(mediaType = MediaType.TEXT_PLAIN_VALUE) }),
			@ApiResponse(responseCode = "400", description = "Question supplied is invalid, returns CSV string containing error messages", content = {
					@Content(mediaType = MediaType.TEXT_PLAIN_VALUE) })
	})
	/**
	 * add question method from the question service, when creating a new question
	 * it expands upon the unique id to the next number in the sequence.
	 * 
	 * @param question
	 * @return
	 */
	@PostMapping
	public ResponseEntity<Question> addQuestion(@Valid @RequestBody Question question) {
		question = questionService.addQuestion(question);
		URI locationURI = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}")
				.buildAndExpand(question.getQuestionID())
				.toUri();

		return ResponseEntity.created(locationURI).body(question);
	}

	@Operation(summary = "Updates an existing question", description = "Validates the question, then checks if question exists with given id, will update the question with the new information inputted.", method = "PUT", responses = {
			@ApiResponse(responseCode = "200", description = "Question is updated", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE) }),
			@ApiResponse(responseCode = "409", description = "Question already present in the database, conflicting with another question in the database.", content = {
					@Content(mediaType = MediaType.TEXT_PLAIN_VALUE) }),
			@ApiResponse(responseCode = "400", description = "Question supplied is invalid, returns CSV string containing error messages", content = {
					@Content(mediaType = MediaType.TEXT_PLAIN_VALUE) }),
			@ApiResponse(responseCode = "404", description = "Question not found.", content = {
					@Content(mediaType = MediaType.TEXT_PLAIN_VALUE) })
	})
	/**
	 * update question from the question service.
	 * 
	 * @param id
	 * @param question
	 * @return
	 */
	@PutMapping("{id}")
	public ResponseEntity<Question> updateQuestion(@PathVariable int id, @Valid @RequestBody Question question) {
		return ResponseEntity.ok(questionService.updateQuestion(question, id));
	}

	@Operation(summary = "Deletes a existing question", description = "Checks to see if there is a question with the given id, if so will then delete it from the database.", method = "DELETE", responses = {
			@ApiResponse(responseCode = "200", description = "Question is deleted.", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE) }),
			@ApiResponse(responseCode = "404", description = "Question with given id is not found.", content = {
					@Content(mediaType = MediaType.TEXT_PLAIN_VALUE) })
	})
	/**
	 * delete function from the question service, taking in the parameter id.
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("{id}")
	public ResponseEntity<Void> deleteQuestion(@PathVariable int id) {
		questionService.deleteQuestionById(id);
		return ResponseEntity.ok().build();
	}

	@Operation(summary = "Gets all distinct values of Leagues", description = "Gets all unique values of Leagues within the database and passes them as strings for the dropdown in the front-end.", method = "GET", responses = {
			@ApiResponse(responseCode = "200", description = "All unique Leagues are outputted as strings for front end usage.", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE) }),
			@ApiResponse(responseCode = "404", description = "Unique Leagues are not outputted in the body of the response.", content = {
					@Content(mediaType = MediaType.TEXT_PLAIN_VALUE) })
	})
	/**
	 * repo asked for distinct values of the column and passes as list of strings
	 * for drop down front-end
	 * 
	 * @return distinct Leagues.
	 */
	@GetMapping("/League")
	public List<String> getLeagues() {
		return questionService.findAllLeagues();
	}

	@Operation(summary = "Gets all distinct values of FootballTeams", description = "Gets all unique values of FootballTeams within the database and passes them as strings for the dropdown in the front-end.", method = "GET", responses = {
			@ApiResponse(responseCode = "200", description = "All unique FootballTeams are outputted as strings for front end usage.", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE) }),
			@ApiResponse(responseCode = "404", description = "Unique FootballTeams are not outputted in the body of the response.", content = {
					@Content(mediaType = MediaType.TEXT_PLAIN_VALUE) })
	})
	/**
	 * repo asked for distinct values of the column and passes as list of strings
	 * for drop down front-end
	 * 
	 * @return distinct FootballTeams.
	 */
	@GetMapping("/FootballTeam")
	public List<String> getFootballTeams() {
		return questionService.findAllFootballTeams();
	}

	@Operation(summary = "Gets all distinct values of keywords", description = "Gets all unique values of keywords within the database and passes them as strings for the dropdown in the front-end.", method = "GET", responses = {
		@ApiResponse(responseCode = "200", description = "All unique keywords are outputted as strings for front end usage.", content = {
				@Content(mediaType = MediaType.APPLICATION_JSON_VALUE) }),
		@ApiResponse(responseCode = "404", description = "Unique keywords are not outputted in the body of the response.", content = {
				@Content(mediaType = MediaType.TEXT_PLAIN_VALUE) })
})
/**
 * repo asked for distinct values of the column and passes as list of strings
 * for drop down front-end
 * 
 * @return distinct keywords
 */
@GetMapping("/keyword")
public List<String> getKeywords() {
	return questionService.findAllKeywords();
}
}
