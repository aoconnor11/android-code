package com.mcqs.anita.android_app;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

@JsonObject
public class UserDetails {

    @JsonField
    private int currentUserId;

    @JsonField
    private int currentExamId;

    @JsonField
    private double currentDifficultyLevel;

    @JsonField
    private int defaultExam;

    @JsonField
    private String apiRoute;

    public UserDetails() {
    }

    public UserDetails(int currentUserId, int currentExamId, double currentDifficultyLevel) {
        this.currentUserId = currentUserId;
        this.currentExamId = currentExamId;
        this.currentDifficultyLevel = currentDifficultyLevel;
    }

    public String getApiRoute() {
        return apiRoute;
    }

    public void setApiRoute(String apiRoute) {
        this.apiRoute = apiRoute;
    }

    public int getDefaultExam() {
        return defaultExam;
    }

    public void setDefaultExam(int defaultExam) {
        this.defaultExam = defaultExam;
    }

    public int getCurrentUserId() {
        return currentUserId;
    }

    public void setCurrentUserId(int currentUserId) {
        this.currentUserId = currentUserId;
    }

    public int getCurrentExamId() {
        return currentExamId;
    }

    public void setCurrentExamId(int currentExamId) {
        this.currentExamId = currentExamId;
    }

    public double getCurrentDifficultyLevel() {
        return currentDifficultyLevel;
    }

    public void setCurrentDifficultyLevel(double currentDifficultyLevel) {
        this.currentDifficultyLevel = currentDifficultyLevel;
    }
}
