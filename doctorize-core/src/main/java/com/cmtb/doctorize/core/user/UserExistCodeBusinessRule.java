/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.core.user;

import com.cmtb.doctorize.core.shared.Validation;
import com.cmtb.doctorize.domain.user.ChangePasswordDisplayObject;
import com.cmtb.doctorize.domain.user.UserNotFoundException;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 *
 * @author CMTB
 */
@Component("UserExistCodeBusinessRule")
public class UserExistCodeBusinessRule implements Validation< ChangePasswordDisplayObject> {

    @Resource(name = "UserDomain")
    private UserDomain userDomain;

    @Override
    public ChangePasswordDisplayObject validate(ChangePasswordDisplayObject model) {

        if (model.getUser() == null) {
            throw new UserNotFoundException();
        }
        Boolean exists = userDomain.existAssociatedCodeByPatient(model);
        if (!exists) {
            throw new UserNotFoundException();
        }
        return model;
    }

}
