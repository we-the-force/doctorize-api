/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.core.user;

import com.cmtb.doctorize.core.doctorOffice.UserDoctorOfficeDomain;
import com.cmtb.doctorize.core.shared.ClusterValidationAbstract;
import com.cmtb.doctorize.domain.doctor.DoctorOffice;
import com.cmtb.doctorize.domain.doctor.UserDoctorOffice;
import com.cmtb.doctorize.domain.user.AssistantDisplayObject;
import com.cmtb.doctorize.domain.user.ChangePasswordDisplayObject;
import com.cmtb.doctorize.domain.user.User;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pc
 */
@Component(value = "UserOrchestrator")
public class UserOrchestratorImpl implements UserOrchestrator {
    
    @Resource(name = "UserDomain")
    private UserDomain userDomain;
    
    @Resource(name = "UserDoctorOfficeDomain")
    private UserDoctorOfficeDomain userDoctorOfficeDomain;
    
    @Resource(name = "UserResetPasswordClusterValidator")
    private ClusterValidationAbstract userResetPasswordValidatorCluster;
    
    @Resource(name = "UserChangePasswordClusterValidator")
    private UserChangePasswordClusterValidator userChangePasswordClusterValidator;
    
    @Transactional
    @Override
    public User save(User user){
        
        return userDomain.save(user);
    }
    
    @Transactional
    @Override
    public Boolean resetPassword(ChangePasswordDisplayObject changePasswordDisplayObject) {
        
        User user = userDomain.getUserByEmail(changePasswordDisplayObject.getEmail());
        changePasswordDisplayObject.setUser(user);
        
        userResetPasswordValidatorCluster.run(changePasswordDisplayObject);
        user.setPassword(changePasswordDisplayObject.getPassword());
        return userDomain.resetPassword(user);
    }
    
    @Transactional
    @Override
    public Boolean changePassword(ChangePasswordDisplayObject changePasswordDisplayObject) {
        
        User user = userDomain.getUserById(changePasswordDisplayObject.getId());
        changePasswordDisplayObject.setUser(user);
        
        userChangePasswordClusterValidator.run(changePasswordDisplayObject);
        user.setPassword(changePasswordDisplayObject.getPassword());
        return userDomain.changePassword(user);
    }
    
    @Transactional
    @Override
    public Boolean inviteAssistant(AssistantDisplayObject assistantDisplayObject){
        
        
        
        User user = userDomain.inviteAssistant(assistantDisplayObject);
        
        UserDoctorOffice userDoctorOffice = new UserDoctorOffice();
        userDoctorOffice.setUser(user);
        DoctorOffice doctorOffice = new DoctorOffice();
        doctorOffice.setId(assistantDisplayObject.getDoctorOfficeId());
        userDoctorOffice.setDoctorOffice(doctorOffice);
        
        userDoctorOfficeDomain.save(userDoctorOffice);
        
        return true;
    }
    
    @Transactional
    @Override
    public Boolean confirmationAccount(ChangePasswordDisplayObject changePasswordDisplayObject){
        
        return userDomain.confirmationAccount(changePasswordDisplayObject);
    }
    
    @Transactional
    @Override
    public Boolean confirmationAssistantAccount(AssistantDisplayObject assistantDisplayObject){
        return userDomain.confirmationAssistantAccount(assistantDisplayObject);
    }
    
    @Transactional
    @Override
    public Boolean delete(Long userId){
        return userDomain.delete(userId);
    }
    
}
