package com.upteam.auth.vo;


import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by opasichnyk on 11/25/2015.
 */
public class RegistrationRequestVO {

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
