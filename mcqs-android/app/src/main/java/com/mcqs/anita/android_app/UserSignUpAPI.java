package com.mcqs.anita.android_app;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

@JsonObject
public class UserSignUpAPI {

    @JsonField
    private boolean result;

    @JsonField
    private String[] username;

    @JsonField
    private String[] email;

    public UserSignUpAPI() {
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String[] getUsername() {
        return username;
    }

    public void setUsername(String[] username) {
        this.username = username;
    }

    public String[] getEmail() {
        return email;
    }

    public void setEmail(String[] email) {
        this.email = email;
    }
}
