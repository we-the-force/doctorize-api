/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.core.catalogs;

import com.cmtb.doctorize.domain.catalogs.Frequency;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pc
 */
public interface FrequencyOrchestrator {

    public Boolean delete(Long frequencyId);

    public Frequency save(Frequency frequency);

    public Boolean update(Frequency frequency);
    
}
