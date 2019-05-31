/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.utilities.email;

import com.cmtb.doctorize.domain.utilities.Email;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 *
 * @author cmtb
 */
@Component
public class EmailUtilsComponent {

    @Autowired
    private EmailSender emailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Async
    public void send(Email email) {
        Context context = new Context();

        if (email != null) {
            if ((email.getTo() != null) && (!email.getTo().isEmpty())) {
                email.getContent().entrySet().stream().forEach((entry) -> {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    context.setVariable(key, value);
                });
                String body = templateEngine.process(email.getEmailProcess()
                        .getTemplate(), context);
                emailSender.send(email.getTo(), email.getCc(), email.getBcc(),
                        email.getEmailProcess().getSubject(), body, true);
            }
        } 
    }
    
    @Async
    public void send(Email email, String body) {
        Context context = new Context();

        if (email != null) {
            if ((email.getTo() != null) && (!email.getTo().isEmpty())) {
//                email.getContent().entrySet().stream().forEach((entry) -> {
//                    String key = entry.getKey();
//                    String value = entry.getValue();
//                    context.setVariable(key, value);
//                });
                
                emailSender.send(email.getTo(), email.getCc(), email.getBcc(),
                        email.getEmailProcess().getSubject(), body, true);
            }
        } 
    }
    
    @Async
    public void send(List<Email> emails){
        for (Email email : emails) {
            send(email);
        }
    }
}
