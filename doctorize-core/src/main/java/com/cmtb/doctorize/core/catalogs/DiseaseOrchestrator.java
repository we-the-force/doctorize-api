/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.core.catalogs;

import com.cmtb.doctorize.domain.catalogs.Disease;

/**
 *
 * @author pc
 */
public interface DiseaseOrchestrator {

    public Boolean delete(Long diseaseId);

    public Disease save(Disease disease);

    public Boolean update(Disease disease);
    
}
