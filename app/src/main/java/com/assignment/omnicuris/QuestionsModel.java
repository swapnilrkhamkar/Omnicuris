package com.assignment.omnicuris;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;

public class QuestionsModel implements Serializable {

    private String question;
    private ArrayList<AnswersModel> answers;

    public QuestionsModel() {
    }

    public QuestionsModel(String question, ArrayList<AnswersModel> answers) {
        this.question = question;
        this.answers = answers;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public ArrayList<AnswersModel> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<AnswersModel> answers) {
        this.answers = answers;
    }

    @NonNull
    @Override
    public String toString() {
        return "QuestionsModel{" +
                "question='" + question + '\'' +
                ", answers=" + answers +
                '}';
    }
}
