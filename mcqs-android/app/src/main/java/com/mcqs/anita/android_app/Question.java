package com.mcqs.anita.android_app;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;
import com.bluelinelabs.logansquare.annotation.OnJsonParseComplete;
import com.bluelinelabs.logansquare.annotation.OnPreJsonSerialize;

@JsonObject
public class Question {

    @JsonField(name = "options")
    private QuestionOptions[] questionOptions = new QuestionOptions[5];

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
    private String[] images;

    @JsonField
    private int questionId;

    private boolean imagesDownloaded;

    private boolean questionAnswered;

    private boolean answeredCorrectly;

    public Question() {
    }

    public Question(QuestionOptions[] questionOptions, String background, String question, String core, String explanation) {
        this.questionOptions = questionOptions;
        this.background = background;
        this.question = question;
        this.core = core;
        this.explanation = explanation;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    public QuestionOptions[] getQuestionOptions() {
        return questionOptions;
    }

    public void setQuestionOptions(QuestionOptions[] questionOptions) {
        this.questionOptions = questionOptions;
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
