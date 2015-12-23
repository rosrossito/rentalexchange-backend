package com.upteam.auth.component.emailgenerator;

import java.util.List;
import java.util.Map;

/**
 * Created by opasichnyk on 12/4/2015.
 */
public interface EmailGenerator {

    List<String> getEmailsTo();

    String getSubject();

    String getTemplate();

    Map<String, Object> getModel();

}
