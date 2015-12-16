package com.upteam.auth.component;

/**
 * Created by opasichnyk on 12/4/2015.
 */
public interface EmailSender {
    void sendEmail(EmailGenerator generator);
    void sendEmail(EmailGenerator generator, String encoding);

}
