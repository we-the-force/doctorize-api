/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.core.user;

import com.cmtb.doctorize.core.assistent.AssistantDoctorOfficeDomain;
import com.cmtb.doctorize.core.shared.SecurityComponent;
import com.cmtb.doctorize.data.user.UserCodeForgotPasswordDao;
import com.cmtb.doctorize.data.user.UserDao;
import com.cmtb.doctorize.domain.doctor.UserDoctorOffice;
import com.cmtb.doctorize.domain.shared.ConfirmationCodeExceptoin;
import com.cmtb.doctorize.domain.shared.ItemNotFoundException;
import com.cmtb.doctorize.domain.shared.PermissionEnum;
import com.cmtb.doctorize.domain.shared.Permissions;
import com.cmtb.doctorize.domain.assistant.AssistantDisplayObject;
import com.cmtb.doctorize.domain.assistant.AssistantDisplayObjectNEW;
import com.cmtb.doctorize.domain.assistant.AssistantDoctorOffice;
import com.cmtb.doctorize.domain.assistant.AssistantDoctorOfficeDisplayObject;
import com.cmtb.doctorize.domain.specialty.Specialty;
import com.cmtb.doctorize.domain.user.ChangePasswordDisplayObject;
import com.cmtb.doctorize.domain.user.LoginDisplayObject;
import com.cmtb.doctorize.domain.user.RoleEnum;
import com.cmtb.doctorize.domain.user.User;
import com.cmtb.doctorize.domain.user.UserCodeForgotPassword;
import com.cmtb.doctorize.domain.user.UserConfirmedException;
import com.cmtb.doctorize.domain.user.UserDisabledException;
import com.cmtb.doctorize.domain.user.UserDisplayObject;
import com.cmtb.doctorize.domain.shared.NotFoundException;
import com.cmtb.doctorize.domain.shared.StatusEnum;
import com.cmtb.doctorize.domain.user.UserUnconfirmedException;
import com.cmtb.doctorize.domain.utilities.AttachmentResultDisplayObject;
import com.cmtb.doctorize.utilities.PasswordEncrypt;
import com.cmtb.doctorize.utilities.RandomStringGenerator;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.hibernate.LazyInitializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author pc
 */
@Component(value = "UserDomain")
public class UserDomainImpl implements UserDomain {
    
    @Autowired
    private PasswordEncrypt passwordEncrypt;
    
    @Resource(name = "UserCodeForgotPasswordDao")
    private UserCodeForgotPasswordDao userCodeForgotPasswordDao;
    
    @Resource(name="UserNotifyCodeChangePasswordComponent")
    private UserNotifyCodeChangePasswordComponent userNotifyCodeChangePasswordComponent;
    
    @Resource(name="UserNotifyConfirmationCodeComponent")
    private UserNotifyConfirmationCodeComponent userNotifyConfirmationCodeComponent;
    
    @Resource(name="AssistantNotifyConfirmationCodeComponent")
    private AssistantNotifyConfirmationCodeComponent assistantNotifyConfirmationCodeComponent;
    
    @Resource(name = "UserDao")
    private UserDao userDao;
    
    @Resource(name = "UserAttachmentImagesComponent")
    private UserAttachmentImagesComponent userAttachmentImagesComponent;
    
    @Autowired
    private SecurityComponent securityComponent;
    
    private void setDoctorPermissions(User user){
        Permissions medicalRecord = new Permissions();
        medicalRecord.setId(PermissionEnum.MEDICAL_RECORD.getPermissionId());
        user.getPermissions().add(medicalRecord);
        
        Permissions patients = new Permissions();
        patients.setId(PermissionEnum.PATIENTS.getPermissionId());
        user.getPermissions().add(patients);
        
        Permissions payments = new Permissions();
        payments.setId(PermissionEnum.PAYMENTS.getPermissionId());
        user.getPermissions().add(payments);
        
        Permissions schedule = new Permissions();
        schedule.setId(PermissionEnum.SCHEDULE.getPermissionId());
        user.getPermissions().add(schedule);
    }
    
    private UserDisplayObject assemblerUserDoctorOfficeTOUserDisplayObect(UserDoctorOffice userDoctorOffice){
        UserDisplayObject userDO = this.assembleUserDisplayObject(userDoctorOffice.getUser());
//        userDO.setDoctorOfficeName(userDoctorOffice.getDoctorOffice().getName());
        
        return userDO;
    }
    
