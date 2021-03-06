package com.upteam.auth.component;

import com.upteam.auth.component.emailgenerator.EmailGenerator;


import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;


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

    @Resource
    private Environment env;

    @Override
    public void sendEmail(final EmailGenerator emailGenerator) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                message.setSubject(emailGenerator.getSubject());
                message.setTo(emailGenerator.getEmailsTo().get(0));
                message.setFrom(env.getProperty("email.from"));
                String textMainEmailTEmplate = VelocityEngineUtils.mergeTemplateIntoString(
                        velocityEngine, "emailtemplates/email.vm", "UTF-8", emailGenerator.getModel());
                message.setText(textMainEmailTEmplate, true);
            }
        };
        javaMailSender.send(preparator);
        LOG.info("Email to " + emailGenerator.getEmailsTo().get(0) +
                " with subject: " + emailGenerator.getSubject() +
                ". Successfully sent.");
    }
}
