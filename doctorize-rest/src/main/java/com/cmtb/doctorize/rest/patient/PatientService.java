/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.rest.patient;

import com.cmtb.doctorize.core.patient.PatientDomain;
import com.cmtb.doctorize.core.patient.PatientOrchestrator;
import com.cmtb.doctorize.domain.patient.PatientContainerDisplayObject;
import com.cmtb.doctorize.domain.patient.PatientDisplayObject;
import com.cmtb.doctorize.domain.shared.ItemNotFoundException;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    
    @RequestMapping(value = "/doctors/{doctorId}/patients", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody PatientDisplayObject patientDO) {
        try {
            PatientDisplayObject result = patientOrchestrator.save(patientDO);
            return new ResponseEntity(result, HttpStatus.OK);
        } catch (ItemNotFoundException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/patients/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable("id") Long patientId) {
        try {

            PatientDisplayObject result = patientDomain.getById(patientId);
            return new ResponseEntity(result, HttpStatus.OK);

        } catch (ItemNotFoundException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/doctors/{doctorId}/patients", method = RequestMethod.GET)
    public ResponseEntity<?> getByDOctorId(@PathVariable("doctorId") Long doctorId) {
        try {

            List<PatientDisplayObject> result = patientDomain.getListByDoctorId(doctorId);
            return new ResponseEntity(result, HttpStatus.OK);

        } catch (ItemNotFoundException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/patients/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Long patientId) {
        try {

            Boolean result = patientOrchestrator.delete(patientId);
            return new ResponseEntity(result, HttpStatus.OK);

        } catch (ItemNotFoundException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/patients/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<?> update(@PathVariable("id") Long patientId, @RequestBody Map<String, Object> patientDOMap) {
        try {
            patientDOMap.put("id", patientId);
            Boolean result = patientOrchestrator.patch(patientDOMap);
            return new ResponseEntity(result, HttpStatus.OK);

        } catch (ItemNotFoundException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
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
    
    @RequestMapping(value = "/patients", params = {"filter", "search"}, method = RequestMethod.GET)
    public ResponseEntity<?> getListByRole(@RequestParam String filter, @RequestParam String search) {
        try {

            List<PatientDisplayObject> result = patientDomain.getByFilter(filter, search);
            return new ResponseEntity(result, HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/patients", params = {"offset", "limit"}, method = RequestMethod.GET)
    public ResponseEntity<?> getListByRole(@RequestParam Integer offset, @RequestParam Integer limit) {
        try {

            List<PatientDisplayObject> result = patientDomain.getListByLimit(offset, limit);
            return new ResponseEntity(result, HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
