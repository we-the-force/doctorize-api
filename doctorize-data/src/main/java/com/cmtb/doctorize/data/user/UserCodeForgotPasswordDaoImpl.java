/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.data.user;

import com.cmtb.doctorize.domain.shared.RequiredException;
import com.cmtb.doctorize.domain.user.ChangePasswordDisplayObject;
import com.cmtb.doctorize.domain.user.UserCodeForgotPassword;
import javax.transaction.Transactional;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.TransientPropertyValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pc
 */
@Transactional
@Repository("UserCodeForgotPasswordDao")
public class UserCodeForgotPasswordDaoImpl implements UserCodeForgotPasswordDao {
    
    @Autowired
    private SessionFactory _sessionFactory;

    private Session getSession() {
        return _sessionFactory.getCurrentSession();
    }
    
    @Override
    public void deleteCodeByUserId(Long userId) {
        String hql = "delete from UserCodeForgotPassword UC"
                + " where (UC.user.id=:userId)";

        Query query = this.getSession().createQuery(hql);
        query.setLong("userId", userId);
        query.executeUpdate();
    }
    
    @Override
    public Long createCodeChangePassword(UserCodeForgotPassword userCodeForgotPassword) {
        try {
            Long newId = (long) this.getSession().save(userCodeForgotPassword);
            return newId;
        } catch (TransientPropertyValueException e) {
            throw new RequiredException(e.getPropertyName());
        }
    }
    
    @Override
    public UserCodeForgotPassword existAssociatedCodeByPatient(ChangePasswordDisplayObject changePasswordDisplayObject) {
        String hql = "select UC from UserCodeForgotPassword UC"
                + " where ((UC.user.id=:userId)and(UC.code=:code))";

        Query query = this.getSession().createQuery(hql);
        query.setLong("userId", changePasswordDisplayObject.getUser().getId());
        query.setString("code", changePasswordDisplayObject.getCode());

        return (UserCodeForgotPassword) query.uniqueResult();

    }
}
