/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.rest.catalogs;

import com.cmtb.doctorize.core.catalogs.DiseaseDomain;
import com.cmtb.doctorize.core.catalogs.DiseaseOrchestrator;
import com.cmtb.doctorize.domain.catalogs.Disease;
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
public class DiseaseService {
    
    @Resource(name = "DiseaseOrchestrator")
    private DiseaseOrchestrator diseaseOrchestrator;
    
    @Resource(name = "DiseaseDomain")
    DiseaseDomain diseaseDomain;
    
    @RequestMapping(value = "/diseases", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody Disease disease) {
        try {
            Disease result = diseaseOrchestrator.save(disease);
            return new ResponseEntity(result, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/diseases/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getId(@PathVariable("id") Long diseaseId) {
        try {

            Disease result = diseaseDomain.get(diseaseId);
            return new ResponseEntity(result, HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/diseases", method = RequestMethod.GET)
    public ResponseEntity<?> getList() {
        try {

            List<Disease> result = diseaseDomain.getList();
            return new ResponseEntity(result, HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/diseases/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<?> update(@PathVariable("id") Long diseaseId, @RequestBody Disease disease) {
        try {
            disease.setId(diseaseId);
            Boolean result = diseaseOrchestrator.update(disease);
            return new ResponseEntity(result, HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/diseases/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Long diseaseId) {
        try {

            Boolean result = diseaseOrchestrator.delete(diseaseId);
            return new ResponseEntity(result, HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
