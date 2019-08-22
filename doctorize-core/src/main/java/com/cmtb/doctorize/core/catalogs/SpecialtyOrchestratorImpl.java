/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.core.catalogs;

import com.cmtb.doctorize.domain.catalogs.Specialty;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pc
 */
@Component(value = "SpecialtyOrchestrator")
public class SpecialtyOrchestratorImpl implements SpecialtyOrchestrator {
    
    @Resource(name = "SpecialtyDomain")
    private SpecialtyDomain specialtyDomain;
    
    @Transactional
    @Override
    public Specialty save(Specialty specialty){
        return specialtyDomain.save(specialty);
    }
    
    @Transactional
    @Override
    public Boolean delete(Long specialtyId){
        return specialtyDomain.delete(specialtyId);
    }
    
    @Transactional
    @Override
    public Boolean update(Specialty specialty){
        return specialtyDomain.update(specialty);
    }
}
