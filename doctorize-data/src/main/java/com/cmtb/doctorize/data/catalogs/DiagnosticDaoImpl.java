/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.data.catalogs;

import com.cmtb.doctorize.domain.catalogs.Diagnostic;
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
@Repository("DiagnosticDao")
public class DiagnosticDaoImpl implements DiagnosticDao {
    
    @Autowired
    private SessionFactory _sessionFactory;

    private Session getSession() {
        return _sessionFactory.getCurrentSession();
    }
    
    @Override
    public Diagnostic save(Diagnostic diagnostic){
        try {
            Long newId = (long) this.getSession().save(diagnostic);
            diagnostic.setId(newId);
            return diagnostic;
        } catch (TransientPropertyValueException e) {
            throw new RequiredException(e.getPropertyName());
        }
    }
    
    @Override
    public Diagnostic get(Long diagnosticId) {
        String hql = "select D from Diagnostic D"
                + " where D.id =:diagnosticId";

        Query query = this.getSession().createQuery(hql);
        query.setLong("diagnosticId", diagnosticId);

        return (Diagnostic) query.uniqueResult();
    }
    
    @Override
    public List<Diagnostic> getList() {
        String hql = "select D from Diagnostic D";

        Query query = this.getSession().createQuery(hql);

        return (List<Diagnostic>) query.list();
    }
    
    @Override
    public Boolean update(Diagnostic diagnostic){
        StringBuilder builder = new StringBuilder();
        builder.append("update Diagnostic D");
        builder.append(" set D.name=:name");
        builder.append(" where (D.id=:diagnosticId)");
        
        String hql = builder.toString();

        Query query = this.getSession().createQuery(hql);
        query.setString("name", diagnostic.getName());
        
        query.setLong("diagnosticId", diagnostic.getId());
        
        int records=query.executeUpdate();
        
        return (records > 0);
    }
    
    @Override
    public Boolean delete(Long diagnosticId){
        String hql = "delete from Diagnostic D "
                + " where (D.id=:diagnosticId)";

        Query query = this.getSession().createQuery(hql);
        query.setLong("diagnosticId", diagnosticId);

        int affected = query.executeUpdate();
        
        return (affected > 0);
    }
}
