package com.mcqs.anita.android_app;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;
import com.bluelinelabs.logansquare.annotation.OnJsonParseComplete;
import com.bluelinelabs.logansquare.annotation.OnPreJsonSerialize;

import java.util.HashMap;

@JsonObject
public class QuestionAPI {

    @JsonField
    private int correctAnswer;

    @JsonField
    private HashMap<String, String> options;

    @JsonField
    private HashMap<String, String> correctAnswers;

    @JsonField
    private HashMap<String, String> incorrectAnswers;

    @JsonField
    private String background;

    @JsonField
    private String question;

    @JsonField
    private String core;

    @JsonField
    private String explanation;

    private int index;

    @JsonField
    private HashMap<String, String> images = new HashMap<>();

    @JsonField
    private int questionId;

    private boolean imagesDownloaded;

    private boolean questionAnswered;

    private boolean answeredCorrectly;

    public QuestionAPI() {
    }

    public QuestionAPI(String background, String question, String core, String explanation) {
        this.background = background;
        this.question = question;
        this.core = core;
        this.explanation = explanation;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public HashMap<String, String> getOptions() {
        return options;
    }

    public void setOptions(HashMap<String, String> options) {
        this.options = options;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public HashMap<String, String> getIncorrectAnswers() {
        return incorrectAnswers;
    }

    public void setIncorrectAnswers(HashMap<String, String> incorrectAnswers) {
        this.incorrectAnswers = incorrectAnswers;
    }

    public HashMap<String, String> getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(HashMap<String, String> correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public HashMap<String, String> getImages() {
        return images;
    }

    public void setImages(HashMap<String, String> images) {
        this.images = images;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCore() {
        return core;
    }

    public void setCore(String core) {
        this.core = core;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public boolean isImagesDownloaded() {
        return imagesDownloaded;
    }

    public void setImagesDownloaded(boolean imagesDownloaded) {
        this.imagesDownloaded = imagesDownloaded;
    }

    public boolean isQuestionAnswered() {
        return questionAnswered;
    }

    public void setQuestionAnswered(boolean questionAnswered) {
        this.questionAnswered = questionAnswered;
    }

    public boolean isAnsweredCorrectly() {
        return answeredCorrectly;
    }

    public void setAnsweredCorrectly(boolean answeredCorrectly) {
        this.answeredCorrectly = answeredCorrectly;
    }

    @OnJsonParseComplete
    void onParseComplete() {
    }

    @OnPreJsonSerialize
    void onPreSerialize() {
    }
}
