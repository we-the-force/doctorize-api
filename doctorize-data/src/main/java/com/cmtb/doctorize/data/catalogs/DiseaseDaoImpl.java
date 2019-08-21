/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.data.catalogs;

import com.cmtb.doctorize.domain.catalogs.Disease;
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
@Repository("DiseaseDao")
public class DiseaseDaoImpl implements DiseaseDao {
    
    @Autowired
    private SessionFactory _sessionFactory;

    private Session getSession() {
        return _sessionFactory.getCurrentSession();
    }
    
    @Override
    public Disease save(Disease disease){
        try {
            Long newId = (long) this.getSession().save(disease);
            disease.setId(newId);
            return disease;
        } catch (TransientPropertyValueException e) {
            throw new RequiredException(e.getPropertyName());
        }
    }
    
    @Override
    public Disease get(Long diseaseId) {
        String hql = "select D from Disease D"
                + " where P.id =:diseaseId";

        Query query = this.getSession().createQuery(hql);
        query.setLong("diseaseId", diseaseId);

        return (Disease) query.uniqueResult();
    }
    
    @Override
    public List<Disease> getList() {
        String hql = "select D from Disease D";

        Query query = this.getSession().createQuery(hql);

        return (List<Disease>) query.list();
    }
    
    @Override
    public Boolean update(Disease disease){
        StringBuilder builder = new StringBuilder();
        builder.append("update Disease D");
        builder.append(" set D.name=:name");
        builder.append(" where (D.id=:diseaseId)");
        
        String hql = builder.toString();

        Query query = this.getSession().createQuery(hql);
        query.setString("photo", disease.getName());
        
        query.setLong("diseaseId", disease.getId());
        
        int records=query.executeUpdate();
        
        return (records > 0);
    }
    
    @Override
    public Boolean delete(Long diseaseId){
        String hql = "delete from Disease D "
                + " where (D.id=:diseaseId)";

        Query query = this.getSession().createQuery(hql);
        query.setLong("diseaseId", diseaseId);

        int affected = query.executeUpdate();
        
        return (affected > 0);
    }
}
