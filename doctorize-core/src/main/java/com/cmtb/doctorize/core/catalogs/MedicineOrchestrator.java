/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.core.catalogs;

import com.cmtb.doctorize.domain.catalogs.Medicine;

/**
 *
 * @author pc
 */
public interface MedicineOrchestrator {

    public Boolean delete(Long medicineId);

    public Medicine save(Medicine medicine);

    public Boolean update(Medicine medicine);
    
}
