/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.data.assistant;

import com.cmtb.doctorize.domain.shared.RequiredException;
import com.cmtb.doctorize.domain.assistant.AssistantDoctorOffice;
import com.cmtb.doctorize.domain.shared.StatusEnum;
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
 * @author gealtec
 */
@Transactional
@Repository("AssistantDoctorOfficeDao")
public class AssistantDoctorOfficeDaoImpl implements AssistantDoctorOfficeDao {
    
    @Autowired
    private SessionFactory _sessionFactory;

    private Session getSession() {
        return _sessionFactory.getCurrentSession();
    }
    
    @Override
    public AssistantDoctorOffice save(AssistantDoctorOffice assistantDoctorOffice){
        try {
            Long newId = (long) this.getSession().save(assistantDoctorOffice);
            assistantDoctorOffice.setId(newId);
            return assistantDoctorOffice;
        } catch (TransientPropertyValueException e) {
            throw new RequiredException(e.getPropertyName());
        }
    }
    
    @Override
    public List<AssistantDoctorOffice> getListAssistantsByDoctorId(Long doctorId) {
        String hql = "select ADO from AssistantDoctorOffice ADO"
                + " join fetch ADO.assistant A"
                + " join fetch ADO.doctor D"
                + " join fetch ADO.doctorOffice DO"
                + " left join fetch ADO.permissions P"
                + " where D.id =:doctorId"
                + " and (D.status=:active or D.status=:unconfirmed)";

        Query query = this.getSession().createQuery(hql);
        query.setLong("doctorId", doctorId);
        query.setByte("active", StatusEnum.ACTIVE.getId());
        query.setByte("unconfirmed", StatusEnum.UNCONFIRMED.getId());
        
        query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        return (List<AssistantDoctorOffice>) query.list();
    }
    
    @Override
    public Boolean deleteAssistantByIdAndDoctor(Long assistantId, Long doctorId){
        String hql = "delete from AssistantDoctorOffice ADO "
                + " where ADO.assistant.id=:assistantId and ADO.doctor.id =:doctorId";

        Query query = this.getSession().createQuery(hql);
        query.setLong("assistantId", assistantId);
        query.setLong("doctorId", doctorId);

        int affected = query.executeUpdate();
        
        return (affected > 0);
    }
    
    @Override
    public Boolean exists(AssistantDoctorOffice assistantDoctorOffice){
        String hql = "select count(*) from AssistantDoctorOffice ADO"
                + " where ADO.doctor.id =:doctorId and ADO.assistant.id=:assistantId"
                + " and ADO.doctorOffice.id=:doctorOfficeId";

        Query query = this.getSession().createQuery(hql);
        query.setLong("doctorId", assistantDoctorOffice.getDoctor().getId());
        query.setLong("assistantId", assistantDoctorOffice.getAssistant().getId());
        query.setLong("doctorOfficeId", assistantDoctorOffice.getDoctorOffice().getId());
        
        
        Long matches = (Long) query.uniqueResult();
        return matches > 0;
    }
}
