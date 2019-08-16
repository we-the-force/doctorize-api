/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.core.medicalAppointment;

import com.cmtb.doctorize.core.shared.ClusterValidationAbstract;
import com.cmtb.doctorize.core.shared.Validation;
import com.cmtb.doctorize.domain.medicalAppointment.MedicalAppoinmentFilterDisplayObject;
import com.cmtb.doctorize.domain.user.ChangePasswordDisplayObject;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 *
 * @author cmtb
 */
@Component("MedicalAppoinmentFilterClusterValidator")
public class MedicalAppoinmentFilterClusterValidator extends ClusterValidationAbstract<MedicalAppoinmentFilterDisplayObject> {

    @Resource(name = "ValidateAppoinmentFilterBusinessRule")
    private Validation validateAppoinmentFilterBusinessRule;

    @Override
    public void assingValidations() {
        List<Validation> validations = new ArrayList<>();

        validations.add(validateAppoinmentFilterBusinessRule);

        super.setValidations(validations);
    }

}
