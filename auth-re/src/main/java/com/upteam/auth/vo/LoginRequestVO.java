package com.upteam.auth.vo;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by Влад on 22.12.2015.
 */
public class LoginRequestVO {
    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
