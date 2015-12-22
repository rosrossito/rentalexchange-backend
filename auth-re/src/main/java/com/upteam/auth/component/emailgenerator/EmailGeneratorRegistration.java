package com.upteam.auth.component.emailgenerator;

import com.upteam.auth.domain.SystemUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Влад on 11.12.2015.
 */
public class EmailGeneratorRegistration implements EmailGenerator {

    private static final String EMAIL_TEMPLATE = "com/registration.vm";

    private String mailTo;
    private String registrationConfirmLink;
    private final String subject = "Exchange Rental. Инструкции по активации учётной записи пользователя";
    private final String sendFrom = "exchange.rental.info@gmail.com";

    private List<String> emailsTo = new ArrayList<String>();

    private SystemUser systemUser;

    public EmailGeneratorRegistration(String mailTo, String registrationConfirmLink, SystemUser systemUser) {
        this.mailTo = mailTo;
        this.emailsTo.add(mailTo);
        this.registrationConfirmLink = registrationConfirmLink;
        this.systemUser = systemUser;
    }


    @Override
    public List<String> getEmailsTo() {
        return emailsTo;
    }

    @Override
    public String getSubject() {
        return subject;
    }

    @Override
    public String getText() {
        return "text";
    }

    @Override
    public String getFrom() {
        return sendFrom;
    }

    @Override
    public String getTemplate() {
        return EMAIL_TEMPLATE;
    }

    @Override
    public Map getModel() {
        Map model = new HashMap();
        model.put("registrationConfirmLink",registrationConfirmLink);
        model.put("userEmail", mailTo);
        return model;
    }

}
