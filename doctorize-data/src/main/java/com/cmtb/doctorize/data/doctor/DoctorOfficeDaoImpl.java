/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.data.doctor;

import com.cmtb.doctorize.domain.doctor.DoctorOffice;
import com.cmtb.doctorize.domain.doctor.DoctorOfficeDisplayObject;
import com.cmtb.doctorize.domain.shared.RequiredException;
import com.cmtb.doctorize.domain.shared.StatusEnum;
import java.util.List;
import java.util.Map;
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
    
    private String needComa(Boolean needComa){
        if(needComa){
            return ",";
        }else{
            needComa = true;
            return "";
        }
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
                + " where UDO.user.id=:userId and DO.status=:status";

        Query query = getSession().createQuery(hql);
        query.setByte("status", StatusEnum.ACTIVE.getId());
        query.setLong("userId", userId).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        
        
        return (List<DoctorOffice>) query.list();
    }
    
    @Override
    public DoctorOffice getById(Long doctorOfficeId){
        String hql = "select DO from DoctorOffice DO"
                + " left join fetch DO.availableDays"
                + " where DO.id=:doctorOfficeId and DO.status=:status";

        Query query = getSession().createQuery(hql);
        query.setByte("status", StatusEnum.ACTIVE.getId());
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
    
    @Override
    public boolean patch(Map<String, Object> doctorOfficeMap){
        Boolean needComa = false;
        
        StringBuilder builder = new StringBuilder();
        builder.append("update DoctorOffice DO");
        builder.append(" set");
        if(doctorOfficeMap.containsKey("name")){
            builder.append(needComa(needComa));
            builder.append(" DO.name=:name");
        }
        if(doctorOfficeMap.containsKey("email")){
            builder.append(needComa(needComa));
            builder.append(" DO.email=:email");
        }
        if(doctorOfficeMap.containsKey("phone")){
            builder.append(needComa(needComa));
            builder.append(" DO.phone=:phone");
        }
        if(doctorOfficeMap.containsKey("hospital")){
            builder.append(needComa(needComa));
            builder.append(" DO.hospital=:hospital");
        }
        if(doctorOfficeMap.containsKey("number")){
            builder.append(needComa(needComa));
            builder.append(" DO.number=:number");
        }
        if(doctorOfficeMap.containsKey("address")){
            builder.append(needComa(needComa));
            builder.append(" DO.address=:address");
        }
        if(doctorOfficeMap.containsKey("lat") && doctorOfficeMap.get("lat") != null){
            builder.append(needComa(needComa));
            builder.append(" DO.lat=:lat");
        }
        if(doctorOfficeMap.containsKey("lng") && doctorOfficeMap.get("lng") != null){
            builder.append(needComa(needComa));
            builder.append(" DO.lng=:lng");
        }
        if(doctorOfficeMap.containsKey("startTime")){
            builder.append(needComa(needComa));
            builder.append(" DO.startTime=:startTime");
        }
        if(doctorOfficeMap.containsKey("closeTime")){
            builder.append(needComa(needComa));
            builder.append(" DO.closeTime=:closeTime");
        }
        if(doctorOfficeMap.containsKey("lunchStartTime")){
            builder.append(needComa(needComa));
            builder.append(" DO.lunchStartTime=:lunchStartTime");
        }
        if(doctorOfficeMap.containsKey("lunchCloseTime")){
            builder.append(needComa(needComa));
            builder.append(" DO.lunchCloseTime=:lunchCloseTime");
        }
        if(doctorOfficeMap.containsKey("duration")){
            builder.append(needComa(needComa));
            builder.append(" DO.duration=:duration");
        }
        
        
        
        builder.append(" where (DO.id=:doctorOfficeId)");
        
        String hql = builder.toString();

        
        Query query = this.getSession().createQuery(hql);
        if(doctorOfficeMap.containsKey("name")){
            query.setString("name", doctorOfficeMap.get("name").toString());
        }
        if(doctorOfficeMap.containsKey("email")){
            query.setString("email", doctorOfficeMap.get("email").toString());
        }
        if(doctorOfficeMap.containsKey("phone")){
            query.setString("phone", doctorOfficeMap.get("phone").toString());
        }
        if(doctorOfficeMap.containsKey("hospital")){
            query.setString("hospital", doctorOfficeMap.get("hospital").toString());
        }
        if(doctorOfficeMap.containsKey("number")){
            query.setString("number", doctorOfficeMap.get("number").toString());
        }
        if(doctorOfficeMap.containsKey("address")){
            query.setString("address", doctorOfficeMap.get("address").toString());
        }
        if(doctorOfficeMap.containsKey("lat") && doctorOfficeMap.get("lat") != null){
            query.setDouble("lat", (Double)doctorOfficeMap.get("lat"));
        }
        if(doctorOfficeMap.containsKey("lng") && doctorOfficeMap.get("lng") != null){
            query.setDouble("lng", (Double)doctorOfficeMap.get("lng"));
        }
        if(doctorOfficeMap.containsKey("startTime")){
            query.setString("startTime", doctorOfficeMap.get("startTime").toString());
        }
        if(doctorOfficeMap.containsKey("closeTime")){
            query.setString("closeTime", doctorOfficeMap.get("closeTime").toString());
        }
        if(doctorOfficeMap.containsKey("lunchStartTime")){
            query.setString("lunchStartTime", doctorOfficeMap.get("lunchStartTime").toString());
        }
        if(doctorOfficeMap.containsKey("lunchCloseTime")){
            query.setString("lunchCloseTime", doctorOfficeMap.get("lunchCloseTime").toString());
        }
        if(doctorOfficeMap.containsKey("duration")){
            query.setShort("duration", ((Integer)doctorOfficeMap.get("duration")).shortValue());
        }
        
        query.setLong("doctorOfficeId", ((Integer)doctorOfficeMap.get("id")).longValue());
        
        int records=query.executeUpdate();
        
        return (records > 0);
    }
    
    @Override
    public Boolean delete(Long doctorOfficeId){
        String hql = "update DoctorOffice DO"
                + " set DO.status=:status"
                + " where (DO.id=:doctorOfficeId)";

        Query query = this.getSession().createQuery(hql);
        query.setLong("doctorOfficeId", doctorOfficeId);     
        query.setByte("status", StatusEnum.DISABLE.getId());
        
        int records=query.executeUpdate();
        
        return (records > 0);
    }
}
