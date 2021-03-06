package com.upteam.auth.vo;


import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by opasichnyk on 11/25/2015.
 */
public class UserInfoVO {

    @JsonProperty("login")
    private String avatar;

    @JsonProperty("email")
    private String email;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
