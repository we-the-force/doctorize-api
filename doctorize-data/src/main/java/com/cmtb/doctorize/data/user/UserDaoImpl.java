/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.data.user;

import com.cmtb.doctorize.domain.shared.RequiredException;
import com.cmtb.doctorize.domain.user.User;
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
                + " where (U.email = :email)";

        Query query = this.getSession().createQuery(hql);
        query.setString("email", email);

        return (User) query.uniqueResult();
    }
    
    @Override
    public boolean update(User user){
        
        String hql = "update User U"
                + " set U.name=:name, U.email=:email,"
                + " U.cellphone=:cellphone"
                + " where (U.id=:userId)";

        Query query = this.getSession().createQuery(hql);
        query.setString("name", user.getName());
        query.setString("email", user.getEmail());
        query.setString("cellphone", user.getCellphone());
        
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
                + " where U.id =:userId";

        Query query = this.getSession().createQuery(hql);
        query.setLong("userId", userId);

        return (User) query.uniqueResult();
    }
    
}
