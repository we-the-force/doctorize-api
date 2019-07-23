/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.data.doctor;

import com.cmtb.doctorize.domain.shared.RequiredException;
import com.cmtb.doctorize.domain.doctor.UserDoctorOffice;
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
 * @author pc
 */
@Transactional
@Repository("UserDoctorOfficeDao")
public class UserDoctorOfficeDaoImpl implements UserDoctorOfficeDao {
    
    @Autowired
    private SessionFactory _sessionFactory;
    
    private Session getSession() {
        return _sessionFactory.getCurrentSession();
    }
    
    @Override
    public UserDoctorOffice save(UserDoctorOffice userDoctorOffice){
        try {
            Long newId = (long) this.getSession().save(userDoctorOffice);
            userDoctorOffice.setId(newId);
            return userDoctorOffice;
        } catch (TransientPropertyValueException e) {
            throw new RequiredException(e.getPropertyName());
        }
    }
    
    @Override
    public Boolean deleteByUserId(Long userId){
        String hql = "delete from UserDoctorOffice UDO "
                + " where (UDO.user.id=:userId)";

        Query query = this.getSession().createQuery(hql);
        query.setLong("userId", userId);

        int affected = query.executeUpdate();
        
        return (affected > 0);
    }
    
    @Override
    public List<UserDoctorOffice> getListAssistantsByDoctorId(Long doctorId) {
        String hql = "select UDO from UserDoctorOffice UDO"
                + " join fetch UDO.user U"
                + " join fetch UDO.doctorOffice DO"
                + " left join fetch U.permissions P"
                + " where U.doctor.id =:doctorId"
                + " and (U.doctor.status=:active or U.doctor.status=:unconfirmed)";

        Query query = this.getSession().createQuery(hql);
        query.setLong("doctorId", doctorId);
        query.setByte("active", StatusEnum.ACTIVE.getId());
        query.setByte("unconfirmed", StatusEnum.UNCONFIRMED.getId());
        
        query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        return (List<UserDoctorOffice>) query.list();
    }
}
