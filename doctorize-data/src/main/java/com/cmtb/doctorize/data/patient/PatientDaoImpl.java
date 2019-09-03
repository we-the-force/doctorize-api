/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.data.patient;

import com.cmtb.doctorize.domain.patient.Patient;
import com.cmtb.doctorize.domain.shared.RequiredException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
@Repository("PatientDao")
public class PatientDaoImpl implements PatientDao {
    
    private Boolean needComa = false;
    
    private String needComa(){
        if(needComa){
            return ",";
        }else{
            needComa = true;
            return "";
        }
    }
    
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
    public Boolean patch(Map<String, Object> patientMap){
        
        needComa = false;
        
        StringBuilder builder = new StringBuilder();
        builder.append("update Patient P");
        builder.append(" set");
        if(patientMap.containsKey("name")){
            builder.append(needComa());
            builder.append(" P.name=:name");
        }
        if(patientMap.containsKey("email")){
            builder.append(needComa());
            builder.append(" P.email=:email");
        }
        if(patientMap.containsKey("cellphone")){
            builder.append(needComa());
            builder.append(" P.cellphone=:cellphone");
        }
        if(patientMap.containsKey("birthdate")){
            builder.append(needComa());
            builder.append(" P.birthdate=:birthdate");
        }
        if(patientMap.containsKey("gender")){
            builder.append(needComa());
            builder.append(" P.gender=:gender");
        }
        if(patientMap.containsKey("maritalStatus")){
            builder.append(needComa());
            builder.append(" P.maritalStatus=:maritalStatus");
        }
        if(patientMap.containsKey("weight")){
            builder.append(needComa());
            builder.append(" P.weight=:weight");
        }
        if(patientMap.containsKey("height")){
            builder.append(needComa());
            builder.append(" P.height=:height");
        }
        if(patientMap.containsKey("bloodType")){
            builder.append(needComa());
            builder.append(" P.bloodType=:bloodType");
        }
        if(patientMap.containsKey("bloodPressure")){
            builder.append(needComa());
            builder.append(" P.bloodPressure=:bloodPressure");
        }
        builder.append(" where (P.id=:patientId)");
        
        String hql = builder.toString();

        Query query = this.getSession().createQuery(hql);
        if(patientMap.containsKey("name")){
            query.setString("name", patientMap.get("name").toString());
        }
        if(patientMap.containsKey("email")){
            query.setString("email", patientMap.get("email").toString());
        }
        if(patientMap.containsKey("cellphone")){
            query.setString("cellphone", patientMap.get("cellphone").toString());
        }
        if(patientMap.containsKey("birthdate")){
            SimpleDateFormat formatter =new SimpleDateFormat("yyyy-MM-dd");
            Date date;
            try {
                date = formatter.parse(patientMap.get("birthdate").toString());
                query.setTimestamp("birthdate", date);
            } catch (ParseException ex) {
                Logger.getLogger(PatientDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(patientMap.containsKey("gender")){
            query.setByte("gender", ((Integer)patientMap.get("gender")).byteValue());
        }
        if(patientMap.containsKey("maritalStatus")){
            query.setByte("maritalStatus", ((Integer)patientMap.get("maritalStatus")).byteValue());
        }
        if(patientMap.containsKey("weight")){
            query.setInteger("weight", (Integer)patientMap.get("weight"));
        }
        if(patientMap.containsKey("height")){
            query.setInteger("height", (Integer)patientMap.get("height"));
        }
        if(patientMap.containsKey("bloodType")){
            query.setByte("bloodType", ((Integer)patientMap.get("bloodType")).byteValue());
        }
        if(patientMap.containsKey("bloodPressure")){
            query.setString("bloodPressure", patientMap.get("bloodPressure").toString());
        }
        
        query.setLong("patientId", (Long)patientMap.get("id"));
        
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
    
    @Override
    public List<Patient> getByFilter(String filter, String value){
        
        StringBuilder builder = new StringBuilder();
        builder.append("select P from Patient P");
        
        switch (filter) {
            case "name":
                builder.append(" where P.name=:name");
                break;
            case "email":
                builder.append(" where P.email=:email");
                break;
            case "cellphone":
                builder.append(" where P.cellphone=:cellphone");
                break;
            case "bloodPressure":
                builder.append(" where P.bloodPressure=:bloodPressure");
                break;
            case "photo":
                builder.append(" where P.photo=:photo");
                break;
            default:
                return new ArrayList<>();
        }
        
        String hql = builder.toString();
        
        Query query = this.getSession().createQuery(hql);
        
        switch (filter) {
            case "name":
                query.setString("name", value);
                break;
            case "email":
                query.setString("email", value);
                break;
            case "cellphone":
                query.setString("cellphone", value);
                break;
            case "bloodPressure":
                query.setString("bloodPressure", value);
                break;
            case "photo":
                query.setString("photo", value);
                break;
            default:
                return new ArrayList<>();
        }

        return (List<Patient>) query.list();
    }
    
    @Override
    public List<Patient> getListByLimit(Integer offset, Integer limit){
        String hql = "select P from Patient P"
                + " ORDER BY P.id ";

        Query query = this.getSession().createQuery(hql);
        query.setFirstResult(offset);
        query.setMaxResults(limit);

        return (List<Patient>) query.list();
    }
}
