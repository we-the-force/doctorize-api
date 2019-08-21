/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.core.medicalAppointment;

import com.cmtb.doctorize.core.shared.Validation;
import com.cmtb.doctorize.domain.medicalAppointment.MedicalAppoinmentFilterDisplayObject;
import com.cmtb.doctorize.domain.shared.FilterException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.springframework.stereotype.Component;

/**
 *
 * @author CMTB
 */
@Component("ValidateAppoinmentFilterBusinessRule")
public class ValidateAppoinmentFilterBusinessRule implements Validation<MedicalAppoinmentFilterDisplayObject> {

    private void setDates(MedicalAppoinmentFilterDisplayObject model, int index){
        
        if(model.getDoctorId() == null){
            throw new FilterException();
        }
        
        try {
            Date date= new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(model.getSearch().get(index) + " 00:00");
            
            model.setStartDate(date);
            
            Date dateEND= new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(model.getSearch().get(index) + " 23:59");
            
            model.setEndDate(dateEND);
        } catch (ParseException ex) {
            throw new FilterException();
        }
    }

    @Override
    public MedicalAppoinmentFilterDisplayObject validate(MedicalAppoinmentFilterDisplayObject model) {

        for(int i=0; i < model.getFilter().size(); i++){
            switch(model.getFilter().get(i)){
                case "office": {
                    model.setOffice(Long.parseLong(model.getSearch().get(i)));
                    break;
                }
                case "date": {
                    setDates(model, i);
                    break;
                }
                case "email": {
                    model.setEmail(model.getSearch().get(i));
                    break;
                }
                default:{
                    throw new FilterException();
                }
            }
        }
        
        return model;
    }

}
