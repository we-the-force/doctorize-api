/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.data.patient;

import com.cmtb.doctorize.domain.patient.Patient;
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
@Repository("PatientDao")
public class PatientDaoImpl implements PatientDao {
    
    @Autowired
    private SessionFactory _sessionFactory;

    private Session getSession() {
        return _sessionFactory.getCurrentSession();
    }
    
    @Override
    public Patient save(Patient patient){
        try {
            Long newId = (long) this.getSession().save(patient);
            patient.setId(newId);
            return patient;
        } catch (TransientPropertyValueException e) {
            throw new RequiredException(e.getPropertyName());
        }
    }
    
    @Override
    public Patient getPatientById(Long patientId) {
        String hql = "select P from Patient P"
                + " where P.id =:patientId";

        Query query = this.getSession().createQuery(hql);
        query.setLong("patientId", patientId);

        return (Patient) query.uniqueResult();
    }
    
    @Override
    public List<Patient> getListByDoctorId(Long doctorId){
        String hql = "select P from Patient P"
                + " join fetch P.medicalAppointments PMA"
                + " where PMA.doctor.id =:doctorId";

        Query query = this.getSession().createQuery(hql);
        query.setLong("doctorId", doctorId);
        
        query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        return (List<Patient>) query.list();
    }
    
    @Override
    public Boolean delete(Long patientId){
        String hql = "delete from Patient P "
                + " where (P.id=:patientId)";

        Query query = this.getSession().createQuery(hql);
        query.setLong("patientId", patientId);

        int affected = query.executeUpdate();
        
        return (affected > 0);
    }
    
    @Override
    public Boolean update(Patient patient){
        StringBuilder builder = new StringBuilder();
        builder.append("update Patient P");
        builder.append(" set P.name=:name, P.email=:email,");
        builder.append(" P.cellphone=:cellphone,");
        builder.append(" P.birthdate=:birthdate,");
        builder.append(" P.gender=:gender,");
        builder.append(" P.maritalStatus=:maritalStatus,");
        builder.append(" P.weight=:weight,");
        builder.append(" P.height=:height,");
        builder.append(" P.bloodType=:bloodType,");
        builder.append(" P.bloodPressure=:bloodPressure,");
        builder.append(" P.photo=:photo");
        builder.append(" where (P.id=:patientId)");
        
        String hql = builder.toString();

        Query query = this.getSession().createQuery(hql);
        query.setString("name", patient.getName());
        query.setString("email", patient.getEmail());
        query.setString("cellphone", patient.getCellphone());
        query.setDate("birthdate", patient.getBirthdate());
        
        query.setByte("gender", patient.getGender());
        query.setByte("maritalStatus", patient.getMaritalStatus());
        query.setInteger("weight", patient.getWeight());
        query.setInteger("height", patient.getHeight());
        query.setByte("bloodType", patient.getBloodType());
        query.setString("bloodPressure", patient.getBloodPressure());
        query.setString("photo", patient.getPhoto());
        
        
        query.setLong("patientId", patient.getId());
        
        int records=query.executeUpdate();
        
        return (records > 0);
    }
    
    @Override
    public Boolean updatePhoto(Patient patient){
        StringBuilder builder = new StringBuilder();
        builder.append("update Patient P");
        builder.append(" set P.photo=:photo");
        builder.append(" where (P.id=:patientId)");
        
        String hql = builder.toString();

        Query query = this.getSession().createQuery(hql);
        query.setString("photo", patient.getPhoto());
        
        query.setLong("patientId", patient.getId());
        
        int records=query.executeUpdate();
        
        return (records > 0);
    }
}
