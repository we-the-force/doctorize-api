/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.core.specialty;

import com.cmtb.doctorize.data.specialty.SpecialtyDao;
import com.cmtb.doctorize.domain.specialty.Specialty;
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
    public List<Specialty> getList(){
        return specialtyDao.getList();
    }
    
}
