package com.stackroute.service;

import com.stackroute.domain.Answer;
import com.stackroute.domain.Comment;
import com.stackroute.domain.Question;
import com.stackroute.domain.Replies;
import com.stackroute.exceptions.QuestionAlreadyExistsException;
import com.stackroute.exceptions.QuestionNotFoundException;

public interface QuestionService {
    public Question saveQuestion(Question questionObject) throws QuestionAlreadyExistsException;
    public Question addAnswer(int questionId,Answer answer) throws QuestionNotFoundException;
    public Question addQuestionComment(int questionId, Comment comment) throws QuestionAlreadyExistsException;
    public Question addQuestionCommentReply(int questionId, String comment, Replies replies);
}
