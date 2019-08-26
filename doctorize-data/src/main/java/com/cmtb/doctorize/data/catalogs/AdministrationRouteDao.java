/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.data.catalogs;

import com.cmtb.doctorize.domain.catalogs.AdministrationRoute;
import java.util.List;

/**
 *
 * @author pc
 */
public interface AdministrationRouteDao {

    public Boolean delete(Long administrationRouteId);

    public AdministrationRoute get(Long administrationRouteId);

    public List<AdministrationRoute> getList();

    public AdministrationRoute save(AdministrationRoute administrationRoute);

    public Boolean update(AdministrationRoute administrationRoute);
    
}
