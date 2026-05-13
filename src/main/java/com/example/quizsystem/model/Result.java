package com.example.quizsystem.model;

public class Result {
    public String studentEmail;
    public int score;
    public int totalQuestions;

    public Result(String studentEmail,int score,int totalQuestions){
        this.studentEmail=studentEmail;
        this.score=score;
        this.totalQuestions=totalQuestions;
    }
}
