/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.data.user;

import com.cmtb.doctorize.domain.shared.RequiredException;
import com.cmtb.doctorize.domain.user.RoleEnum;
import com.cmtb.doctorize.domain.user.User;
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
@Repository("UserDao")
public class UserDaoImpl implements UserDao {
    
    @Autowired
    private SessionFactory _sessionFactory;

    private Session getSession() {
        return _sessionFactory.getCurrentSession();
    }
    
    @Override
    public User save(User user){
        try {
            Long newId = (long) this.getSession().save(user);
            user.setId(newId);
            return user;
        } catch (TransientPropertyValueException e) {
            throw new RequiredException(e.getPropertyName());
        }
    }
    
    @Override
    public User getUserByEmail(String email) {
        String hql = "select U from User U"
                + " left join fetch U.specialty US"
                + " left join fetch U.permissions UP"
                + " where (U.email = :email)";

        Query query = this.getSession().createQuery(hql);
        query.setString("email", email);

        return (User) query.uniqueResult();
    }
    
    @Override
    public boolean update(User user){
        
        StringBuilder builder = new StringBuilder();
        builder.append("update User U");
        builder.append(" set U.name=:name, U.email=:email,");
        if(user.getSpecialty() != null){
            builder.append(" U.specialty.id=:specialtyId,");
        }
        
        builder.append(" U.cellphone=:cellphone");
        builder.append(" where (U.id=:userId)");
        
        String hql = builder.toString();

        Query query = this.getSession().createQuery(hql);
        query.setString("name", user.getName());
        query.setString("email", user.getEmail());
        query.setString("cellphone", user.getCellphone());
        
        if(user.getSpecialty() != null){
            query.setLong("specialtyId", user.getSpecialty().getId());
        }
        
        query.setLong("userId", user.getId());
        
        int records=query.executeUpdate();
        
        return (records > 0);
    }
    
    @Override
    public boolean patch(Map<String, Object> userMap){
        Boolean needComa = false;
        Long specialtyId = 0L;
        
        StringBuilder builder = new StringBuilder();
        builder.append("update User U");
        builder.append(" set");
        
        if(userMap.containsKey("name")){
            builder.append(" U.name=:name");
            needComa = true;
        }
        
        if(userMap.containsKey("email")){
            if(needComa){
                builder.append(",");
            }else{
                needComa = true;
            }
            builder.append(" U.email=:email");
        }
        
        if(userMap.containsKey("cellphone")){
            if(needComa){
                builder.append(",");
            }else{
                needComa = true;
            }
            builder.append(" U.cellphone=:cellphone");
        }
        
        if(userMap.containsKey("specialty")){
            Map<String, Object> specialty = (Map<String, Object>) userMap.get("specialty");
            if(specialty.containsKey("id")){
                specialtyId = ((Integer) specialty.get("id")).longValue();
                if(needComa){
                    builder.append(",");
                }else{
                    needComa = true;
                }

                builder.append(" U.specialty.id=:specialtyId");
            }
        }
        
        
        builder.append(" where (U.id=:userId)");
        
        String hql = builder.toString();

        Query query = this.getSession().createQuery(hql);
        
        if(userMap.containsKey("name")){
            query.setString("name", userMap.get("name").toString());
        }
        
        if(userMap.containsKey("email")){
            query.setString("email", userMap.get("email").toString());
        }
        
        if(userMap.containsKey("cellphone")){
            query.setString("cellphone", userMap.get("cellphone").toString());
        }
        
        if(userMap.containsKey("specialty")){
            query.setLong("specialtyId", specialtyId);
        }
        
        query.setLong("userId", (Long) userMap.get("id"));
        
        int records=query.executeUpdate();
        
        return (records > 0);
    }
    
    @Override
    public boolean updatePhoto(User user){
        
        String hql = "update User U"
                + " set U.photo=:photo"
                + " where (U.id=:userId)";

        Query query = this.getSession().createQuery(hql);
        query.setString("photo", user.getPhoto());
        
        query.setLong("userId", user.getId());
        
        int records=query.executeUpdate();
        
        return (records > 0);
    }
    
