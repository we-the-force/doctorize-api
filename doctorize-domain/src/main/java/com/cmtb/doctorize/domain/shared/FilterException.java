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
public class FilterException extends RuntimeException {

    private final String message = "Filtro inválido, favor de verificar";

    @Override
    public String getMessage() {
        return this.message;
    }
    
}
