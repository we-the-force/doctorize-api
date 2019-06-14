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
public class UserUnconfirmedException extends RuntimeException {

    private final String message = "Usuario pendinte de confirmar";

    @Override
    public String getMessage() {
        return this.message;
    }
    
}
