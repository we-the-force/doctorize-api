/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.utilities.image;

/**
 *
 * @author jazavala
 */
public class ImageGeneratorException extends RuntimeException {

    private final String message;

    public ImageGeneratorException(String message) {
        this.message=message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
    
    
    
}
