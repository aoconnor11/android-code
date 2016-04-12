package com.mcqs.anita.android_app;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

@JsonObject
public class QuestionOptions {

    @JsonField
    private boolean correctAnswer;

    @JsonField
    private String answer;

    public QuestionOptions() {
    }

    public QuestionOptions(String answer, boolean correctAnswer) {
        this.answer = answer;
        this.correctAnswer = correctAnswer;
    }

    public QuestionOptions(boolean correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public boolean isCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(boolean correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
