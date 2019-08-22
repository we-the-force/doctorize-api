/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.data.catalogs;

import com.cmtb.doctorize.domain.catalogs.Specialty;
import java.util.List;

/**
 *
 * @author pc
 */
public interface SpecialtyDao {

    public Specialty save(Specialty specialty);
    
    public Specialty get(Long specialtyId);
    
    public List<Specialty> getList();
    
    public Boolean update(Specialty specialty);
    
    public Boolean delete(Long specialtyId);
    
}
