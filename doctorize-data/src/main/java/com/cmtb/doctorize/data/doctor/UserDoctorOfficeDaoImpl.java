/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.data.doctor;

import com.cmtb.doctorize.domain.shared.RequiredException;
import com.cmtb.doctorize.domain.user.UserDoctorOffice;
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
@Repository("UserDoctorOfficeDao")
public class UserDoctorOfficeDaoImpl implements UserDoctorOfficeDao {
    
    @Autowired
    private SessionFactory _sessionFactory;
    
    private Session getSession() {
        return _sessionFactory.getCurrentSession();
    }
    
    @Override
    public UserDoctorOffice save(UserDoctorOffice userDoctorOffice){
        try {
            Long newId = (long) this.getSession().save(userDoctorOffice);
            userDoctorOffice.setId(newId);
            return userDoctorOffice;
        } catch (TransientPropertyValueException e) {
            throw new RequiredException(e.getPropertyName());
        }
    }
}