    private UserDisplayObject assembleUserDisplayObject(User userTemp){
        
        UserDisplayObject userDisplayObject = new UserDisplayObject();        
            
        userDisplayObject.setCellphone(userTemp.getCellphone());
        userDisplayObject.setEmail(userTemp.getEmail());
        userDisplayObject.setId(userTemp.getId());
        userDisplayObject.setName(userTemp.getName());
        userDisplayObject.setPhoto(userTemp.getPhoto());
//        userDisplayObject.setRoleId(userTemp.getRoleId());
        userDisplayObject.setStatus(userTemp.getStatus());

        if(userTemp.getSpecialty() != null){
            Specialty specialty = new Specialty();
            specialty.setId(userTemp.getSpecialty().getId());
            specialty.setName(userTemp.getSpecialty().getName());
            userDisplayObject.setSpecialty(specialty);
        }
        
        try{
            if(userTemp.getPermissions() != null && userTemp.getPermissions().size() > 0){
                for(Permissions permissionItem: userTemp.getPermissions()){
                    userDisplayObject.getPermissions().add(permissionItem);
                }
            }
        }catch(LazyInitializationException ex){
            
        }
        
        return userDisplayObject;
    }
    
    private AssistantDisplayObjectNEW assemblerUserToAssistantDO(User user){
        AssistantDisplayObjectNEW assistantDO = new AssistantDisplayObjectNEW();
        
        assistantDO.setCellphone(user.getCellphone());
        assistantDO.setEmail(user.getEmail());
        assistantDO.setId(user.getId());
        assistantDO.setName(user.getName());
        
        for(AssistantDoctorOffice assistanDoctorOfficeItem : user.getAssistantDoctorOffices()){
            
            AssistantDoctorOfficeDisplayObject assistantDoctorOfficeDO = new AssistantDoctorOfficeDisplayObject();
            assistantDoctorOfficeDO.setOfficeId(assistanDoctorOfficeItem.getDoctorOffice().getId());
            assistantDoctorOfficeDO.setOfficeName(assistanDoctorOfficeItem.getDoctorOffice().getName());
            assistantDoctorOfficeDO.setPermissions(assistanDoctorOfficeItem.getPermissions());
            assistantDoctorOfficeDO.setDoctorId(assistanDoctorOfficeItem.getDoctor().getId());
            assistantDO.getOffices().add(assistantDoctorOfficeDO);
        }
        
        assistantDO.setPhoto(user.getPhoto());
        
        return assistantDO;
    }
    
    private User assembleUser(User userTemp){
        User user = new User();
        
        user.setId(userTemp.getId());
        user.setName(userTemp.getName());
        user.setEmail(userTemp.getEmail());
        user.setCellphone(userTemp.getCellphone());
        user.setPhoto(userTemp.getPhoto());
        user.setPassword(userTemp.getPassword());
        user.setRoleId(userTemp.getRoleId());
        
        if(userTemp.getSpecialty() != null){
            Specialty specialty = new Specialty();
            specialty.setId(userTemp.getSpecialty().getId());
            specialty.setName(userTemp.getSpecialty().getName());
            user.setSpecialty(specialty);
        }
        
        if(userTemp.getPermissions() != null){
            for(Permissions permissionItem: userTemp.getPermissions()){
                user.getPermissions().add(permissionItem);
            }
        }
//        user.setToken(userTemp.getToken());
        
//        try{
//            for(Address addressItem: userTemp.getAddresses()){
//                
//                Address address = new Address();
//            
//                City city = new City();
//                city.setId(addressItem.getCity().getId());
//                city.setName(addressItem.getCity().getName());
//                address.setCity(city);
//
//                address.setId(addressItem.getId());
//                address.setInteriorNumber(addressItem.getInteriorNumber());
//                address.setName(addressItem.getName());
//                address.setOutdoorNumber(addressItem.getOutdoorNumber());
//                address.setPostalCodeValue(addressItem.getPostalCodeValue());
//                address.setReceiver(addressItem.getReceiver());
//                
//                State state = new State();
//                state.setId(addressItem.getState().getId());
//                state.setName(addressItem.getState().getName());
//                address.setState(state);
//                
//                address.setSuburb(addressItem.getSuburb());
//                address.setStreet(addressItem.getStreet());
//                
//                user.getAddresses().add(address);
//            }
//        }catch(LazyInitializationException err){
//            System.out.println(err);
//        }
        
        return user;
    }
    
