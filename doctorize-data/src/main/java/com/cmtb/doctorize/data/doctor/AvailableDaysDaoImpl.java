/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.data.doctor;

import com.cmtb.doctorize.domain.doctor.AvailableDays;
import com.cmtb.doctorize.domain.shared.RequiredException;
import org.hibernate.Query;
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
@Repository("AvailableDaysDao")
public class AvailableDaysDaoImpl implements AvailableDaysDao {
    
    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void saveAvailableDay(AvailableDays day) {
        try {
            this.getCurrentSession().save(day);
        } catch (TransientPropertyValueException e) {
            throw new RequiredException(e.getPropertyName());
        }
    }
    
    @Override
    public void deleteAvailableDaysByDoctorOffice(Long doctorOfficeId) {
        String hql = "delete from AvailableDays d"
                + " where (d.office.id=:doctorOfficeId)";
 
        Query query = this.getCurrentSession().createQuery(hql);
        query.setLong("doctorOfficeId", doctorOfficeId);
        int results = query.executeUpdate();
        System.out.println(results);
    }
}
