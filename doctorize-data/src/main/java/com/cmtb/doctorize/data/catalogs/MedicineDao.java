/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.data.catalogs;

import com.cmtb.doctorize.domain.catalogs.Medicine;
import java.util.List;

/**
 *
 * @author pc
 */
public interface MedicineDao {

    public Boolean delete(Long medicineId);

    public Medicine get(Long medicineId);

    public List<Medicine> getList();

    public Medicine save(Medicine medicine);

    public Boolean update(Medicine medicine);
    
}
