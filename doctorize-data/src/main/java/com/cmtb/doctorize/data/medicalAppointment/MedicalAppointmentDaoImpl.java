/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.data.medicalAppointment;

import com.cmtb.doctorize.domain.medicalAppointment.MedicalAppointment;
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
@Repository("MedicalAppointmentDao")
public class MedicalAppointmentDaoImpl implements MedicalAppointmentDao {
    
    @Autowired
    private SessionFactory _sessionFactory;

    private Session getSession() {
        return _sessionFactory.getCurrentSession();
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
    public List<MedicalAppointment> getListByFilter(Long doctorId, List<String> filter, List<String> search){
        
        StringBuilder builder = new StringBuilder();
        builder.append("select MA from MedicalAppointment MA");
        builder.append(" left join fetch MA.doctor");
        builder.append(" left join fetch MA.doctorOffice");
        builder.append(" where MA.doctor.id =:doctorId");
        
        String hql = builder.toString();

        Query query = this.getSession().createQuery(hql);
        query.setLong("doctorId", doctorId);
        
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
    public Boolean update(MedicalAppointment medicalAppointment){
        
        StringBuilder builder = new StringBuilder();
        builder.append("update MedicalAppointment MA");
        builder.append(" set MA.name=:name, MA.email=:email,");
        builder.append(" MA.phone=:phone,");
        builder.append(" MA.date=:date,");
        if(medicalAppointment.getPatient() != null){
            builder.append(" MA.patient.id=:patientId,");
        }
        builder.append(" MA.doctorOffice.id=:doctorOfficeId");
        builder.append(" where (MA.id=:appointmentId)");
        
        String hql = builder.toString();

        Query query = this.getSession().createQuery(hql);
        query.setString("name", medicalAppointment.getName());
        query.setString("email", medicalAppointment.getEmail());
        query.setString("phone", medicalAppointment.getPhone());
        query.setDate("date", medicalAppointment.getDate());
        query.setLong("doctorOfficeId", medicalAppointment.getDoctorOffice().getId());
        
        query.setLong("appointmentId", medicalAppointment.getId());
        if(medicalAppointment.getPatient() != null){
            query.setLong("patientId", medicalAppointment.getPatient().getId());
        }
        
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
