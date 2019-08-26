/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.data.catalogs;

import com.cmtb.doctorize.domain.catalogs.AdministrationRoute;
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
@Repository("AdministrationRouteDao")
public class AdministrationRouteDaoImpl implements AdministrationRouteDao {
    
    @Autowired
    private SessionFactory _sessionFactory;

    private Session getSession() {
        return _sessionFactory.getCurrentSession();
    }
    
    @Override
    public AdministrationRoute save(AdministrationRoute administrationRoute){
        try {
            Long newId = (long) this.getSession().save(administrationRoute);
            administrationRoute.setId(newId);
            return administrationRoute;
        } catch (TransientPropertyValueException e) {
            throw new RequiredException(e.getPropertyName());
        }
    }
    
    @Override
    public AdministrationRoute get(Long administrationRouteId) {
        String hql = "select AR from AdministrationRoute AR"
                + " where AR.id =:administrationRouteId";

        Query query = this.getSession().createQuery(hql);
        query.setLong("administrationRouteId", administrationRouteId);

        return (AdministrationRoute) query.uniqueResult();
    }
    
    @Override
    public List<AdministrationRoute> getList() {
        String hql = "select AR from AdministrationRoute AR";

        Query query = this.getSession().createQuery(hql);

        return (List<AdministrationRoute>) query.list();
    }
    
    @Override
    public Boolean update(AdministrationRoute administrationRoute){
        StringBuilder builder = new StringBuilder();
        builder.append("update AdministrationRoute AR");
        builder.append(" set AR.name=:name");
        builder.append(" where (AR.id=:administrationRouteId)");
        
        String hql = builder.toString();

        Query query = this.getSession().createQuery(hql);
        query.setString("name", administrationRoute.getName());
        
        query.setLong("administrationRouteId", administrationRoute.getId());
        
        int records=query.executeUpdate();
        
        return (records > 0);
    }
    
    @Override
    public Boolean delete(Long administrationRouteId){
        String hql = "delete from AdministrationRoute AR "
                + " where (AR.id=:administrationRouteId)";

        Query query = this.getSession().createQuery(hql);
        query.setLong("administrationRouteId", administrationRouteId);

        int affected = query.executeUpdate();
        
        return (affected > 0);
    }
}