    private UserCodeForgotPassword createCodeChangePassword(ChangePasswordDisplayObject changePasswordDisplayObject) {
        
        userCodeForgotPasswordDao.deleteCodeByUserId(changePasswordDisplayObject.getUser().getId());

        UserCodeForgotPassword userCodeForgotPassword = new UserCodeForgotPassword();
        User user = new User();
        user.setId(changePasswordDisplayObject.getUser().getId());
        userCodeForgotPassword.setUser(user);
        userCodeForgotPassword.setCode(changePasswordDisplayObject.getCode());
        userCodeForgotPassword.setCreateDate(new Date());
        
        Long newId = userCodeForgotPasswordDao.createCodeChangePassword(userCodeForgotPassword);
        userCodeForgotPassword.setId(newId);
        return userCodeForgotPassword;
    }
    
    @Override
    public User login(LoginDisplayObject loginDisplayObject) {
        User user;
        
        user = this.getUserByEmail(loginDisplayObject.getEmail());
        
        if (user == null) {
            throw new NotFoundException();
        }else if(user.getStatus().equals(StatusEnum.DISABLE.getId())){
            throw new UserDisabledException();
        }else if(user.getStatus().equals(StatusEnum.UNCONFIRMED.getId())){
            throw new UserUnconfirmedException();
        }

        Boolean match = passwordEncrypt.checkPassword(loginDisplayObject.getPassword(), user.getPassword());

        if (!match) {
            throw new NotFoundException();
        }
        
//        String token = securityComponent.createToken(user);
//        user.setToken(token);
//        
        return this.assembleUser(user);
    }
    
    @Override
    public Boolean forgotPassword(String email){        
        
        char[] code = RandomStringGenerator.generate();
        
        ChangePasswordDisplayObject displayObject
                = new ChangePasswordDisplayObject();
        
        displayObject.setCode(new String(code));
        displayObject.setEmail(email);
        
        Boolean exist;
        User user = this.getUserByEmail(email);
        
        exist = (user != null);
        if (exist) {
            displayObject.setUser(user);
            this.createCodeChangePassword(displayObject);
            userNotifyCodeChangePasswordComponent.notify(displayObject);
        }else{
            throw new NotFoundException();
        }
        return exist;
    }
    
    @Override
    public User save(User user){
        String email = (user.getEmail() != null ? user.getEmail().toLowerCase() : "");
        user.setEmail(email);
        
        if(user.getId() == null){
            String hash = passwordEncrypt.hashPassword(user.getPassword());
            user.setPassword(hash);
            user.setStatus(StatusEnum.UNCONFIRMED.getId());
            
            char[] code = RandomStringGenerator.generate();
            user.setConfirmationCode(new String(code));
            
            setDoctorPermissions(user);
            
            userDao.save(user);
            
            ChangePasswordDisplayObject displayObject = new ChangePasswordDisplayObject();
            displayObject.setCode(new String(code));
            displayObject.setEmail(email);
            displayObject.setUser(user);
            
            AttachmentResultDisplayObject results = userAttachmentImagesComponent.attachementPhoto(user);
            
            userNotifyConfirmationCodeComponent.notify(displayObject);
        
            if (results.getUpdated()) {
                user.setPhoto(results.getPath());
            }
            
        }else{
            this.update(user);
        }
        
        user.setImageData("");
        return user;
    }
    
    @Override
    public boolean update(User user){
        
        AttachmentResultDisplayObject results = userAttachmentImagesComponent.attachementPhoto(user);
        
        if (results.getUpdated()) {
            user.setPhoto(results.getPath());
            this.updatePhoto(user);
        }
        
        return userDao.update(user);
    }
    
    @Override
    public boolean updatePhoto(User user){
        return userDao.updatePhoto(user);
    }
    
