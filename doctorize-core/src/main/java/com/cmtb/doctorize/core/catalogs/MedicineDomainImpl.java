/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.core.catalogs;

import com.cmtb.doctorize.data.catalogs.MedicineDao;
import com.cmtb.doctorize.domain.catalogs.Medicine;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 *
 * @author pc
 */
@Component(value = "MedicineDomain")
public class MedicineDomainImpl implements MedicineDomain {
    
    @Resource(name = "MedicineDao")
    private MedicineDao medicineDao;
    
    @Override
    public Medicine save(Medicine medicine){
        return medicineDao.save(medicine);
    }
    
    @Override
    public Medicine get(Long medicineId) {
        return medicineDao.get(medicineId);
    }
    
    @Override
    public List<Medicine> getList() {
        return medicineDao.getList();
    }
    
    @Override
    public Boolean update(Medicine medicine){
        return medicineDao.update(medicine);
    }
    
    @Override
    public Boolean delete(Long medicineId){
        return medicineDao.delete(medicineId);
    }
}
