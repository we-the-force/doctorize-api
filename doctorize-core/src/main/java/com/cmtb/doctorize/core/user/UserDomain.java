/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.core.user;

import com.cmtb.doctorize.domain.assistant.AssistantDisplayObject;
import com.cmtb.doctorize.domain.assistant.AssistantDisplayObjectNEW;
import com.cmtb.doctorize.domain.user.ChangePasswordDisplayObject;
import com.cmtb.doctorize.domain.user.LoginContainerDisplayObject;
import com.cmtb.doctorize.domain.user.LoginDisplayObject;
import com.cmtb.doctorize.domain.user.User;
import com.cmtb.doctorize.domain.user.UserDisplayObject;
import java.util.List;
import java.util.Map;

/**
 *
 * @author pc
 */
public interface UserDomain {

    public LoginContainerDisplayObject login(LoginDisplayObject loginDisplayObject);
    
    public Boolean forgotPassword(String email);
            
    public User save(User user);
    
    public boolean update(User user);
    
    public boolean patch(Map<String, Object> userMap);
    
    public boolean updatePhoto(User user);
    
    public User getUserByEmail(String email);
    
    public Boolean existAssociatedCodeByPatient(ChangePasswordDisplayObject changePasswordDisplayObject);
    
    public Boolean resetPassword(User user);
    
    public User getUserById(Long userId);
    
    public Boolean changePassword(User user);
    
    public User inviteAssistant(AssistantDisplayObject assistantDisplayObject);
    
    public Boolean confirmationAccount(ChangePasswordDisplayObject displayObject);
    
    public Boolean confirmationAssistantAccount(AssistantDisplayObject assistantDisplayObject);
    
    public Boolean delete(Long userId);
    
    public List<AssistantDisplayObjectNEW> getListByDoctorId(Long doctorId);
    
    public UserDisplayObject getById(Long userId);
    
    public AssistantDisplayObjectNEW getAssistantByIdAndDoctor(Long assistantId, Long doctorId);
    
    public AssistantDisplayObjectNEW getAssistantByIdAndDoctorOffice(Long assistantId, Long doctorOfficeId);
    
    public Boolean deleteAssistant(Long assistantId);
    
    public AssistantDisplayObjectNEW addUpdateAssistantDoctorOffice(AssistantDisplayObjectNEW assistantDisplayObject);
    
}
