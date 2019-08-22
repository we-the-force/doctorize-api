/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.rest.catalogs;

import com.cmtb.doctorize.core.catalogs.SpecialtyDomain;
import com.cmtb.doctorize.core.catalogs.SpecialtyOrchestrator;
import com.cmtb.doctorize.domain.catalogs.Specialty;
import java.util.List;
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
 * @author pc
 */
@CrossOrigin
@RestController
public class SpecialtyService {
    
    @Resource(name = "SpecialtyDomain")
    SpecialtyDomain specialtyDomain;
    
    @Resource(name = "SpecialtyOrchestrator")
    SpecialtyOrchestrator specialtyOrchestrator;
    
    @RequestMapping(value = "/specialties", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody Specialty specialty) {
        try {
            Specialty result = specialtyOrchestrator.save(specialty);
            return new ResponseEntity(result, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/specialties/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getId(@PathVariable("id") Long specialtyId) {
        try {

            Specialty result = specialtyDomain.get(specialtyId);
            return new ResponseEntity(result, HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/specialties", method = RequestMethod.GET)
    public ResponseEntity<?> getList() {
        try {

            List<Specialty> result = specialtyDomain.getList();
            return new ResponseEntity(result, HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/specialties/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<?> update(@PathVariable("id") Long specialtyId, @RequestBody Specialty specialty) {
        try {
            specialty.setId(specialtyId);
            Boolean result = specialtyOrchestrator.update(specialty);
            return new ResponseEntity(result, HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/specialties/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Long specialtyId) {
        try {

            Boolean result = specialtyOrchestrator.delete(specialtyId);
            return new ResponseEntity(result, HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/specialty/getList", method = RequestMethod.GET)
    public ResponseEntity<?> getListOld() {
        try {

            List<Specialty> result = specialtyDomain.getList();
            return new ResponseEntity(result, HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
