package com.upteam.auth.vo;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author by opasichnyk
 *         created: 2/24/2016.
 */
public class SuccessResponseVO {

    @JsonProperty("message")
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
