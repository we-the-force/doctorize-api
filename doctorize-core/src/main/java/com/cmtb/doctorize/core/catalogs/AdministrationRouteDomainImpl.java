/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.core.catalogs;

import com.cmtb.doctorize.data.catalogs.AdministrationRouteDao;
import com.cmtb.doctorize.domain.catalogs.AdministrationRoute;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 *
 * @author pc
 */
@Component(value = "AdministrationRouteDomain")
public class AdministrationRouteDomainImpl implements AdministrationRouteDomain {
    
    @Resource(name = "AdministrationRouteDao")
    private AdministrationRouteDao administrationRouteDao;
    
    @Override
    public AdministrationRoute save(AdministrationRoute administrationRoute){
        return administrationRouteDao.save(administrationRoute);
    }
    
    @Override
    public AdministrationRoute get(Long administrationRouteId) {
        return administrationRouteDao.get(administrationRouteId);
    }
    
    @Override
    public List<AdministrationRoute> getList() {
        return administrationRouteDao.getList();
    }
    
    @Override
    public Boolean update(AdministrationRoute administrationRoute){
        return administrationRouteDao.update(administrationRoute);
    }
    
    @Override
    public Boolean delete(Long administrationRouteId){
        return administrationRouteDao.delete(administrationRouteId);
    }
}
