/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.data.user;

import com.cmtb.doctorize.domain.user.User;

/**
 *
 * @author pc
 */
public interface UserDao {

    public User save(User user);
            
    public User getUserByEmail(String email);
    
    public boolean update(User user);
    
    public boolean updatePhoto(User user);
    
    public int changePassword(User user);
    
    public User getUserById(Long userId);
    
    public boolean confirmationAccount(String email);
    
    public boolean confirmationAssistantAccount(User user);
    
}
