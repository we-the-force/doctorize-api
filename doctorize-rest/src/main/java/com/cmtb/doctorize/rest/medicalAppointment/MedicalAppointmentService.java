/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.rest.medicalAppointment;

import com.cmtb.doctorize.core.medicalAppointment.MedicalAppointmentOrchestrator;
import com.cmtb.doctorize.domain.medicalAppointment.MedicalAppointment;
import com.cmtb.doctorize.domain.medicalAppointment.MedicalAppointmentDisplayObject;
import javax.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author pc
 */
@CrossOrigin
@RestController
public class MedicalAppointmentService {
    
    @Resource(name = "MedicalAppointmentOrchestrator")
    private MedicalAppointmentOrchestrator medicalAppointmentOrchestrator;
    
    @RequestMapping(value = "/medicalAppointment/save", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody MedicalAppointmentDisplayObject medicalAppointment) {
        try {
            MedicalAppointmentDisplayObject result = medicalAppointmentOrchestrator.save(medicalAppointment);
            return new ResponseEntity(result, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
