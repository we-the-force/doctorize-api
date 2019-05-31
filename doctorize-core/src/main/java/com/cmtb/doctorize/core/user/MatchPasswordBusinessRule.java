/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.core.user;


import com.cmtb.doctorize.core.shared.Validation;
import com.cmtb.doctorize.domain.user.ChangePasswordDisplayObject;
import org.springframework.stereotype.Component;

/**
 *
 * @author CMTB
 */
@Component("MatchPasswordBusinessRule")
public class MatchPasswordBusinessRule implements Validation<ChangePasswordDisplayObject> {

    @Override
    public ChangePasswordDisplayObject validate(ChangePasswordDisplayObject model) {

        if (!model.getPassword().trim().equals(model.getConfirmPassword().trim())) {
            throw new IllegalArgumentException(" contraseña y confirmación de contraseña no coinciden");
        }

        return model;
    }

}
