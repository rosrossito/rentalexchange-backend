package com.upteam.auth.component;

import java.util.List;

/**
 * Created by opasichnyk on 12/4/2015.
 */
public interface EmailGenerator {

    List<String> getEmailsTo();

    String getSubject();

    String getText();

    String getFrom();

}
