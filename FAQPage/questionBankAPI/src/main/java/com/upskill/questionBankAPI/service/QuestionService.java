package com.upskill.questionBankAPI.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upskill.questionBankAPI.exception.NoQuestionFoundException;
import com.upskill.questionBankAPI.model.Question;
import com.upskill.questionBankAPI.repository.QuestionRepo;

/**
 * Question Service that holds all the methods and functionality from the
 * Question Repo.
 */
@Service
public class QuestionService {

	/**
	 * retrieving the question repo class
	 */
	@Autowired
	private QuestionRepo questionRepo;

	/**
	 * method to find all questions using the in-built method within the repo.
	 * 
	 * @return all questions
	 */
	public List<Question> findAllQuestions() {
		return questionRepo.findAll();
	}

	/**
	 * method to get question by id, using a repo method of find by id.
	 * if the id is incorrect or not retrievable then an error is shown.
	 * 
	 * @param id
	 * @return
	 */
	public Question getQuestionById(int id) {
		Optional<Question> questionOpt = questionRepo.findById(id);
		if (questionOpt.isEmpty()) {
			throw new NoQuestionFoundException(getNotFoundMessage(id));
		}
		return questionOpt.get();
	}

	/**
	 * Method to do the simple search query found in the question repo.
	 * 
	 * @param simpleSearch
	 * @return questions that match the search value
	 */
	public List<Question> getQuestionsBySimpleSearch(String simpleSearch) {
		List<Question> questions = questionRepo.findBySimpleSearch(simpleSearch);
		if (questions.isEmpty()) {
			System.err.println("No records in the database relating to" + simpleSearch);
		}
		return questions;
	}

	/**
	 * method to add a question to the database using repo built in method to save
	 * the parameter we pass through which is a question model.
	 * 
	 * @param question
	 * @return
	 */
	public Question addQuestion(Question question) {
		return questionRepo.save(question);
	}

	/**
	 * method to update a question already within the database. Take in the two
	 * parameters and set the question parameter to an id.
	 * if there is no question then there is an exception thrown.
	 * 
	 * @param question
	 * @param id
	 * @return
	 */
	public Question updateQuestion(Question question, int id) {
		Optional<Question> existingQuestion = questionRepo.findById(id);
		if (existingQuestion.isEmpty()) {
			throw new NoQuestionFoundException(getNotFoundMessage(id));
		}
		question.setQuestionID(id);
		return questionRepo.save(question);
	}

	/**
	 * method to delete the question by the indivudial question id.
	 * if there is no question then the exception is thrown.
	 * 
	 * @param id
	 */
	public void deleteQuestionById(int id) {
		Optional<Question> existingQuestion = questionRepo.findById(id);
		if (existingQuestion.isEmpty()) {
			throw new NoQuestionFoundException(getNotFoundMessage(id));
		}

		questionRepo.deleteById(id);
	}

	/**
	 * returns a list of distinct values of Leagues.
	 * 
	 * @return
	 */
	public List<String> findAllLeagues() {
		return questionRepo.findLeagues();
	}

	/**
	 * returns a list of distinct values of footballTeams.
	 * 
	 * @return
	 */
	public List<String> findAllFootballTeams() {
		return questionRepo.findFootballTeams();
	}

	/**
	 * returns a list of distinct values for Keywords.
	 * @return
	 */
	public List<String> findAllKeywords(){
		return questionRepo.findKeywords();
	}
	/**
	 * method to be used with the no question exception to stop reoccuring code.
	 * 
	 * @param id
	 * @return
	 */
	private String getNotFoundMessage(int id) {
		return "There is no question associated with this id: " + id;
	}

}
