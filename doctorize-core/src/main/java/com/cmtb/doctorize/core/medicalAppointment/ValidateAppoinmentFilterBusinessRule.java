/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.core.medicalAppointment;

import com.cmtb.doctorize.core.shared.Validation;
import com.cmtb.doctorize.domain.medicalAppointment.MedicalAppoinmentFilterDisplayObject;
import com.cmtb.doctorize.domain.user.ChangePasswordDisplayObject;
import com.cmtb.doctorize.domain.shared.NotFoundException;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 *
 * @author CMTB
 */
@Component("ValidateAppoinmentFilterBusinessRule")
public class ValidateAppoinmentFilterBusinessRule implements Validation<MedicalAppoinmentFilterDisplayObject> {


    @Override
    public MedicalAppoinmentFilterDisplayObject validate(MedicalAppoinmentFilterDisplayObject model) {

        for(String filterName: model.getFilter()){
            switch(filterName){
                case "office": {
                    
                }
                case "date": {
                    
                }
                case "email": {
                    
                }
                default:{
                    
                }
            }
        }
        
        if (model.getUser() == null) {
            throw new NotFoundException();
        }
        Boolean exists = userDomain.existAssociatedCodeByPatient(model);
        if (!exists) {
            throw new NotFoundException();
        }
        return model;
    }

}
