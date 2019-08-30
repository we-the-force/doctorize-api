/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.rest.medicalAppointment;

import com.cmtb.doctorize.core.medicalAppointment.MedicalAppointmentDomain;
import com.cmtb.doctorize.core.medicalAppointment.MedicalAppointmentOrchestrator;
import com.cmtb.doctorize.domain.medicalAppointment.MedicalAppointmentDisplayObject;
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
 * @author pc
 */
@CrossOrigin
@RestController
public class MedicalAppointmentService {
    
    @Resource(name = "MedicalAppointmentOrchestrator")
    private MedicalAppointmentOrchestrator medicalAppointmentOrchestrator;
    
    @Resource(name = "MedicalAppointmentDomain")
    private MedicalAppointmentDomain medicalAppointmentDomain;
    
    @RequestMapping(value = "/doctors/{id}/appointments", method = RequestMethod.POST)
    public ResponseEntity<?> save(@PathVariable("id") Long doctorId, @RequestBody MedicalAppointmentDisplayObject medicalAppointment) {
        try {
            medicalAppointment.setDoctorId(doctorId);
            MedicalAppointmentDisplayObject result = medicalAppointmentOrchestrator.save(medicalAppointment);
            return new ResponseEntity(result, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/doctors/{id}/appointments", method = RequestMethod.GET)
    public ResponseEntity<?> getListByDoctorId(@PathVariable("id") Long doctorId) {
        try {
            List<MedicalAppointmentDisplayObject> result = medicalAppointmentDomain.getListByDoctorId(doctorId);
            return new ResponseEntity(result, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/doctors/{doctorId}/appointments", params = {"filter", "search"}, method = RequestMethod.GET)
    public ResponseEntity<?> getListByFilters(@PathVariable("doctorId") Long doctorId, @RequestParam List<String> filter, @RequestParam List<String> search) {
        try {

            List<MedicalAppointmentDisplayObject> result = medicalAppointmentDomain.getListByFilters(doctorId, filter, search);
            return new ResponseEntity(result, HttpStatus.OK);

        } catch (ItemNotFoundException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/appointments/{appointmentId}", method = RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable("appointmentId") Long appointmentId) {
        try {
            MedicalAppointmentDisplayObject result = medicalAppointmentDomain.getById(appointmentId);
            return new ResponseEntity(result, HttpStatus.OK);
        } catch (ItemNotFoundException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/appointments/{appointmentId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("appointmentId") Long appointmentId) {
        try {
            Boolean result = medicalAppointmentDomain.delete(appointmentId);
            return new ResponseEntity(result, HttpStatus.OK);
        } catch (ItemNotFoundException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/appointments/{appointmentId}", method = RequestMethod.PATCH)
    public ResponseEntity<?> update(@PathVariable("appointmentId") Long appointmentId, 
            @RequestBody Map<String, Object> medicalAppointmentDisplayObjectMap) {
        try {
            medicalAppointmentDisplayObjectMap.put("id", appointmentId);
            Boolean result = medicalAppointmentOrchestrator.patch(medicalAppointmentDisplayObjectMap);
            return new ResponseEntity(result, HttpStatus.OK);
        } catch (ItemNotFoundException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
