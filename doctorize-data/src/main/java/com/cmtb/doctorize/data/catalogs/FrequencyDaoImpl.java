/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.data.catalogs;

import com.cmtb.doctorize.domain.catalogs.Frequency;
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
@Repository("FrequencyDao")
public class FrequencyDaoImpl implements FrequencyDao {
    
    @Autowired
    private SessionFactory _sessionFactory;

    private Session getSession() {
        return _sessionFactory.getCurrentSession();
    }
    
    @Override
    public Frequency save(Frequency frequency){
        try {
            Long newId = (long) this.getSession().save(frequency);
            frequency.setId(newId);
            return frequency;
        } catch (TransientPropertyValueException e) {
            throw new RequiredException(e.getPropertyName());
        }
    }
    
    @Override
    public Frequency get(Long frequencyId) {
        String hql = "Select F from Frequency F"
                + " where F.id =:frequencyId";

        Query query = this.getSession().createQuery(hql);
        query.setLong("frequencyId", frequencyId);

        return (Frequency) query.uniqueResult();
    }
    
    @Override
    public List<Frequency> getList() {
        String hql = "select F from Frequency F";

        Query query = this.getSession().createQuery(hql);

        return (List<Frequency>) query.list();
    }
    
    @Override
    public Boolean update(Frequency frequency){
        StringBuilder builder = new StringBuilder();
        builder.append("update Frequency F");
        builder.append(" set F.name=:name");
        builder.append(" where (F.id=:frequencyId)");
        
        String hql = builder.toString();

        Query query = this.getSession().createQuery(hql);
        query.setString("name", frequency.getName());
        
        query.setLong("frequencyId", frequency.getId());
        
        int records=query.executeUpdate();
        
        return (records > 0);
    }
    
    @Override
    public Boolean delete(Long frequencyId){
        String hql = "delete from Frequency F "
                + " where (F.id=:frequencyId)";

        Query query = this.getSession().createQuery(hql);
        query.setLong("frequencyId", frequencyId);

        int affected = query.executeUpdate();
        
        return (affected > 0);
    }
}
