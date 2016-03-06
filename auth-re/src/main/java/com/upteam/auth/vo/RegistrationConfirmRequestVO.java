package com.upteam.auth.vo;


import org.codehaus.jackson.annotate.JsonProperty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;


/**
 * Created by opasichnyk on 11/25/2015.
 */
public class RegistrationConfirmRequestVO {

    @JsonProperty("password")
    @NotEmpty(message = "EmptyPassword")
    @Length(min = 8, max = 20, message = "InvalidPasswordLength")
    //TODO need fix
    //@Pattern(regexp = "((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$", message = "InvalidPasswordFormat")
    private String password;

    @JsonProperty("uuid")
    @NotEmpty(message = "EmptyUuid")
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
