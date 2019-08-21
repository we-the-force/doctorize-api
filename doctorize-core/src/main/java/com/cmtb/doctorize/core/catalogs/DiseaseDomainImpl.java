/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.core.catalogs;

import com.cmtb.doctorize.data.catalogs.DiseaseDao;
import com.cmtb.doctorize.domain.catalogs.Disease;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 *
 * @author pc
 */
@Component(value = "DiseaseDomain")
public class DiseaseDomainImpl implements DiseaseDomain {
    
    @Resource(name = "DiseaseDao")
    private DiseaseDao diseaseDao;
    
    @Override
    public Disease save(Disease disease){
        return diseaseDao.save(disease);
    }
    
    @Override
    public Disease get(Long diseaseId) {
        return diseaseDao.get(diseaseId);
    }
    
    @Override
    public List<Disease> getList() {
        return diseaseDao.getList();
    }
    
    @Override
    public Boolean update(Disease disease){
        return diseaseDao.update(disease);
    }
    
    @Override
    public Boolean delete(Long diseaseId){
        return diseaseDao.delete(diseaseId);
    }
}
