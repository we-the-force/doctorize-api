/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.data.patient;

import com.cmtb.doctorize.domain.patient.Patient;
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
@Repository("PatientDao")
public class PatientDaoImpl implements PatientDao {
    
    @Autowired
    private SessionFactory _sessionFactory;

    private Session getSession() {
        return _sessionFactory.getCurrentSession();
    }
    
    @Override
    public Patient save(Patient patient){
        try {
            Long newId = (long) this.getSession().save(patient);
            patient.setId(newId);
            return patient;
        } catch (TransientPropertyValueException e) {
            throw new RequiredException(e.getPropertyName());
        }
    }
    
    @Override
    public Patient getPatientById(Long patientId) {
        String hql = "select P from Patient P"
                + " where P.id =:patientId";

        Query query = this.getSession().createQuery(hql);
        query.setLong("patientId", patientId);

        return (Patient) query.uniqueResult();
    }
}
