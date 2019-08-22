/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.core.catalogs;

import com.cmtb.doctorize.data.catalogs.SpecialtyDao;
import com.cmtb.doctorize.domain.catalogs.Specialty;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 *
 * @author pc
 */
@Component(value = "SpecialtyDomain")
public class SpecialtyDomainImpl implements SpecialtyDomain {
    
    @Resource(name = "SpecialtyDao")
    private SpecialtyDao specialtyDao;
    
    @Override
    public Specialty save(Specialty specialty){
        return specialtyDao.save(specialty);
    }
    
    @Override
    public Specialty get(Long specialtyId) {
        return specialtyDao.get(specialtyId);
    }
    
    @Override
    public List<Specialty> getList(){
        return specialtyDao.getList();
    }
    
    @Override
    public Boolean update(Specialty specialty){
        return specialtyDao.update(specialty);
    }
    
    @Override
    public Boolean delete(Long specialtyId){
        return specialtyDao.delete(specialtyId);
    }
    
}
