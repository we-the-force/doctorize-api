/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.data.catalogs;

import com.cmtb.doctorize.domain.catalogs.Frequency;
import java.util.List;

/**
 *
 * @author pc
 */
public interface FrequencyDao {

    public Boolean delete(Long frequencyId);

    public Frequency get(Long frequencyId);

    public List<Frequency> getList();

    public Frequency save(Frequency frequency);

    public Boolean update(Frequency frequency);
    
}
