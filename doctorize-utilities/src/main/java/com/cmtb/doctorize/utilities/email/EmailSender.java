/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.utilities.email;

import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import org.springframework.stereotype.Component;

/**
 *
 * @author jazavala
 */
@Component
public class EmailSender {

    @Autowired
    private JavaMailSender javaMailSender;

    public void send(String to, String cc, String bcc, String subject, String message, Boolean isHtml) {
        try {
            if ((to != null) && (!to.trim().isEmpty())) {
                MimeMessage mail = javaMailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(mail, true);
                helper.setTo(to);
                helper.setSubject(subject);
                helper.setText(message, isHtml);

                if ((cc != null) && (!cc.trim().isEmpty())) {
                    helper.setCc(cc);
                }
                
                if ((bcc != null) && (!bcc.trim().isEmpty())) {
                     helper.setBcc(bcc);
                }
                 

                javaMailSender.send(mail);
            }
        } catch (Exception e) {

        }
    }
}
