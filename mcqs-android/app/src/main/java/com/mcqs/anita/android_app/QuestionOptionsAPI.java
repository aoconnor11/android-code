package com.mcqs.anita.android_app;

import com.bluelinelabs.logansquare.annotation.JsonObject;

import java.util.HashMap;

@JsonObject
public class QuestionOptionsAPI {

    private HashMap<String, String> options;

    public QuestionOptionsAPI() {
    }

    public HashMap<String, String> getOptions() {
        return options;
    }

    public void setOptions(HashMap<String, String> options) {
        this.options = options;
    }
}
