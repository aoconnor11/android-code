package com.mcqs.anita.android_app;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

@JsonObject
public class ResetPasswordJSONAPI {

    @JsonField
    private String username;

    public ResetPasswordJSONAPI() {
    }

    public ResetPasswordJSONAPI(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
