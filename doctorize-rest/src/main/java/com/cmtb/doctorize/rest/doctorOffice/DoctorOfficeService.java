/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.rest.doctorOffice;

import com.cmtb.doctorize.core.doctorOffice.DoctorOfficeDomain;
import com.cmtb.doctorize.core.doctorOffice.DoctorOfficeOrchestrator;
import com.cmtb.doctorize.domain.doctor.DoctorOffice;
import com.cmtb.doctorize.domain.doctor.DoctorOfficeDisplayObject;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
    
    @RequestMapping(value = "/doctorOffice/getListByUserId", params = {"userId"}, method = RequestMethod.GET)
    public ResponseEntity<?> getListByUserId(@RequestParam Long userId) {
        try {

            List<DoctorOfficeDisplayObject> result = doctorOfficeDomain.getListByUserId(userId);
            return new ResponseEntity(result, HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/doctorOffice/save", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody DoctorOfficeDisplayObject doctorOfficeDisplayObject) {
        try {
            DoctorOffice result = doctorOfficeOrchestrator.save(doctorOfficeDisplayObject);
            return new ResponseEntity(result, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/doctorOffice", params = {"doctorOfficeId"}, method = RequestMethod.GET)
    public ResponseEntity<?> getById(@RequestParam Long doctorOfficeId) {
        try {

            DoctorOfficeDisplayObject result = doctorOfficeDomain.getById(doctorOfficeId);
            return new ResponseEntity(result, HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
}
