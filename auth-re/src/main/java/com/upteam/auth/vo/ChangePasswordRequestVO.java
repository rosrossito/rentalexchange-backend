package com.upteam.auth.vo;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by Администратор on 21.12.2015.
 */
public class ChangePasswordRequestVO {

    @JsonProperty("login")
    private String login;

    @JsonProperty("email")
    private String email;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