    @Override
    public int changePassword(User user) {
        String hql = "update User U"
                + " set U.password=:password"
                + " where (U.id=:userId)";

        Query query = this.getSession().createQuery(hql);
        query.setString("password", user.getPassword());
        query.setLong("userId", user.getId());
        return query.executeUpdate();
    }
    
    @Override
    public User getUserById(Long userId) {
        String hql = "select U from User U"
                + " left join fetch U.specialty US"
                + " left join fetch U.permissions UP"
                + " where U.id =:userId";

        Query query = this.getSession().createQuery(hql);
        query.setLong("userId", userId);

        return (User) query.uniqueResult();
    }
    
    @Override
    public User getAssistantByIdAndDoctor(Long assistantId, Long doctorId){
        String hql = "select U from User U"
                + " join fetch U.assistantDoctorOffices ADO"
                + " join fetch ADO.permissions P"
                + " join fetch ADO.doctor D"
                + " join fetch ADO.doctorOffice DO"
                + " where U.id =:assistantId and D.id =:doctorId";

        Query query = this.getSession().createQuery(hql);
        query.setLong("assistantId", assistantId);
        query.setLong("doctorId", doctorId);

        return (User) query.uniqueResult();
    }
    
    @Override
    public User getAssistantByIdAndDoctorOffice(Long assistantId, Long doctorOfficeId){
        String hql = "select U from User U"
                + " join fetch U.assistantDoctorOffices ADO"
                + " join fetch ADO.permissions P"
                + " join fetch ADO.doctorOffice DO"
                + " where U.id =:assistantId and DO.id =:doctorOfficeId";

        Query query = this.getSession().createQuery(hql);
        query.setLong("assistantId", assistantId);
        query.setLong("doctorOfficeId", doctorOfficeId);

        return (User) query.uniqueResult();
    }
    
    @Override
    public boolean confirmationAccount(String email){
        String hql = "update User U"
                + " set U.status=:status,"
                + " U.confirmationCode=:confirmationCode"
                + " where (U.email=:email)";

        Query query = this.getSession().createQuery(hql);
        query.setString("email", email);
        query.setString("confirmationCode", "");        
        query.setByte("status", StatusEnum.ACTIVE.getId());
        
        int records=query.executeUpdate();
        
        return (records > 0);
        
    }
    
    @Override
    public boolean confirmationAssistantAccount(User user){
        
        String hql = "update User U"
                + " set U.status=:status,"
                + " U.confirmationCode=:confirmationCode,"
                + " U.password=:password"
                + " where (U.email=:email)";

        Query query = this.getSession().createQuery(hql);
        query.setString("email", user.getEmail());
        query.setString("confirmationCode", "");        
        query.setByte("status", StatusEnum.ACTIVE.getId());
        query.setString("password", user.getPassword());
        
        int records=query.executeUpdate();
        
        return (records > 0);
    }
    
    @Override
    public Boolean delete(Long userId){
        String hql = "update User U"
                + " set U.status=:status"
                + " where (U.id=:userId)";

        Query query = this.getSession().createQuery(hql);
        query.setLong("userId", userId);     
        query.setByte("status", StatusEnum.DISABLE.getId());
        
        int records=query.executeUpdate();
        
        return (records > 0);
    }
    
    @Override
    public List<User> getListByDoctorId(Long doctorId) {
        String hql = "select U from User U"
                + " join fetch U.assistantDoctorOffices ADO"
                + " join fetch ADO.permissions P"
                + " join fetch ADO.doctor D"
                + " join fetch ADO.doctorOffice DO"
                + " where D.id =:doctorId"
                + " and (D.status=:active or D.status=:unconfirmed)";

        Query query = this.getSession().createQuery(hql);
        query.setLong("doctorId", doctorId);
        query.setByte("active", StatusEnum.ACTIVE.getId());
        query.setByte("unconfirmed", StatusEnum.UNCONFIRMED.getId());
        
        query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        return (List<User>) query.list();
    }
    
    @Override
    public Boolean deleteAssistant(Long assistantId){
        String hql = "delete from User U "
                + " where (U.id=:assistantId) and U.roleId=:roleId";

        Query query = this.getSession().createQuery(hql);
        query.setLong("assistantId", assistantId);
        query.setByte("roleId", RoleEnum.ASSISTANT.getId());

        int affected = query.executeUpdate();
        
        return (affected > 0);
    }
    
}
