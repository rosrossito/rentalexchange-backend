package com.upteam.auth.component.emailgenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Skirdovs on 16.12.2015.
 */
public class EmailGeneratorConfirmRegistration implements EmailGenerator {
    private String mailTo;
    private final String subject = "Exchange Rental. Уведомление об активации учётной записи пользователя";
    private final String text = "Здравствуйте!\n" +
            " \n" +
            "Учётная запись пользователя с данным e-mail " + mailTo + " активирована.\n" +
            " \n" +
            "Для входа в личный кабинет перейдите по <a href=[%ссылка для входа с параметром e-mail%]>этой ссылке</a>\n" +
            "(или откройте в интернет-браузере ссылку [%ссылка для входа с параметром e-mail%]).\n" +
            "\n" +
            "Если у вас возникли вопросы, пишите в <a href=mailto:[%e-mail поддержки%]>службу поддержки</a> по адресу [%e-mail поддержки%].\n" +
            " \n" +
            "Пожалуйста, не отвечайте на данное письмо.\n" +
            " \n" +
            "С уважением, Ваш <a href=[%адрес площадки%]>[%Название площадки%]</a>.";
    private final String sendFrom = "exchange.rental.info@gmail.com";

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
}
