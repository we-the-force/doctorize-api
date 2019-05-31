/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.core.shared;

import com.cmtb.doctorize.domain.shared.ClusterEmptyValidationException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pc
 */
public abstract class ClusterValidationAbstract<T> {
    
    private List<Validation> validations = new ArrayList<>();

    public void setValidations(List<Validation> validations) {
        this.validations = validations;
    }

    public abstract void assingValidations();

    public T run(T model) {
        assingValidations();

        if ((validations == null) || (validations.isEmpty())) {
            throw new ClusterEmptyValidationException();
        }

        for (Validation validation : validations) {
            model = (T) validation.validate(model);
        }
        return model;
    }
}
