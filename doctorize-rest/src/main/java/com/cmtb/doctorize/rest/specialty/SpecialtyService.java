/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.rest.specialty;

import com.cmtb.doctorize.core.specialty.SpecialtyDomain;
import com.cmtb.doctorize.domain.specialty.Specialty;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
    
    @RequestMapping(value = "/specialty/getList", method = RequestMethod.GET)
    public ResponseEntity<?> getList() {
        try {

            List<Specialty> result = specialtyDomain.getList();
            return new ResponseEntity(result, HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
