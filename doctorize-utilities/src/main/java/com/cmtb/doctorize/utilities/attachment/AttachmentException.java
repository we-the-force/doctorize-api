/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.utilities.attachment;

/**
 *
 * @author CMTB
 */
public class AttachmentException extends RuntimeException {

    private final String message;

    public AttachmentException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

}
