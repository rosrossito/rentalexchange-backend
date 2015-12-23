package com.upteam.auth.component.emailgenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Skirdovs on 16.12.2015.
 */

public class EmailGeneratorConfirmRegistration implements EmailGenerator {

    private static final String EMAIL_TEMPLATE = "emailtemplates/registration-confirm.vm";
    private static final String subject = "Exchange Rental. Уведомление об активации учётной записи пользователя";
    private List<String> emailsTo = new ArrayList<String>();
    private String activateUserLink;

    public EmailGeneratorConfirmRegistration(String mailTo, String activateUserLink) {
        this.emailsTo.add(mailTo);
        this.activateUserLink = activateUserLink;
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
        model.put("activateUserLink", activateUserLink);
        model.put("userEmail", emailsTo.get(0));
        return model;
    }


}
