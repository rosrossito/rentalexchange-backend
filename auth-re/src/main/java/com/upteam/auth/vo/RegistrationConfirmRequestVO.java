package com.upteam.auth.vo;


import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by opasichnyk on 11/25/2015.
 */
public class RegistrationConfirmRequestVO {

    @JsonProperty("password")
    private String password;

    @JsonProperty("uuid")
    private String uuid;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

}
