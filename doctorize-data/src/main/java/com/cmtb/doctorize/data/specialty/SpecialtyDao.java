/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.data.specialty;

import com.cmtb.doctorize.domain.specialty.Specialty;
import java.util.List;

/**
 *
 * @author pc
 */
public interface SpecialtyDao {

    public Specialty save(Specialty specialty);
    
    public List<Specialty> getList();
    
}