    @Override
    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }
    
    @Override
    public Boolean existAssociatedCodeByPatient(ChangePasswordDisplayObject changePasswordDisplayObject) {
        UserCodeForgotPassword userCodeForgotPassword = userCodeForgotPasswordDao.existAssociatedCodeByPatient(changePasswordDisplayObject);
        return (userCodeForgotPassword != null);
    }
    
    @Override
    public Boolean resetPassword(User user) {

        String encryptPassoword = passwordEncrypt.hashPassword(
                user.getPassword());

        user.setPassword(encryptPassoword);

        userCodeForgotPasswordDao.deleteCodeByUserId(user.getId());

        int results = userDao.changePassword(user);
        return (results > 0);
    }
    
    @Override
    public User getUserById(Long userId){
        User userTemp = userDao.getUserById(userId);
        return assembleUser(userTemp);
    }
    
    @Override
    public Boolean changePassword(User user) {
        String encryptPassoword = passwordEncrypt.hashPassword(
                user.getPassword());

        user.setPassword(encryptPassoword);

        int results = userDao.changePassword(user);
        return (results > 0);
    }
    
    @Override
    public User inviteAssistant(AssistantDisplayObject assistantDisplayObject){
        
        String email = (assistantDisplayObject.getEmail() != null ? assistantDisplayObject.getEmail().toLowerCase() : "");
        assistantDisplayObject.setEmail(email);
        
        User user = new User();
        user.setEmail(email);
        
        user.setRoleId(RoleEnum.ASSISTANT.getId());
        user.setPassword("temp");
        user.setStatus(StatusEnum.UNCONFIRMED.getId());
        
//        if(!assistantDisplayObject.getPermissions().isEmpty()){
//            for(Permissions permissionItem: assistantDisplayObject.getPermissions()){
//                user.getPermissions().add(permissionItem);
//            }
//        }
        
        char[] code = RandomStringGenerator.generate();
        user.setConfirmationCode(new String(code));
        
        assistantDisplayObject.setCode(new String(code));
        
        userDao.save(user);
        
        assistantNotifyConfirmationCodeComponent.notify(assistantDisplayObject);
        
        return user;
    }

    @Override
    public Boolean confirmationAccount(ChangePasswordDisplayObject displayObject){
        
        User user = this.getUserByEmail(displayObject.getEmail());
        
        if(user == null){
            throw new NotFoundException();
        }else if(user.getStatus().equals(StatusEnum.ACTIVE.getId())){
            throw new UserConfirmedException();
        }else if(!user.getConfirmationCode().equals(displayObject.getCode())){
            throw new ConfirmationCodeExceptoin();
        }else{
            return userDao.confirmationAccount(user.getEmail());
        }
    }
    
    @Override
    public Boolean confirmationAssistantAccount(AssistantDisplayObject assistantDisplayObject){
        
        User user = this.getUserByEmail(assistantDisplayObject.getEmail());
        
        if(user == null){
            throw new NotFoundException();
        }else if(user.getStatus().equals(StatusEnum.ACTIVE.getId())){
            throw new UserConfirmedException();
        }else if(!user.getConfirmationCode().equals(assistantDisplayObject.getCode())){
            throw new ConfirmationCodeExceptoin();
        }else{
            String hash = passwordEncrypt.hashPassword(assistantDisplayObject.getPassword());
            user.setPassword(hash);
            
            user.setName(assistantDisplayObject.getName());
            
            this.update(user);
            
            return userDao.confirmationAssistantAccount(user);
        }
    }
    
    @Override
    public Boolean delete(Long userId){
        
        if(userDao.delete(userId)){
            return true;
        }else{
            throw new NotFoundException();
        }
    }
    
    @Override
    public List<AssistantDisplayObjectNEW> getListByDoctorId(Long doctorId){
        
        List<AssistantDisplayObjectNEW> usersDO = new ArrayList<>();
        
        List<User> users = userDao.getListByDoctorId(doctorId);
        
        for(User userItem: users){
            usersDO.add(this.assemblerUserToAssistantDO(userItem));
        }
        
        return usersDO;
    }
    
    @Override
    public UserDisplayObject getById(Long userId){
        User user = userDao.getUserById(userId);
        if(user.getStatus().equals(StatusEnum.UNCONFIRMED.getId())){
            throw new UserUnconfirmedException();
        }else if(user.getStatus().equals(StatusEnum.DISABLE.getId())){
            throw new ItemNotFoundException();
        }
        return this.assembleUserDisplayObject(user);
    }
    
    @Override
    public AssistantDisplayObjectNEW getAssistantByIdAndDoctor(Long assistantId, Long doctorId){
        User user = userDao.getAssistantByIdAndDoctor(assistantId, doctorId);
        
        if(user == null || user.getStatus().equals(StatusEnum.DISABLE.getId())){
            throw new ItemNotFoundException();
        } else if(user.getStatus().equals(StatusEnum.UNCONFIRMED.getId())){
            throw new UserUnconfirmedException();
        }
        
        return this.assemblerUserToAssistantDO(user);
    }
    
    @Override
    public Boolean deleteAssistant(Long assistantId){
        return userDao.deleteAssistant(assistantId);
    }
    
}
