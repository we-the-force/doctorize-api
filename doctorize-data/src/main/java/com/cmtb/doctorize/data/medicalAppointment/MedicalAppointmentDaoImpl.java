/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.data.medicalAppointment;

import com.cmtb.doctorize.domain.medicalAppointment.MedicalAppoinmentFilterDisplayObject;
import com.cmtb.doctorize.domain.medicalAppointment.MedicalAppointment;
import com.cmtb.doctorize.domain.shared.RequiredException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@Repository("MedicalAppointmentDao")
public class MedicalAppointmentDaoImpl implements MedicalAppointmentDao {
    
    private Boolean needComa = false;
    
    @Autowired
    private SessionFactory _sessionFactory;

    private Session getSession() {
        return _sessionFactory.getCurrentSession();
    }
    
    private String needComa(){
        if(needComa){
            return ",";
        }else{
            needComa = true;
            return "";
        }
    }
    
    @Override
    public MedicalAppointment save(MedicalAppointment medicalAppointment){
        try {
            Long newId = (long) this.getSession().save(medicalAppointment);
            medicalAppointment.setId(newId);
            return medicalAppointment;
        } catch (TransientPropertyValueException e) {
            throw new RequiredException(e.getPropertyName());
        }
    }
    
    @Override
    public List<MedicalAppointment> getListByDoctorId(Long doctorId){
        
        String hql = "select MA from MedicalAppointment MA"
                + " left join fetch MA.doctor"
                + " left join fetch MA.doctorOffice"
                + " where MA.doctor.id =:doctorId";

        Query query = this.getSession().createQuery(hql);
        query.setLong("doctorId", doctorId);
        
        query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        return (List<MedicalAppointment>) query.list(); 
    }
    
    @Override
    public List<MedicalAppointment> getListByFilter(MedicalAppoinmentFilterDisplayObject filterDO){
        
        StringBuilder builder = new StringBuilder();
        builder.append("select MA from MedicalAppointment MA");
        builder.append(" left join fetch MA.doctor");
        builder.append(" left join fetch MA.doctorOffice");
        builder.append(" where MA.doctor.id =:doctorId");
        if(filterDO.getEmail() != null && !filterDO.getEmail().equals("")){
            builder.append(" and MA.email =:email");
        }
        if(filterDO.getOffice() != null){
            builder.append(" and MA.doctorOffice.id =:officeId");
        }
        if(filterDO.getStartDate() != null){
            builder.append(" and MA.date >=:startDate");
            builder.append(" and MA.date <=:endDate");
        }
        
        
        String hql = builder.toString();

        Query query = this.getSession().createQuery(hql);
        query.setLong("doctorId", filterDO.getDoctorId());
        if(filterDO.getEmail() != null && !filterDO.getEmail().equals("")){
            query.setString("email", filterDO.getEmail());
        }
        if(filterDO.getOffice() != null){
            query.setLong("officeId", filterDO.getOffice());
        }
        if(filterDO.getStartDate() != null){
            builder.append(" and MA.date >=:startDate");
            builder.append(" and MA.date <=:endDate");
            query.setTimestamp("startDate", filterDO.getStartDate());
            query.setTimestamp("endDate", filterDO.getEndDate());
        }
        
        
        query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        return (List<MedicalAppointment>) query.list(); 
    }
    
    @Override
    public MedicalAppointment getById(Long appointmentId){
        
        String hql = "select MA from MedicalAppointment MA"
                + " left join fetch MA.doctor"
                + " left join fetch MA.doctorOffice"
                + " where MA.id =:appointmentId";

        Query query = this.getSession().createQuery(hql);
        query.setLong("appointmentId", appointmentId);

        return (MedicalAppointment) query.uniqueResult(); 
    }
    
    @Override
    public Boolean delete(Long appointmentId){
        String hql = "delete from MedicalAppointment MA "
                + " where (MA.id=:appointmentId)";

        Query query = this.getSession().createQuery(hql);
        query.setLong("appointmentId", appointmentId);

        int affected = query.executeUpdate();
        
        return (affected > 0);
    }
    
    @Override
    public Boolean deleteByPatientId(Long patientId){
        String hql = "delete from MedicalAppointment MA "
                + " where (MA.patient.id=:patientId)";

        Query query = this.getSession().createQuery(hql);
        query.setLong("patientId", patientId);

        int affected = query.executeUpdate();
        
        return (affected > 0);
    }
    
    @Override
    public Boolean patch(Map<String, Object> medicalAppointmentMap){
        needComa = false;
        
        StringBuilder builder = new StringBuilder();
        builder.append("update MedicalAppointment MA");
        builder.append(" set");
        if(medicalAppointmentMap.containsKey("name")){
            builder.append(needComa());
            builder.append(" MA.name=:name");
        }
        if(medicalAppointmentMap.containsKey("email")){
            builder.append(needComa());
            builder.append(" MA.email=:email");
        }
        if(medicalAppointmentMap.containsKey("phone")){
            builder.append(needComa());
            builder.append(" MA.phone=:phone");
        }
        if(medicalAppointmentMap.containsKey("date")){
            builder.append(needComa());
            builder.append(" MA.date=:date");
        }
        if(medicalAppointmentMap.containsKey("patientId")){
            builder.append(needComa());
            builder.append(" MA.patient.id=:patientId");
        }
        if(medicalAppointmentMap.containsKey("officeId")){
            builder.append(needComa());
            builder.append(" MA.doctorOffice.id=:officeId");
        }
        
        builder.append(" where (MA.id=:appointmentId)");
        
        String hql = builder.toString();

        Query query = this.getSession().createQuery(hql);
        if(medicalAppointmentMap.containsKey("name")){
            query.setString("name", medicalAppointmentMap.get("name").toString());
        }
        if(medicalAppointmentMap.containsKey("email")){
            query.setString("email", medicalAppointmentMap.get("email").toString());
        }
        if(medicalAppointmentMap.containsKey("phone")){
            query.setString("phone", medicalAppointmentMap.get("phone").toString());
        }
        if(medicalAppointmentMap.containsKey("date")){
            SimpleDateFormat formatter =new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
            Date date;
            try {
                date = formatter.parse(medicalAppointmentMap.get("date").toString());
                query.setTimestamp("date", date);
            } catch (ParseException ex) {
                Logger.getLogger(MedicalAppointmentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }   
        }
        if(medicalAppointmentMap.containsKey("officeId")){
            query.setLong("officeId", ((Integer)medicalAppointmentMap.get("officeId")).longValue());
        }
        if(medicalAppointmentMap.containsKey("patientId")){
            query.setLong("patientId", ((Integer)medicalAppointmentMap.get("patientId")).longValue());
        }
        
        query.setLong("appointmentId", (Long)medicalAppointmentMap.get("id"));
        
        int records=query.executeUpdate();
        
        return (records > 0);
    }
    
    @Override
    public Boolean setPatient(Long patientId, Long appoinmentId){
        
        StringBuilder builder = new StringBuilder();
        builder.append("update MedicalAppointment MA");
        builder.append(" set MA.patient.id=:patientId");
        builder.append(" where (MA.id=:appointmentId)");
        
        String hql = builder.toString();

        Query query = this.getSession().createQuery(hql);
        query.setLong("patientId", patientId);
        query.setLong("appointmentId", appoinmentId);
        
        int records=query.executeUpdate();
        
        return (records > 0);
    }
}
