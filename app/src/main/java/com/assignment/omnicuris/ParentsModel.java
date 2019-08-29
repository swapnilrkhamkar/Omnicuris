package com.assignment.omnicuris;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;

public class ParentsModel implements Serializable {

    private ArrayList<QuestionsModel> questions;

    public ParentsModel(ArrayList<QuestionsModel> questions) {
        this.questions = questions;
    }

    public ParentsModel() {
    }

    public ArrayList<QuestionsModel> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<QuestionsModel> questions) {
        this.questions = questions;
    }

    @NonNull
    @Override
    public String toString() {
        return "ParentsModel{" +
                "questions=" + questions +
                '}';
    }
}
