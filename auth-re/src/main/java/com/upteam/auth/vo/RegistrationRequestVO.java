package com.upteam.auth.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by opasichnyk on 11/25/2015.
 */
public class RegistrationRequestVO {

    @JsonProperty("email")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
