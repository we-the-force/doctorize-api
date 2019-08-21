/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.core.catalogs;

import com.cmtb.doctorize.domain.catalogs.Disease;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pc
 */
@Component(value = "DiseaseOrchestrator")
public class DiseaseOrchestratorImpl implements DiseaseOrchestrator {
    
    @Resource(name = "DiseaseDomain")
    private DiseaseDomain diseaseDomain;
    
    @Transactional
    @Override
    public Disease save(Disease disease){
        return diseaseDomain.save(disease);
    }
    
    @Transactional
    @Override
    public Boolean delete(Long diseaseId){
        diseaseDomain.delete(diseaseId);
        return diseaseDomain.delete(diseaseId);
    }
    
    @Transactional
    @Override
    public Boolean update(Disease disease){
        return diseaseDomain.update(disease);
    }
}
