/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.domain.utilities;

import java.util.Map;

/**
 *
 * @author cmtb
 */
public class Email {

    private String subject;
    private EmailProcessEnum emailProcess;
    private Map<String, String> content;
    private String to;
    private String cc;
    private String bcc;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public EmailProcessEnum getEmailProcess() {
        return emailProcess;
    }

    public void setEmailProcess(EmailProcessEnum emailProcess) {
        this.emailProcess = emailProcess;
    }

    public Map<String, String> getContent() {
        return content;
    }

    public void setContent(Map<String, String> content) {
        this.content = content;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getBcc() {
        return bcc;
    }

    public void setBcc(String bcc) {
        this.bcc = bcc;
    }
 
    
    
}
