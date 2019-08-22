/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.core.catalogs;

import com.cmtb.doctorize.domain.catalogs.Medicine;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pc
 */
@Component(value = "MedicineOrchestrator")
public class MedicineOrchestratorImpl implements MedicineOrchestrator  {
    
    @Resource(name = "MedicineDomain")
    private MedicineDomain medicineDomain;
    
    @Transactional
    @Override
    public Medicine save(Medicine medicine){
        return medicineDomain.save(medicine);
    }
    
    @Transactional
    @Override
    public Boolean delete(Long medicineId){
        return medicineDomain.delete(medicineId);
    }
    
    @Transactional
    @Override
    public Boolean update(Medicine medicine){
        return medicineDomain.update(medicine);
    }
}
