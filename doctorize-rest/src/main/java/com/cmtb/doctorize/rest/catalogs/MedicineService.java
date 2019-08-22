/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.rest.catalogs;

import com.cmtb.doctorize.core.catalogs.MedicineDomain;
import com.cmtb.doctorize.core.catalogs.MedicineOrchestrator;
import com.cmtb.doctorize.domain.catalogs.Medicine;
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
public class MedicineService {
    
    @Resource(name = "MedicineOrchestrator")
    private MedicineOrchestrator medicineOrchestrator;
    
    @Resource(name = "MedicineDomain")
    MedicineDomain medicineDomain;
    
    @RequestMapping(value = "/medicines", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody Medicine medicine) {
        try {
            Medicine result = medicineOrchestrator.save(medicine);
            return new ResponseEntity(result, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/medicines/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getId(@PathVariable("id") Long medicineId) {
        try {

            Medicine result = medicineDomain.get(medicineId);
            return new ResponseEntity(result, HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/medicines", method = RequestMethod.GET)
    public ResponseEntity<?> getList() {
        try {

            List<Medicine> result = medicineDomain.getList();
            return new ResponseEntity(result, HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/medicines/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<?> update(@PathVariable("id") Long medicineId, @RequestBody Medicine medicine) {
        try {
            medicine.setId(medicineId);
            Boolean result = medicineOrchestrator.update(medicine);
            return new ResponseEntity(result, HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/medicines/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Long medicineId) {
        try {

            Boolean result = medicineOrchestrator.delete(medicineId);
            return new ResponseEntity(result, HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
