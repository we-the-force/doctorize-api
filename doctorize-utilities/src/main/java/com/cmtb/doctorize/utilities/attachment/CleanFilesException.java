/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.utilities.attachment;

/**
 *
 * @author jazavala
 */
public class CleanFilesException extends RuntimeException {
    
    private   String message;
    
    public CleanFilesException(String message) {
        this.message=message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
    
    
}
