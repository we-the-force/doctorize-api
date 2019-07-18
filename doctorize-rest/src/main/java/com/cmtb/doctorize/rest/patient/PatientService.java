/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.rest.patient;

import com.cmtb.doctorize.core.patient.PatientDomain;
import com.cmtb.doctorize.core.patient.PatientOrchestrator;
import com.cmtb.doctorize.domain.patient.Patient;
import com.cmtb.doctorize.domain.patient.PatientContainerDisplayObject;
import com.cmtb.doctorize.domain.patient.PatientDisplayObject;
import javax.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author gealtec
 */
@CrossOrigin
@RestController
public class PatientService {
    
    @Resource(name = "PatientOrchestrator")
    private PatientOrchestrator patientOrchestrator;
    
    @Resource(name = "PatientDomain")
    PatientDomain patientDomain;
    
    @RequestMapping(value = "/patient/save", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody Patient patient) {
        try {
            Patient result = patientOrchestrator.save(patient);
            return new ResponseEntity(result, HttpStatus.OK);
//        } catch (UserDuplicateException ex) {
//            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/patient/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable("id") Long patientId) {
        try {

            PatientDisplayObject result = patientDomain.getById(patientId);
            return new ResponseEntity(result, HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/patientContainer/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getPatientContainerById(@PathVariable("id") Long patientId) {
        try {

            PatientContainerDisplayObject result = patientDomain.loadCollectionPatient(patientId);
            return new ResponseEntity(result, HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
