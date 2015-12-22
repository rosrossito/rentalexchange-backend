package com.upteam.auth.component.emailgenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Влад on 11.12.2015.
 */
public class EmailGeneratorRegistration implements EmailGenerator {

    private static final String EMAIL_TEMPLATE="com/temp.vm";

    private String mailTo;
    private String registrationConfirmLink;
    private String mailTo = "yeapless@gmail.com";
    private String registrationConfirmLink = "http:rental-exchange:8080/sfsfsf-sfs-fs-f-sf-sf-s-fsfDA-DA";
    private final String subject = "Exchange Rental. Инструкции по активации учётной записи пользователя";
    /* private final String text = "Здравствуйте! \n"
             + "На сайте Rentalexchange.com была выполнена регистрация учётной записи пользователя с указанием адреса " + mailTo + ". \n\n"
             + "Если Вы случайно получили это письмо, пожалуйста, проигнорируйте его. \n\n"
             + "Для активации учётной записи пользователя перейдите по " + registrationConfirmLink + " этой ссылке. \n"
             + "(или откройте в интернет-браузере ссылку " + registrationConfirmLink + ")\n\n"
             + "Если у вас возникли вопросы, пишите в <a href=mailto:[%e-mail поддержки%]>службу поддержки</a> по адресу [%e-mail поддержки%]. \n\n"
             + "Пожалуйста, не отвечайте на данное письмо. \n\n"
             + "С уважением, Ваш <a href=[%адрес площадки%]>[%Название площадки%]</a>.";*/
    private final String sendFrom = "exchange.rental.info@gmail.com";

    private List<String> emailsTo = new ArrayList<String>();
    private SystemUser systemUser;

    public EmailGeneratorRegistration(String mailTo, String registrationConfirmLink) {
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
        return text;
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
        return null;
    }

}
