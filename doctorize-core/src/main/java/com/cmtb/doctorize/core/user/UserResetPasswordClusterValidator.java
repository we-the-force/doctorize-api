/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.core.user;

import com.cmtb.doctorize.core.shared.ClusterValidationAbstract;
import com.cmtb.doctorize.core.shared.Validation;
import com.cmtb.doctorize.domain.user.ChangePasswordDisplayObject;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 *
 * @author cmtb
 */
@Component("UserResetPasswordClusterValidator")
public class UserResetPasswordClusterValidator extends ClusterValidationAbstract<ChangePasswordDisplayObject> {

    @Resource(name = "MatchPasswordBusinessRule")
    private Validation matchPasswordBusinessRule;

    @Resource(name = "UserExistCodeBusinessRule")
    private Validation userExistCodeBusinessRule;

    @Override
    public void assingValidations() {
        List<Validation> validations = new ArrayList<>();

        validations.add(matchPasswordBusinessRule);
        validations.add(userExistCodeBusinessRule);

        super.setValidations(validations);
    }

}
