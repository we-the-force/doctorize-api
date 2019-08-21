/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.core.catalogs;

import com.cmtb.doctorize.domain.catalogs.Disease;
import java.util.List;

/**
 *
 * @author pc
 */
public interface DiseaseDomain {

    Boolean delete(Long diseaseId);

    Disease get(Long diseaseId);

    List<Disease> getList();

    Disease save(Disease disease);

    Boolean update(Disease disease);
    
}
