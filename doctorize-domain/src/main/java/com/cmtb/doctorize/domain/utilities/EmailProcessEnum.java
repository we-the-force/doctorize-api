/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.domain.utilities;

/**
 *
 * @author cmtb
 */
public enum EmailProcessEnum {

    DOCTOR_NEW_REGISTER("doctor_new_register", "Registro de nuevo doctor"),
    ADMINISTRATOR_REVIEW_NEW_DOCTOR("administrator_review_new_doctor", "Revisión de acceso a sanabitus"),
    DOCTOR_LOCATION("doctor_location", "Ubicación de doctor"),
    SUPPORT_NOTIFY("support_notify", "Soporte técnico"),
    
    PURCHASE_SENT("purchase_sent", "Compra enviada"),
    NEW_PURCHASE("new_purchase", "Nueva Compra"),
    QUOTE_SENT("quote_sent", "Cotización enviada"),
    CHECKING_QUOTE("checking_quote", "Revisando cotización"),
    NEW_QUOTE("new_quote", "Nueva Cotización"),
    USER_SEND_CODE_CHANGE_PASSWORD("user_send_code_change_password", "Solicitud de cambio de contraseña");

    private final String template;
    private final String subject;

    EmailProcessEnum(String template, String subject) {
        this.template = template;
        this.subject = subject;
    }

    public String getTemplate() {
        return this.template;
    }

    public String getSubject() {
        return this.subject;
    }

}
