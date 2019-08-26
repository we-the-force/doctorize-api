/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.core.catalogs;

import com.cmtb.doctorize.domain.catalogs.Frequency;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pc
 */
@Component(value = "FrequencyOrchestrator")
public class FrequencyOrchestratorImpl implements FrequencyOrchestrator {
    
    @Resource(name = "FrequencyDomain")
    private FrequencyDomain frequencyDomain;
    
    @Transactional
    @Override
    public Frequency save(Frequency frequency){
        return frequencyDomain.save(frequency);
    }
    
    @Transactional
    @Override
    public Boolean delete(Long frequencyId){
        return frequencyDomain.delete(frequencyId);
    }
    
    @Transactional
    @Override
    public Boolean update(Frequency frequency){
        return frequencyDomain.update(frequency);
    }
}
