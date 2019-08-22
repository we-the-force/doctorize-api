/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.core.catalogs;

import com.cmtb.doctorize.domain.catalogs.Specialty;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pc
 */
public interface SpecialtyOrchestrator {

    public Boolean delete(Long specialtyId);

    public Specialty save(Specialty specialty);

    public Boolean update(Specialty specialty);
    
}
