package com.upteam.auth.component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Влад on 11.12.2015.
 */
public class UserRegistrationEmail implements EmailGenerator {

    private String mailTo;
    private final String subject = "Exchange Rental. Инструкции по активации учётной записи пользователя";
    private final String text = "Здравствуйте! \n"
            + "На сайте [Rentalexchange.com%] была выполнена регистрация учётной записи пользователя с указанием адреса" + "userEmail" + ". \n\n"
            + "Если Вы случайно получили это письмо, пожалуйста, проигнорируйте его. \n\n"
            + "Для активации учётной записи пользователя перейдите по <a href=[%уникальный адрес ссылки для регистрации данного e-mail%]>этой ссылке</a> \n"
            + "(или откройте в интернет-браузере ссылку [%уникальный адрес ссылки для регистрации данного e-mail%]).\n\n"
            + "Если у вас возникли вопросы, пишите в <a href=mailto:[%e-mail поддержки%]>службу поддержки</a> по адресу [%e-mail поддержки%]. \n\n"
            + "Пожалуйста, не отвечайте на данное письмо. \n\n"
            + "С уважением, Ваш <a href=[%адрес площадки%]>[%Название площадки%]</a>." ;
    private final String sendFrom = "exchange.rental.info@gmail.com";

    private List<String> emailsTo = new ArrayList<String>();

    public UserRegistrationEmail(String mailTo) {
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
    public String getText() {
        return text;
    }

    @Override
    public String getFrom() {
        return sendFrom;
    }

}