/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.core.doctorOffice;

import com.cmtb.doctorize.data.doctor.AvailableDaysDao;
import com.cmtb.doctorize.data.doctor.DoctorOfficeDao;
import com.cmtb.doctorize.domain.doctor.AvailableDays;
import com.cmtb.doctorize.domain.doctor.DoctorOffice;
import com.cmtb.doctorize.domain.doctor.DoctorOfficeDisplayObject;
import com.cmtb.doctorize.domain.user.User;
import com.cmtb.doctorize.domain.doctor.UserDoctorOffice;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 *
 * @author pc
 */
@Component(value = "DoctorOfficeDomain")
public class DoctorOfficeDomainImpl implements DoctorOfficeDomain {
    
    @Resource(name = "DoctorOfficeDao")
    private DoctorOfficeDao doctorOfficeDao;
    
    @Resource(name = "AvailableDaysDao")
    private AvailableDaysDao availableDaysDao;
    
    @Resource(name = "UserDoctorOfficeDomain")
    private UserDoctorOfficeDomain userDoctorOfficeDomain;
    
    private DoctorOfficeDisplayObject doctorOfficeDisplayObjectAssembler(DoctorOffice doctorOfficeItem, Long userId){
        
            DoctorOfficeDisplayObject doctorOfficeDisplayObject = new DoctorOfficeDisplayObject();
            doctorOfficeDisplayObject.setAddress(doctorOfficeItem.getAddress());
            doctorOfficeDisplayObject.setCloseTime(doctorOfficeItem.getCloseTime());
            
            for(AvailableDays dayItem: doctorOfficeItem.getAvailableDays()){
                doctorOfficeDisplayObject.getDays().add(dayItem.getDay());
            }
            
            doctorOfficeDisplayObject.setEmail(doctorOfficeItem.getEmail());
            doctorOfficeDisplayObject.setHospital(doctorOfficeItem.getHospital());
            doctorOfficeDisplayObject.setId(doctorOfficeItem.getId());
            doctorOfficeDisplayObject.setLat(doctorOfficeItem.getLat());
            doctorOfficeDisplayObject.setLng(doctorOfficeItem.getLng());
            doctorOfficeDisplayObject.setLunchCloseTime(doctorOfficeItem.getLunchCloseTime());
            doctorOfficeDisplayObject.setLunchStartTime(doctorOfficeItem.getLunchStartTime());
            doctorOfficeDisplayObject.setName(doctorOfficeItem.getName());
            doctorOfficeDisplayObject.setNumber(doctorOfficeItem.getNumber());
            doctorOfficeDisplayObject.setPhone(doctorOfficeItem.getPhone());
            doctorOfficeDisplayObject.setStartTime(doctorOfficeItem.getStartTime());
            doctorOfficeDisplayObject.setUserId(userId);
        
        return doctorOfficeDisplayObject;
    }
    
    @Override
    public DoctorOffice save(DoctorOffice doctorOffice){
        
        Boolean isNew = (doctorOffice.getId() == null || doctorOffice.getId() <= 0);
        
        if(isNew){
            doctorOfficeDao.save(doctorOffice);
        }else{
            availableDaysDao.deleteAvailableDaysByDoctorOffice(doctorOffice.getId());
        }
        
        for (AvailableDays day : doctorOffice.getAvailableDays()) {
            DoctorOffice myOffice = new DoctorOffice();
            myOffice.setId(doctorOffice.getId());

            day.setOffice(myOffice);
            availableDaysDao.saveAvailableDay(day);
        }
        
        return doctorOffice;
    }
    
    @Override
    public List<DoctorOfficeDisplayObject> getListByUserId(Long userId){
        
        List<DoctorOffice> doctorOffices = doctorOfficeDao.getListByUserId(userId);
        
        List<DoctorOfficeDisplayObject> doctorOfficesDisplayObject = new ArrayList<>();
        
        for(DoctorOffice doctorOfficeItem: doctorOffices){
            doctorOfficesDisplayObject.add(this.doctorOfficeDisplayObjectAssembler(doctorOfficeItem, userId));
        }
        
        return doctorOfficesDisplayObject;
    }
    
    @Override
    public DoctorOfficeDisplayObject getById(Long doctorOfficeId){
        
        DoctorOffice doctorOffice = doctorOfficeDao.getById(doctorOfficeId);
        
        return this.doctorOfficeDisplayObjectAssembler(doctorOffice, null);
    }
    
}
