/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.data.doctor;

import com.cmtb.doctorize.domain.doctor.DoctorOffice;
import com.cmtb.doctorize.domain.shared.RequiredException;
import java.util.List;
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
@Repository("DoctorOfficeDao")
public class DoctorOfficeDaoImpl implements DoctorOfficeDao {
    
    @Autowired
    private SessionFactory _sessionFactory;
    
    private Session getSession() {
        return _sessionFactory.getCurrentSession();
    }
    
    @Override
    public DoctorOffice save(DoctorOffice doctorOffice){
        try {
            Long newId = (long) this.getSession().save(doctorOffice);
            doctorOffice.setId(newId);
            return doctorOffice;
        } catch (TransientPropertyValueException e) {
            throw new RequiredException(e.getPropertyName());
        }
    }
    
    @Override
    public List<DoctorOffice> getListByUserId(Long userId){
        String hql = "select DO from DoctorOffice DO"
                + " join fetch user DOU"
                + " where DOU.id=:userId";

        Query query = getSession().createQuery(hql);
        
        return (List<DoctorOffice>) query.list();
    }
}
