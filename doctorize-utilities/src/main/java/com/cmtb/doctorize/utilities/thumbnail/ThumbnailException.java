/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.utilities.thumbnail;

/**
 *
 * @author cmtbs
 */
public class ThumbnailException extends RuntimeException {

    private final String message;

    public ThumbnailException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
