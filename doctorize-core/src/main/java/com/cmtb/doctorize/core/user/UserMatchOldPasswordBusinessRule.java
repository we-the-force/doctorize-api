/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.core.user;

import com.cmtb.doctorize.core.shared.Validation;
import com.cmtb.doctorize.domain.user.ChangePasswordDisplayObject;
import com.cmtb.doctorize.domain.shared.NotFoundException;
import com.cmtb.doctorize.domain.user.UserNotMatchPasswordException;
import com.cmtb.doctorize.utilities.PasswordEncrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author CMTB
 */
@Component("UserMatchOldPasswordBusinessRule")
public class UserMatchOldPasswordBusinessRule implements Validation< ChangePasswordDisplayObject> {

    @Autowired
    private PasswordEncrypt passwordEncrypt;

    @Override
    public ChangePasswordDisplayObject validate(ChangePasswordDisplayObject model) {

        if (model.getUser()== null) {
            throw new NotFoundException();
        }

        if (model.getOldPassword() == null) {
            throw new UserNotMatchPasswordException();
        }

        Boolean match = passwordEncrypt.checkPassword(model.getOldPassword(),
                model.getUser().getPassword());
 
        if (!match) {
            throw new UserNotMatchPasswordException();
        }

        return model;
    }

}
