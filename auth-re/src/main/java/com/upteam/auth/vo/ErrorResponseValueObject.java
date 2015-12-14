package com.upteam.auth.vo;

import org.codehaus.jackson.annotate.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

/**
 * Created by opasichnyk on 12/8/2015.
 */
public class ErrorResponseValueObject {

    @JsonProperty("reason")
    private String reason;

    @JsonProperty("timestamp")
    private LocalDate timeStamp;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDate getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDate timeStamp) {
        this.timeStamp = timeStamp;
    }

    // Define the logger object for this class
    private final Logger log = LoggerFactory.getLogger(this.getClass());

}
