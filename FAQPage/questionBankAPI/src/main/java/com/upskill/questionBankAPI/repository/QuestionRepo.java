package com.upskill.questionBankAPI.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.upskill.questionBankAPI.model.Question;

/**
 * Question repository taking in the question model and id and holding the
 * queries that allow the filtering of questions
 */
public interface QuestionRepo extends JpaRepository<Question, Integer> {

	/**
	 * query to perform a search across all the fields in the database and retrieve
	 * the data that matches whether that be partial match or full match.
	 * 
	 * @param simpleSearch
	 * @return fields that match
	 */
	@Query("SELECT q FROM Question q WHERE lower(concat(q.league, q.footballTeam, q.askedBy, q.teamUserWhoAskedSupports, q.questionAsked, q.keywords, q.questionAnswer)) like lower (concat('%', :simpleSearch, '%'))")
	List<Question> findBySimpleSearch(String simpleSearch);

	/**
	 * query to search across only the footballTeam column of the database and retrieve
	 * data that matches the search inputted.
	 * 
	 * @param footballTeamSearch
	 * @return return footballTeams
	 */
	@Query("SELECT q FROM Question q WHERE q.footballTeam LIKE :footballTeamSearch")
	List<Question> findByfootballTeam(String footballTeamSearch);

	/**
	 * query to get distinct values of leagues for front end drop down menu usage.
	 */
	@Query("select distinct q.league from Question q")
	List<String> findLeagues();

	/**
	 * query to get distinct values of footballTeams for front end drop down menu usage.
	 */
	@Query("select distinct q.footballTeam from Question q")
	List<String> findFootballTeams();

	@Query("select distinct q.keywords from Question q")
	List<String> findKeywords();
}
