/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.data.medicalAppointment;

import com.cmtb.doctorize.domain.medicalAppointment.MedicalAppointment;
import com.cmtb.doctorize.domain.shared.RequiredException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.TransientPropertyValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pc
 */
@Transactional
@Repository("MedicalAppointmentDao")
public class MedicalAppointmentDaoImpl implements MedicalAppointmentDao {
    
    @Autowired
    private SessionFactory _sessionFactory;

    private Session getSession() {
        return _sessionFactory.getCurrentSession();
    }
    
    @Override
    public MedicalAppointment save(MedicalAppointment medicalAppointment){
        try {
            Long newId = (long) this.getSession().save(medicalAppointment);
            medicalAppointment.setId(newId);
            return medicalAppointment;
        } catch (TransientPropertyValueException e) {
            throw new RequiredException(e.getPropertyName());
        }
    }
}
