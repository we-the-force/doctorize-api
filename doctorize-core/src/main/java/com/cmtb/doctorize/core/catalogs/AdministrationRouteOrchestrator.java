/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.core.catalogs;

import com.cmtb.doctorize.domain.catalogs.AdministrationRoute;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pc
 */
public interface AdministrationRouteOrchestrator {

    @Transactional
    Boolean delete(Long administrationRouteId);

    @Transactional
    AdministrationRoute save(AdministrationRoute administrationRoute);

    @Transactional
    Boolean update(AdministrationRoute administrationRoute);
    
}
