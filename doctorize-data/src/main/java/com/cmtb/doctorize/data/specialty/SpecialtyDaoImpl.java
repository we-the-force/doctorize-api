/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.data.specialty;

import com.cmtb.doctorize.domain.shared.RequiredException;
import com.cmtb.doctorize.domain.specialty.Specialty;
import java.util.List;
import org.hibernate.Criteria;
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
@Repository("SpecialtyDao")
public class SpecialtyDaoImpl implements SpecialtyDao {
    
    @Autowired
    private SessionFactory _sessionFactory;

    private Session getSession() {
        return _sessionFactory.getCurrentSession();
    }
    
    @Override
    public Specialty save(Specialty specialty){
        try {
            Long newId = (long) this.getSession().save(specialty);
            specialty.setId(newId);
            return specialty;
        } catch (TransientPropertyValueException e) {
            throw new RequiredException(e.getPropertyName());
        }
    }
    
    @Override
    public List<Specialty> getList(){
        String hql = "select S from Specialty S";

        Query query = getSession().createQuery(hql);
        
        return (List<Specialty>) query.list();
    }
    
    
}
