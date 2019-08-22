/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.data.catalogs;

import com.cmtb.doctorize.domain.catalogs.Medicine;
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
@Repository("MedicineDao")
public class MedicineDaoImpl implements MedicineDao {
    
    @Autowired
    private SessionFactory _sessionFactory;

    private Session getSession() {
        return _sessionFactory.getCurrentSession();
    }
    
    @Override
    public Medicine save(Medicine medicine){
        try {
            Long newId = (long) this.getSession().save(medicine);
            medicine.setId(newId);
            return medicine;
        } catch (TransientPropertyValueException e) {
            throw new RequiredException(e.getPropertyName());
        }
    }
    
    @Override
    public Medicine get(Long medicineId) {
        String hql = "select M from Medicine M"
                + " where M.id =:medicineId";

        Query query = this.getSession().createQuery(hql);
        query.setLong("medicineId", medicineId);

        return (Medicine) query.uniqueResult();
    }
    
    @Override
    public List<Medicine> getList() {
        String hql = "select M from Medicine M";

        Query query = this.getSession().createQuery(hql);

        return (List<Medicine>) query.list();
    }
    
    @Override
    public Boolean update(Medicine medicine){
        StringBuilder builder = new StringBuilder();
        builder.append("update Medicine M");
        builder.append(" set M.name=:name");
        builder.append(" where (M.id=:medicineId)");
        
        String hql = builder.toString();

        Query query = this.getSession().createQuery(hql);
        query.setString("name", medicine.getName());
        
        query.setLong("medicineId", medicine.getId());
        
        int records=query.executeUpdate();
        
        return (records > 0);
    }
    
    @Override
    public Boolean delete(Long medicineId){
        String hql = "delete from Medicine M "
                + " where (M.id=:medicineId)";

        Query query = this.getSession().createQuery(hql);
        query.setLong("medicineId", medicineId);

        int affected = query.executeUpdate();
        
        return (affected > 0);
    }
    
}
