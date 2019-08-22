/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.data.catalogs;

import com.cmtb.doctorize.domain.shared.RequiredException;
import com.cmtb.doctorize.domain.catalogs.Specialty;
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
    public Specialty get(Long specialtyId) {
        String hql = "select S from Specialty S"
                + " where S.id =:specialtyId";

        Query query = this.getSession().createQuery(hql);
        query.setLong("specialtyId", specialtyId);

        return (Specialty) query.uniqueResult();
    }
    
    @Override
    public List<Specialty> getList(){
        String hql = "select S from Specialty S";

        Query query = getSession().createQuery(hql);
        
        return (List<Specialty>) query.list();
    }
    
    @Override
    public Boolean update(Specialty specialty){
        StringBuilder builder = new StringBuilder();
        builder.append("update Specialty S");
        builder.append(" set S.name=:name");
        builder.append(" where (S.id=:specialtyId)");
        
        String hql = builder.toString();

        Query query = this.getSession().createQuery(hql);
        query.setString("name", specialty.getName());
        
        query.setLong("specialtyId", specialty.getId());
        
        int records=query.executeUpdate();
        
        return (records > 0);
    }
    
    @Override
    public Boolean delete(Long specialtyId){
        String hql = "delete from Specialty S "
                + " where (S.id=:specialtyId)";

        Query query = this.getSession().createQuery(hql);
        query.setLong("specialtyId", specialtyId);

        int affected = query.executeUpdate();
        
        return (affected > 0);
    }
    
}
