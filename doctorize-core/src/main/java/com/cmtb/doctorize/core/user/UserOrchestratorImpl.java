/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.core.user;

import com.cmtb.doctorize.core.assistent.AssistantDoctorOfficeDomain;
import com.cmtb.doctorize.core.doctorOffice.UserDoctorOfficeDomain;
import com.cmtb.doctorize.core.shared.ClusterValidationAbstract;
import com.cmtb.doctorize.domain.doctor.DoctorOffice;
import com.cmtb.doctorize.domain.doctor.UserDoctorOffice;
import com.cmtb.doctorize.domain.shared.ItemNotFoundException;
import com.cmtb.doctorize.domain.assistant.AssistantDisplayObject;
import com.cmtb.doctorize.domain.assistant.AssistantDoctorOffice;
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
    
    @Resource(name = "AssistantDoctorOfficeDomain")
    private AssistantDoctorOfficeDomain assistantDoctorOfficeDomain;
    
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
        
        AssistantDoctorOffice assistantDoctorOffice = new AssistantDoctorOffice();
        assistantDoctorOffice.setAssistant(user);
        DoctorOffice doctorOffice = new DoctorOffice();
        doctorOffice.setId(assistantDisplayObject.getOfficeId());
        assistantDoctorOffice.setDoctorOffice(doctorOffice);
        assistantDoctorOffice.setPermissions(assistantDisplayObject.getPermissions());
        User doctor = new User();
        doctor.setId(assistantDisplayObject.getDoctorId());
        assistantDoctorOffice.setDoctor(doctor);
        
        assistantDoctorOfficeDomain.save(assistantDoctorOffice);
        
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
    
    @Transactional
    @Override
    public Boolean deleteAssistantByIdAndDoctor(Long assistantId, Long doctorId){
        
        return assistantDoctorOfficeDomain.deleteAssistantByIdAndDoctor(assistantId, doctorId);
        
//        return userDomain.deleteAssistant(assistantId);
    }
    
    @Transactional
    @Override
    public User update(User user){
        if(user.getId() == null){
            throw new ItemNotFoundException();
        }
        return this.save(user);
    }
    
}
