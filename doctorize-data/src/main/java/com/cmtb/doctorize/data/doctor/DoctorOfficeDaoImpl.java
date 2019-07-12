/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.data.doctor;

import com.cmtb.doctorize.domain.doctor.DoctorOffice;
import com.cmtb.doctorize.domain.doctor.DoctorOfficeDisplayObject;
import com.cmtb.doctorize.domain.shared.RequiredException;
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
                + " left join fetch DO.availableDays"
                + " join fetch DO.userDoctorOffices UDO"
                + " where UDO.user.id=:userId";

        Query query = getSession().createQuery(hql);
        query.setLong("userId", userId).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        
        return (List<DoctorOffice>) query.list();
    }
    
    @Override
    public DoctorOffice getById(Long doctorOfficeId){
        String hql = "select DO from DoctorOffice DO"
                + " left join fetch DO.availableDays"
                + " where DO.id=:doctorOfficeId";

        Query query = getSession().createQuery(hql);
        query.setLong("doctorOfficeId", doctorOfficeId);
        
        return (DoctorOffice) query.uniqueResult();
    }
    
    @Override
    public boolean update(DoctorOffice doctorOffice){
        
        String hql = "update DoctorOffice DO"
                + " set DO.name=:name, DO.email=:email,"
                + " DO.phone=:phone, DO.hospital=:hospital,"
                + " DO.number=:number, DO.address=:address,"
                + " DO.lat=:lat, DO.lng=:lng,"
                + " DO.startTime, DO.closeTime,"
                + " DO.lunchStartTime=:lunchStartTime,"
                + " DO.lunchCloseTime=:lunchCloseTime"
                + " where (DO.id=:doctorOfficeId)";

        Query query = this.getSession().createQuery(hql);
        query.setString("name", user.getName());
        query.setString("email", user.getEmail());
        query.setString("cellphone", user.getCellphone());
        
        query.setLong("userId", user.getId());
        
        int records=query.executeUpdate();
        
        return (records > 0);
    }
}
