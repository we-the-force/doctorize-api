/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.data.user;

import com.cmtb.doctorize.domain.user.User;
import java.util.List;
import java.util.Map;

/**
 *
 * @author pc
 */
public interface UserDao {

    public User save(User user);
            
    public User getUserByEmail(String email);
    
    public boolean update(User user);
    
    public boolean patch(Map<String, Object> userMap);
    
    public boolean updatePhoto(User user);
    
    public int changePassword(User user);
    
    public User getUserById(Long userId);
    
    public User getAssistantByIdAndDoctor(Long assistantId, Long doctorId);
    
    public boolean confirmationAccount(String email);
    
    public boolean confirmationAssistantAccount(User user);
    
    public Boolean delete(Long userId);
    
    public List<User> getListByDoctorId(Long doctorId);
    
    public Boolean deleteAssistant(Long assistantId);
    
}
