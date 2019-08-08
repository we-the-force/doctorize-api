/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.rest.doctorOffice;

import com.cmtb.doctorize.core.doctorOffice.DoctorOfficeDomain;
import com.cmtb.doctorize.core.doctorOffice.DoctorOfficeOrchestrator;
import com.cmtb.doctorize.domain.doctor.DoctorOfficeDisplayObject;
import com.cmtb.doctorize.domain.shared.ItemNotFoundException;
import com.cmtb.doctorize.domain.shared.NotFoundException;
import java.util.List;
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
public class DoctorOfficeService {
    
    @Resource(name = "DoctorOfficeDomain")
    DoctorOfficeDomain doctorOfficeDomain;
    
    @Resource(name = "DoctorOfficeOrchestrator")
    DoctorOfficeOrchestrator doctorOfficeOrchestrator;
    
    @RequestMapping(value = "/doctors/{id}/offices", method = RequestMethod.GET)
    public ResponseEntity<?> getListByUserId(@PathVariable("id") Long doctorId) {
        try {

            List<DoctorOfficeDisplayObject> result = doctorOfficeDomain.getListByUserId(doctorId);
            return new ResponseEntity(result, HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/doctors/{id}/offices", method = RequestMethod.POST)
    public ResponseEntity<?> save(@PathVariable("id") Long doctorId, @RequestBody DoctorOfficeDisplayObject doctorOfficeDisplayObject) {
        try {
            doctorOfficeDisplayObject.setUserId(doctorId);
            DoctorOfficeDisplayObject result = doctorOfficeOrchestrator.save(doctorOfficeDisplayObject);
            return new ResponseEntity(result, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/offices/{officeId}", method = RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable("officeId") Long officeId) {
        try {

            DoctorOfficeDisplayObject result = doctorOfficeDomain.getById(officeId);
            return new ResponseEntity(result, HttpStatus.OK);

        } catch (ItemNotFoundException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/offices/{officeId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAssistant(@PathVariable("officeId") Long doctorOfficeId) {
        try {

            Boolean result = doctorOfficeOrchestrator.delete(doctorOfficeId);
            
            return new ResponseEntity(result, HttpStatus.OK);

        } catch (NotFoundException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/offices/{officeId}", method = RequestMethod.PATCH)
    public ResponseEntity<?> update(@PathVariable("officeId") Long doctorOfficeId, @RequestBody DoctorOfficeDisplayObject doctorOfficeDisplayObject) {
        try {
            doctorOfficeDisplayObject.setId(doctorOfficeId);
            DoctorOfficeDisplayObject result = doctorOfficeOrchestrator.save(doctorOfficeDisplayObject);
            return new ResponseEntity(result, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
}
