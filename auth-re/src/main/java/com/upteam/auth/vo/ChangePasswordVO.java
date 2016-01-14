package com.upteam.auth.vo;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by Andrew on 12.01.2016.
 */
public class ChangePasswordVO {

    @JsonProperty("password")
    private String password;

    @JsonProperty("uuid")
    private String uuid;

    public String getPassword() {
        return password;
    }

    public String getUuid() {
        return uuid;
    }
}
