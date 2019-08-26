/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.rest.catalogs;

import com.cmtb.doctorize.core.catalogs.AdministrationRouteDomain;
import com.cmtb.doctorize.core.catalogs.AdministrationRouteOrchestrator;
import com.cmtb.doctorize.domain.catalogs.AdministrationRoute;
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
public class AdministrationRouteService {
    
    @Resource(name = "AdministrationRouteOrchestrator")
    private AdministrationRouteOrchestrator administrationRouteOrchestrator;
    
    @Resource(name = "AdministrationRouteDomain")
    private AdministrationRouteDomain administrationRouteDomain;
    
    @RequestMapping(value = "/administrationRoutes", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody AdministrationRoute administrationRoute) {
        try {
            AdministrationRoute result = administrationRouteOrchestrator.save(administrationRoute);
            return new ResponseEntity(result, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/administrationRoutes/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getId(@PathVariable("id") Long administrationRouteId) {
        try {

            AdministrationRoute result = administrationRouteDomain.get(administrationRouteId);
            return new ResponseEntity(result, HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/administrationRoutes", method = RequestMethod.GET)
    public ResponseEntity<?> getList() {
        try {

            List<AdministrationRoute> result = administrationRouteDomain.getList();
            return new ResponseEntity(result, HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/administrationRoutes/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<?> update(@PathVariable("id") Long administrationRouteId, @RequestBody AdministrationRoute administrationRoute) {
        try {
            administrationRoute.setId(administrationRouteId);
            Boolean result = administrationRouteOrchestrator.update(administrationRoute);
            return new ResponseEntity(result, HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/administrationRoutes/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Long administrationRouteId) {
        try {

            Boolean result = administrationRouteOrchestrator.delete(administrationRouteId);
            return new ResponseEntity(result, HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
