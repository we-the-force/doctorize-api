/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.core.catalogs;

import com.cmtb.doctorize.data.catalogs.FrequencyDao;
import com.cmtb.doctorize.domain.catalogs.Frequency;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 *
 * @author pc
 */
@Component(value = "FrequencyDomain")
public class FrequencyDomainImpl implements FrequencyDomain {
    
    @Resource(name = "FrequencyDao")
    private FrequencyDao frequencyDao;
    
    @Override
    public Frequency save(Frequency frequency){
        return frequencyDao.save(frequency);
    }
    
    @Override
    public Frequency get(Long frequencyId) {
        return frequencyDao.get(frequencyId);
    }
    
    @Override
    public List<Frequency> getList() {
        return frequencyDao.getList();
    }
    
    @Override
    public Boolean update(Frequency frequency){
        return frequencyDao.update(frequency);
    }
    
    @Override
    public Boolean delete(Long frequencyId){
        return frequencyDao.delete(frequencyId);
    }
}
