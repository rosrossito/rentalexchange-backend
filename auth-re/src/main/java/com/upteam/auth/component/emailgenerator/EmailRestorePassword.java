package com.upteam.auth.component.emailgenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Администратор on 22.12.2015.
 */
public class EmailRestorePassword implements EmailGenerator {

    private static final String EMAIL_TEMPLATE = "emailtemplates/restorePassword.vm";
    private final String subject = "Exchange Rental. Инструкции по восстановлению пароля пользователя";
    private List<String> emailsTo = new ArrayList<String>();
    private String restorePasswordLink;

    public EmailRestorePassword(String mailTo, String restorePasswordLink) {
        this.emailsTo.add(mailTo);
        this.restorePasswordLink = restorePasswordLink;
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
        model.put("restorePasswordLink", restorePasswordLink);
        model.put("userEmail", emailsTo.get(0));
        return model;
    }



}
