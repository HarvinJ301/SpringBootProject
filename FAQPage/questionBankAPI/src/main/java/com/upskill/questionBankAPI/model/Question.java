package com.upskill.questionBankAPI.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Question entity to hold the questions and parameters for front-end.
 */
@Entity
@Table(name="QUESTION")
public class Question {
	
	/**
	 * question entity unique Id
	 */
	@Id
	@Column(name="question_id")
	@SequenceGenerator(name = "question_id_gen", sequenceName = "question_id_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "question_id_gen")
	private int questionID;

	/**
	 * The leagues available for users, with constraints on what can be passed in.
	 */
	@Column
	@Pattern(regexp = "^[a-zA-Z0-9(). -]*$")
	@NotBlank(message = "League must not be blank")
	private String league;
	
	/**
	 * Field for football teams that the question database can hold with constraints on what can be passed in.
	 */
	@Column
	@Pattern(regexp = "^[a-zA-Z0-9().' -]*$")
	@Size(max = 50, message = "Football Team must not be greater than 50 characters.")
	private String footballTeam;
	
	/**
	 * Field for asked by that get added to the question bank, with constraints on what excatly can be passed through.
	 */
	@Column
	@Pattern(regexp = "^[a-zA-Z0-9~@&#$^*()_`'’+={}|,.!?/: -]*$")
	@NotBlank(message = "Asked By must not be blank")
	@Size(min =2, max = 30, message = "Asked By must not be greater than 30 characters.")
	private String askedBy;
	
	/**
	 * Field for who you support within the database question bank, with constraints on values passed through.
	 */
	@Column
	@Pattern(regexp = "^[a-zA-Z0-9 -]*$")
	@NotBlank(message = "Who you support must not be blank")
	@Size(min =2, max = 50, message = "Who you support must not be greater than 50 characters.")
	private String teamUserWhoAskedSupports;
	
	/**
	 * Field for Question asked within the database question bank, with constraints on values passed through.
	 */
	@Column
	@Pattern(regexp = "^[a-zA-Z0-9~@&#$^*()_`'’+={}|,.!?/: -]*$")
	@NotBlank(message = "Question asked must not be blank")
	@Size(min =2, max = 255, message = "Question asked must not be greater than 200 characters.")
	private String questionAsked;

	/**
	 * Field for question answer within the database question bank, with constraints on values passed through.
	 */
	@Column
	@Pattern(regexp = "^[a-zA-Z0-9~@&#$^*()_`'’+={}|,.!?/: -]*$")
	@Size(max = 500, message = "Answer must not be greater than 500 characters")
	private String questionAnswer;

	/**
	 * Field to mark whether a question has been answered by a fan.
	 */
	@Column
	private String answered;

	/**
	 * Field for tools and languages(keywords) it self within the database question bank, with constraints on values passed through.
	 */
	@Column
	@Pattern(regexp = "^[a-zA-Z0-9 -]*$")
	@Size(max = 50, message = "Keywords must not be greater than 50 characters.")
	private String keywords;

	public Question () {}

	
	public Question( String league,String footballTeam, String askedBy,String teamUserWhoAskedSupports,String questionAsked,String questionAnswer,String answered,
		String keywords) {
		this.league = league;
		this.footballTeam = footballTeam;
		this.askedBy = askedBy;
		this.teamUserWhoAskedSupports = teamUserWhoAskedSupports;
		this.questionAsked = questionAsked;
		this.questionAnswer = questionAnswer;
		this.answered = answered;
		this.keywords = keywords;
	}

	
	@Override
	public String toString() {
		return "Question [questionID=" + questionID + ", league=" + league + ", footballTeam=" + footballTeam
				+ ", askedBy=" + askedBy + ", teamUserWhoAskedSupports=" + teamUserWhoAskedSupports + ", questionAsked="
				+ questionAsked + ", questionAnswer=" + questionAnswer + ", answered=" + answered + ", keywords="
				+ keywords + "]";
	}

	public int getQuestionID() {
		return questionID;
	}

	public void setQuestionID(int questionID) {
		this.questionID = questionID;
	}

	public String getLeague() {
		return league;
	}

	public void setLeague(String league) {
		this.league = league;
	}

	public String getFootballTeam() {
		return footballTeam;
	}

	public void setFootballTeam(String footballTeam) {
		this.footballTeam = footballTeam;
	}

	public String getAskedBy() {
		return askedBy;
	}

	public void setAskedBy(String askedBy) {
		this.askedBy = askedBy;
	}

	public String getTeamUserWhoAskedSupports() {
		return teamUserWhoAskedSupports;
	}

	public void setTeamUserWhoAskedSupports(String teamUserWhoAskedSupports) {
		this.teamUserWhoAskedSupports = teamUserWhoAskedSupports;
	}

	public String getQuestionAsked() {
		return questionAsked;
	}

	public void setQuestionAsked(String questionAsked) {
		this.questionAsked = questionAsked;
	}

	public String getQuestionAnswer() {
		return questionAnswer;
	}

	public void setQuestionAnswer(String questionAnswer) {
		this.questionAnswer = questionAnswer;
	}

	public String getAnswered() {
		return answered;
	}

	public void setAnswered(String answered) {
		this.answered = answered;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	

}
