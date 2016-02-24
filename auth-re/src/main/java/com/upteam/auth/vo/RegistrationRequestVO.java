package com.upteam.auth.vo;


import org.codehaus.jackson.annotate.JsonProperty;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
/**
 * Created by opasichnyk on 11/25/2015.
 */
public class RegistrationRequestVO {

    @JsonProperty("email")
    @Size(min = 6, max = 80, message = "Email Field Length Is Incorrect [6;80]")
    //@NotNull( message = "EmailFieldIsNull")
    @NotEmpty( message = "Email Field Is Empty")
    @Pattern(regexp = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$", message = "Email Field Doesn't Match Email Pattern Or Contains Nonlatin Chars")
    //@Email( message = "Email Field Doesn't Match Email Pattern")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
