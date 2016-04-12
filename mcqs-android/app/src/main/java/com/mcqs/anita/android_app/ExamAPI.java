package com.mcqs.anita.android_app;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

@JsonObject
public class ExamAPI {

    @JsonField
    private int examId;

    @JsonField
    private String examName;

    @JsonField
    private String examDesc;

    @JsonField
    private int categoryId;

    public ExamAPI() {
    }

    public ExamAPI(int examId, String examName, String examDesc, int categoryId) {
        this.examId = examId;
        this.examName = examName;
        this.examDesc = examDesc;
        this.categoryId = categoryId;
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getExamDesc() {
        return examDesc;
    }

    public void setExamDesc(String examDesc) {
        this.examDesc = examDesc;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
