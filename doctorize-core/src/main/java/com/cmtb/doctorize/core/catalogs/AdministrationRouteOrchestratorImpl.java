/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.core.catalogs;

import com.cmtb.doctorize.domain.catalogs.AdministrationRoute;
import com.cmtb.doctorize.domain.catalogs.Disease;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pc
 */
@Component(value = "AdministrationRouteOrchestrator")
public class AdministrationRouteOrchestratorImpl implements AdministrationRouteOrchestrator {
    
    @Resource(name = "AdministrationRouteDomain")
    private AdministrationRouteDomain administrationRouteDomain;
    
    @Transactional
    @Override
    public AdministrationRoute save(AdministrationRoute administrationRoute){
        return administrationRouteDomain.save(administrationRoute);
    }
    
    @Transactional
    @Override
    public Boolean delete(Long administrationRouteId){
        return administrationRouteDomain.delete(administrationRouteId);
    }
    
    @Transactional
    @Override
    public Boolean update(AdministrationRoute administrationRoute){
        return administrationRouteDomain.update(administrationRoute);
    }
}
