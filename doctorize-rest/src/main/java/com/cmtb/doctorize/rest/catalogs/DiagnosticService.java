/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.rest.catalogs;

import com.cmtb.doctorize.core.catalogs.DiagnosticDomain;
import com.cmtb.doctorize.core.catalogs.DiagnosticOrchestrator;
import com.cmtb.doctorize.core.catalogs.DiseaseDomain;
import com.cmtb.doctorize.core.catalogs.DiseaseOrchestrator;
import com.cmtb.doctorize.domain.catalogs.Diagnostic;
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
public class DiagnosticService {
    
    @Resource(name = "DiagnosticOrchestrator")
    private DiagnosticOrchestrator diagnosticOrchestrator;
    
    @Resource(name = "DiagnosticDomain")
    private DiagnosticDomain diagnosticDomain;
    
    @RequestMapping(value = "/diagnostics", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody Diagnostic diagnostic) {
        try {
            Diagnostic result = diagnosticOrchestrator.save(diagnostic);
            return new ResponseEntity(result, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/diagnostics/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getId(@PathVariable("id") Long diagnosticId) {
        try {

            Diagnostic result = diagnosticDomain.get(diagnosticId);
            return new ResponseEntity(result, HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/diagnostics", method = RequestMethod.GET)
    public ResponseEntity<?> getList() {
        try {

            List<Diagnostic> result = diagnosticDomain.getList();
            return new ResponseEntity(result, HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/diagnostics/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<?> update(@PathVariable("id") Long diagnosticId, @RequestBody Diagnostic diagnostic) {
        try {
            diagnostic.setId(diagnosticId);
            Boolean result = diagnosticOrchestrator.update(diagnostic);
            return new ResponseEntity(result, HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/diagnostics/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Long diagnosticId) {
        try {

            Boolean result = diagnosticOrchestrator.delete(diagnosticId);
            return new ResponseEntity(result, HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
