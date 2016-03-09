package com.upteam.auth.vo;


import org.codehaus.jackson.annotate.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


/**
 * Created by Andrew on 12.01.2016.
 */
public class ChangePasswordVO {

@JsonProperty("password")
@NotEmpty(message = "EmptyPassword")
@Pattern(regexp="^\\S[A-Za-z0-9_\\W]+\\S$", message = "InvalidPasswordFormat")
@Size(min = 8, max = 20, message = "InvalidPasswordLength")
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
