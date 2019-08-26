/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.rest.catalogs;

import com.cmtb.doctorize.core.catalogs.DiseaseDomain;
import com.cmtb.doctorize.core.catalogs.DiseaseOrchestrator;
import com.cmtb.doctorize.core.catalogs.FrequencyDomain;
import com.cmtb.doctorize.core.catalogs.FrequencyOrchestrator;
import com.cmtb.doctorize.domain.catalogs.Disease;
import com.cmtb.doctorize.domain.catalogs.Frequency;
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
public class FrequencyService {
    
    @Resource(name = "FrequencyOrchestrator")
    private FrequencyOrchestrator frequencyOrchestrator;
    
    @Resource(name = "FrequencyDomain")
    private FrequencyDomain frequencyDomain;
    
    @RequestMapping(value = "/frequencies", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody Frequency frequency) {
        try {
            Frequency result = frequencyOrchestrator.save(frequency);
            return new ResponseEntity(result, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/frequencies/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getId(@PathVariable("id") Long frequencyId) {
        try {

            Frequency result = frequencyDomain.get(frequencyId);
            return new ResponseEntity(result, HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/frequencies", method = RequestMethod.GET)
    public ResponseEntity<?> getList() {
        try {

            List<Frequency> result = frequencyDomain.getList();
            return new ResponseEntity(result, HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/frequencies/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<?> update(@PathVariable("id") Long frequencyId, @RequestBody Frequency frequency) {
        try {
            frequency.setId(frequencyId);
            Boolean result = frequencyOrchestrator.update(frequency);
            return new ResponseEntity(result, HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/frequencies/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Long frequencyId) {
        try {

            Boolean result = frequencyOrchestrator.delete(frequencyId);
            return new ResponseEntity(result, HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
