package com.upteam.auth.component;

import com.upteam.auth.component.emailgenerator.EmailGenerator;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by olegls2000 on 12/4/2015.
 */

@Component
public class EmailSenderImpl implements EmailSender {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    @Autowired
    private VelocityEngine velocityEngine;

    @Override
    public void sendEmail(final EmailGenerator emailGenerator) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                message.setSubject(emailGenerator.getSubject());
                message.setTo(emailGenerator.getEmailsTo().get(0));
                message.setFrom(emailGenerator.getFrom());
                String text = VelocityEngineUtils.mergeTemplateIntoString(
                        velocityEngine, emailGenerator.getTemplate(), "UTF-8", emailGenerator.getModel());
                message.setText(text, true);
            }
        };
        javaMailSender.send(preparator);
    }
}
