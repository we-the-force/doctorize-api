/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.core.shared;

/**
 *
 * @author pc
 */
public interface Validation<T> {
    
    public T validate(T model);
    
}
