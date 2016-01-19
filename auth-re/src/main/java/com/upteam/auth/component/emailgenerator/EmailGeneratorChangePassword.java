package com.upteam.auth.component.emailgenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Andrew on 17.01.2016.
 */
public class EmailGeneratorChangePassword implements EmailGenerator  {

    private static final String EMAIL_TEMPLATE = "emailtemplates/change-password-confirm.vm";
    private static final String subject = "Exchange Rental. Уведомление о смене пароля.";
    private List<String> emailsTo = new ArrayList<String>();
    private String activateUserLink;

    public EmailGeneratorChangePassword(String mailTo){
        this.emailsTo.add(mailTo);
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
        model.put("userEmail", emailsTo.get(0));
        return model;
    }
}
