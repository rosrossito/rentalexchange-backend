package com.upteam.auth.component.emailgenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Skirdovs on 16.12.2015.
 */

public class EmailGeneratorConfirmRegistration implements EmailGenerator {

    private String mailTo;
    private String sendFrom;
    private String subject;
    private String text;
    private List<String> emailsTo;


    public EmailGeneratorConfirmRegistration(String mailTo) {
        this.emailsTo = new ArrayList<String>();
        this.emailsTo.add(mailTo);
        this.mailTo = mailTo;
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
        return null;
    }

    @Override
    public Map getModel() {
        return null;
    }

    public void prepareMail(String supportMail, String host, String port, String exchangeRental) {
        subject = exchangeRental + ". Уведомление об активации учётной записи пользователя";
        text = "Здравствуйте!\n" +
                " \n" +
                "Учётная запись пользователя с данным e-mail " + mailTo + " активирована.\n" +
                " \n" +
                "Для входа в личный кабинет перейдите по <a href=" + host + ":" + port + "/" + mailTo + ">этой ссылке</a>\n" +
                "(или откройте в интернет-браузере ссылку " + host + ":" + port + "/" + mailTo + ").\n" +
                "\n" +
                "Если у вас возникли вопросы, пишите в <a href=mailto:" + supportMail + ">службу поддержки</a> по адресу " + supportMail + ".\n" +
                " \n" +
                "Пожалуйста, не отвечайте на данное письмо.\n" +
                " \n" +
                "С уважением, Ваш <a href=" + host + ">" + exchangeRental + "</a>.";
        sendFrom = supportMail;
    }




}
