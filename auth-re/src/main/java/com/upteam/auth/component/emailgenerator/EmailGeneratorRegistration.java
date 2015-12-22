package com.upteam.auth.component.emailgenerator;

import com.upteam.auth.domain.SystemUser;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Влад on 11.12.2015.
 */
public class EmailGeneratorRegistration implements EmailGenerator {

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

    //Это не будет работать  - эти обьекты нужно сконфигурить
    @Autowired
    private VelocityEngine velocityEngine;
    @Autowired
    private JavaMailSenderImpl javaMailSender;

    public EmailGeneratorRegistration(String mailTo, String registrationConfirmLink, SystemUser systemUser) {
        this.emailsTo.add(mailTo);
        this.registrationConfirmLink = registrationConfirmLink;
        this.systemUser = systemUser;
    }

    public void sendEmailTemplate() {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                message.setTo(mailTo);
                message.setFrom(sendFrom);
                Map model = new HashMap();
                model.put("userEmail", mailTo);
                model.put("registrationConfirmLink", registrationConfirmLink);
                String text = VelocityEngineUtils.mergeTemplateIntoString(
                        velocityEngine, "com/registration.vm", "UTF-8", model);
                System.out.println("6");
                message.setText(text, true);
            }
        };
        this.javaMailSender.send(preparator);
    }
   /* public VelocityEngine velocityEngine(){
        VelocityEngineFactoryBean velocityEngine = new VelocityEngineFactoryBean();
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("resource.loader", "class");
        properties.put("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        velocityEngine.setVelocityPropertiesMap(properties);
        return velocityEngine.getObject();
    }*/

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
        return "ssfsf";
    }

    @Override
    public String getFrom() {
        return sendFrom;
    }

}
