package com.upteam.auth.component;

import com.upteam.auth.domain.ActivationLink;

/**
 * Created by opasichnyk on 12/4/2015.
 */
public interface EmailSender {
    void sendEmail(EmailGenerator generator);
    void sendEmail(ActivationLink activationLink);
}
