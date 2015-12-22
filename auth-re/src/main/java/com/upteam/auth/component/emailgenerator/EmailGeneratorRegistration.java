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

    private static final String EMAIL_TEMPLATE = "emailtemplates/registration.vm";

    private static final String subject = "Exchange Rental. Инструкции по активации учётной записи пользователя";

    private List<String> emailsTo = new ArrayList<String>();
    private String registrationConfirmLink;
    private SystemUser systemUser;

    public EmailGeneratorRegistration(String mailTo, String registrationConfirmLink, SystemUser systemUser) {
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
    public String getTemplate() {
        return EMAIL_TEMPLATE;
    }

    @Override
    public Map<String, Object> getModel() {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("registrationConfirmLink", registrationConfirmLink);
        model.put("userEmail", emailsTo.get(0));
        return model;
    }

}
