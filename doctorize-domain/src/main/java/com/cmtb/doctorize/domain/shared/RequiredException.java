/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.domain.shared;

import java.text.MessageFormat;

/**
 *
 * @author pc
 */
public class RequiredException extends RuntimeException {

    private final String field;
    private final String MESSAGE_ERROR = "El campo {0} es requerido";

    public RequiredException(String field) {
        this.field = field;
    }

    @Override
    public String getMessage() {
        return MessageFormat.format(MESSAGE_ERROR, this.field);
    }

}
