package com.upteam.auth.vo;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by Администратор on 21.12.2015.
 */
public class ChangePasswordRequestVO {

    @JsonProperty("email")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
