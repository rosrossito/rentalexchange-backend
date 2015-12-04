package com.upteam.auth.component;

import org.springframework.stereotype.Component;

/**
 * Created by olegls2000 on 12/4/2015.
 */
@Component
public class EmailSenderImpl implements EmailSender {

    @Override
    public void sendEmail(EmailGenerator emailGenerator) {
       //TODO REN-29 [BackEnd] Отправка Емэла с ссылкой для подтверждения регистрации>Andrey
    }
}
