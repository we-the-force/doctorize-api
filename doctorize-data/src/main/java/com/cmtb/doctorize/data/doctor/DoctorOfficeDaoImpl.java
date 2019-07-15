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
        
        StringBuilder builder = new StringBuilder();
        builder.append("update DoctorOffice DO");
        builder.append(" set DO.name=:name,");
        builder.append(" DO.email=:email,");
        builder.append(" DO.phone=:phone,");
        builder.append(" DO.hospital=:hospital,");
        builder.append(" DO.number=:number,");
        builder.append(" DO.address=:address,");
        if(doctorOffice.getLat() != null){
            builder.append(" DO.lat=:lat,");
        }
        if(doctorOffice.getLng() != null){
            builder.append(" DO.lng=:lng,");
        }
        builder.append(" DO.startTime=:startTime,");
        builder.append(" DO.closeTime=:closeTime,");
        builder.append(" DO.lunchStartTime=:lunchStartTime,");
        builder.append(" DO.lunchCloseTime=:lunchCloseTime");
        builder.append(" where (DO.id=:doctorOfficeId)");
        
        String hql = builder.toString();

        
        Query query = this.getSession().createQuery(hql);
        query.setString("name", doctorOffice.getName());
        query.setString("email", doctorOffice.getEmail());
        query.setString("phone", doctorOffice.getPhone());
        query.setString("hospital", doctorOffice.getHospital());
        query.setString("number", doctorOffice.getNumber());
        query.setString("address", doctorOffice.getAddress());
        if(doctorOffice.getLat() != null){
            query.setDouble("lat", doctorOffice.getLat());
        }
        if(doctorOffice.getLng() != null){
            query.setDouble("lng", doctorOffice.getLng());
        }
        query.setString("startTime", doctorOffice.getStartTime());
        query.setString("closeTime", doctorOffice.getCloseTime());
        query.setString("lunchStartTime", doctorOffice.getLunchStartTime());
        query.setString("lunchCloseTime", doctorOffice.getLunchCloseTime());
        
        query.setLong("doctorOfficeId", doctorOffice.getId());
        
        int records=query.executeUpdate();
        
        return (records > 0);
    }
}
