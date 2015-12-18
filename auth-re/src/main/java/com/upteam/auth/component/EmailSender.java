package com.upteam.auth.component;

import com.upteam.auth.component.emailgenerator.EmailGenerator;

/**
 * Created by opasichnyk on 12/4/2015.
 */
public interface EmailSender {
    void sendEmail(EmailGenerator generator);
    void sendEmail(EmailGenerator generator, String encoding);

}
