package com.upskill.questionBankAPI.exception;

/**
 * custom exception for usage within the service components.
 */
public class NoQuestionFoundException extends RuntimeException {

    public NoQuestionFoundException(String message) {
        super(message);
    }
    
    
}

