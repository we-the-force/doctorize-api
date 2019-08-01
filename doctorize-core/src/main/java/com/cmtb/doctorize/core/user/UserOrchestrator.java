/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.core.user;

import com.cmtb.doctorize.domain.user.AssistantDisplayObject;
import com.cmtb.doctorize.domain.user.ChangePasswordDisplayObject;
import com.cmtb.doctorize.domain.user.User;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pc
 */
public interface UserOrchestrator {

    public User save(User user);
    
    public Boolean resetPassword(ChangePasswordDisplayObject changePasswordDisplayObject);
    
    public Boolean changePassword(ChangePasswordDisplayObject changePasswordDisplayObject);
    
    public Boolean inviteAssistant(AssistantDisplayObject assistantDisplayObject);
    
    public Boolean confirmationAccount(ChangePasswordDisplayObject changePasswordDisplayObject);
    
    public Boolean confirmationAssistantAccount(AssistantDisplayObject assistantDisplayObject);
    
    public Boolean delete(Long userId);
    
    public Boolean deleteAssistant(Long assistantId);
    
    public User update(User user);
    
}
