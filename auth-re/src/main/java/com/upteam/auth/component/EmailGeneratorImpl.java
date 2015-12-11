package com.upteam.auth.component;

import java.util.List;

/**
 * Created by Andrew on 10.12.2015.
 */
//это класс для теста
public class EmailGeneratorImpl implements EmailGenerator{

    private List<String> emailsTo;
    private String subject;
    private String text;
    private String from;

    public EmailGeneratorImpl(List<String> emailsTo, String subject, String text, String from) {
        this.emailsTo = emailsTo;
        this.subject = subject;
        this.text = text;
        this.from = from;
    }

    @Override
    public List<String> getEmailsTo() {
        return this.emailsTo;
    }

    @Override
    public String getSubject() {
        return this.subject;
    }

    @Override
    public String getText() {
        return this.text;
    }

    @Override
    public String getFrom() {
        return this.from;
    }

    public void setEmailsTo(List<String> emailsTo) {
        this.emailsTo = emailsTo;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
