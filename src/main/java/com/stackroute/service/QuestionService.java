package com.stackroute.service;

import com.stackroute.domain.Answer;
import com.stackroute.domain.Comment;
import com.stackroute.domain.Question;
import com.stackroute.exceptions.QuestionAlreadyExistsException;
import com.stackroute.exceptions.QuestionNotFoundException;

public interface QuestionService {
    public Question saveQuestion(Question questionObject) throws QuestionAlreadyExistsException;
    public Question addAnswer(String question,Answer answer) throws QuestionNotFoundException;
    public Question addQuestionComment(String question, Comment comment) throws QuestionAlreadyExistsException;
}
