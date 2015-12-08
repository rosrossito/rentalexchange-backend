package com.upteam.auth.vo;

import java.time.LocalDate;

/**
 * Created by opasichnyk on 12/8/2015.
 */
public class ErrorResponseValueObject {

    private String reason;
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
}
