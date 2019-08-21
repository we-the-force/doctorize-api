/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.data.catalogs;

import com.cmtb.doctorize.domain.catalogs.Disease;
import java.util.List;

/**
 *
 * @author pc
 */
public interface DiseaseDao {

    public Boolean delete(Long diseaseId);

    public Disease get(Long diseaseId);

    public List<Disease> getList();

    public Disease save(Disease disease);

    public Boolean update(Disease disease);
    
}
