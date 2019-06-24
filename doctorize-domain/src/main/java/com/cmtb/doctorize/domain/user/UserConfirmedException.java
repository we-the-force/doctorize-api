/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.domain.user;

/**
 *
 * @author pc
 */
public class UserConfirmedException extends RuntimeException {

    private final String message = "El usuario ya ha sido confirmado";

    @Override
    public String getMessage() {
        return this.message;
    }
    
}
