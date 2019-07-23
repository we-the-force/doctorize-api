/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.core.doctorOffice;

import com.cmtb.doctorize.domain.doctor.AvailableDays;
import com.cmtb.doctorize.domain.doctor.DoctorOffice;
import com.cmtb.doctorize.domain.doctor.DoctorOfficeDisplayObject;
import com.cmtb.doctorize.domain.user.User;
import com.cmtb.doctorize.domain.doctor.UserDoctorOffice;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pc
 */
@Component(value = "DoctorOfficeOrchestrator")
public class DoctorOfficeOrchestratorImpl implements DoctorOfficeOrchestrator {
    
    @Resource(name = "DoctorOfficeDomain")
    private DoctorOfficeDomain doctorOfficeDomain;
    
    @Resource(name = "UserDoctorOfficeDomain")
    private UserDoctorOfficeDomain userDoctorOfficeDomain;
    
    @Transactional
    @Override
    public DoctorOffice save(DoctorOfficeDisplayObject doctorOfficeDisplayObject){
        
        DoctorOffice newOffice = new DoctorOffice();

        newOffice.setId(doctorOfficeDisplayObject.getId());
        newOffice.setName(doctorOfficeDisplayObject.getName());
        newOffice.setAddress(doctorOfficeDisplayObject.getAddress());
        newOffice.setNumber(doctorOfficeDisplayObject.getNumber());
        newOffice.setEmail(doctorOfficeDisplayObject.getEmail());
        newOffice.setLat(doctorOfficeDisplayObject.getLat());
        newOffice.setLng(doctorOfficeDisplayObject.getLng());
        newOffice.setHospital(doctorOfficeDisplayObject.getHospital());

        if (!doctorOfficeDisplayObject.getStartTime().contains(":") || doctorOfficeDisplayObject.getStartTime() == null) {
            throw new IllegalArgumentException("Hora de apertura es requerida");
        }
        
        newOffice.setStartTime(doctorOfficeDisplayObject.getStartTime());

        if (!doctorOfficeDisplayObject.getCloseTime().contains(":") || doctorOfficeDisplayObject.getCloseTime() == null) {
            throw new IllegalArgumentException("Hora de cierre es requerida");
        }
        
        newOffice.setCloseTime(doctorOfficeDisplayObject.getCloseTime());
        
        
        if (doctorOfficeDisplayObject.getLunchStartTime() != null && !doctorOfficeDisplayObject.getLunchStartTime().contains(":")) {
            throw new IllegalArgumentException("Hora de inicio de comida incorrecta");
        }
        
        newOffice.setLunchStartTime(doctorOfficeDisplayObject.getLunchStartTime());

        if (doctorOfficeDisplayObject.getLunchCloseTime() != null && !doctorOfficeDisplayObject.getLunchCloseTime().contains(":")) {
            throw new IllegalArgumentException("Hora de fin de comida incorrecta");
        }
        
        newOffice.setLunchCloseTime(doctorOfficeDisplayObject.getLunchCloseTime());
        

        newOffice.setPhone(doctorOfficeDisplayObject.getPhone());

        List<AvailableDays> days = new ArrayList<>();
        for (Byte day : doctorOfficeDisplayObject.getDays()) {
            AvailableDays availableDay = new AvailableDays();
            availableDay.setDay(day);
            days.add(availableDay);
        }

        newOffice.setAvailableDays(new HashSet<>(days));
        
        doctorOfficeDomain.save(newOffice);
        
        if(doctorOfficeDisplayObject.getId() == null){
            UserDoctorOffice userDoctorOffice = new UserDoctorOffice();
            User user = new User();
            user.setId(doctorOfficeDisplayObject.getUserId());
            userDoctorOffice.setUser(user);
            userDoctorOffice.setDoctorOffice(newOffice);

            userDoctorOfficeDomain.save(userDoctorOffice);
        }
        
        
        return newOffice;
    }
    
    @Transactional
    @Override
    public Boolean delete(Long doctorOfficeId){
        return doctorOfficeDomain.delete(doctorOfficeId);
    }
}
