/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.domain.user;

/**
 *
 * @author CMTB
 */
public class UserNotMatchPasswordException extends RuntimeException {

    private final String message = "El password anterior no coincide, favor de verificar su información";

    @Override
    public String getMessage() {
        return this.message;
    }

}
