/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.domain.shared;

/**
 *
 * @author pc
 */
public class ConfirmationCodeExceptoin extends RuntimeException{
    
    private final String message = "Código invalido";

    @Override
    public String getMessage() {
        return this.message;
    }
}
