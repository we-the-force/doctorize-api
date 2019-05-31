/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.core.user;

import com.cmtb.doctorize.data.user.UserCodeForgotPasswordDao;
import com.cmtb.doctorize.data.user.UserDao;
import com.cmtb.doctorize.domain.user.ChangePasswordDisplayObject;
import com.cmtb.doctorize.domain.user.LoginDisplayObject;
import com.cmtb.doctorize.domain.user.User;
import com.cmtb.doctorize.domain.user.UserCodeForgotPassword;
import com.cmtb.doctorize.domain.user.UserNotFoundException;
import com.cmtb.doctorize.utilities.PasswordEncrypt;
import com.cmtb.doctorize.utilities.RandomStringGenerator;
import java.util.Date;
import javax.annotation.Resource;
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
    
    @Resource(name = "UserDao")
    private UserDao userDao;
    
    private User assembleUser(User userTemp){
        User user = new User();
        
        user.setId(userTemp.getId());
        user.setName(userTemp.getName());
        user.setEmail(userTemp.getEmail());
        user.setCellphone(userTemp.getCellphone());
        user.setPhoto(userTemp.getPhoto());
        user.setPassword(userTemp.getPassword());
        user.setRoleId(userTemp.getRoleId());
        
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
            throw new UserNotFoundException();
        }

        Boolean match = passwordEncrypt.checkPassword(loginDisplayObject.getPassword(), user.getPassword());

        if (!match) {
            throw new UserNotFoundException();
        }
        
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
            throw new UserNotFoundException();
        }
        return exist;
    }
    
    @Override
    public User save(User user){
        String email = (user.getEmail() != null ? user.getEmail().toLowerCase() : "");
        user.setEmail(email);
        
//        userClusterValidation.run(user);
//        
//        user.setImageData(user.getPhoto());
//        user.setPhoto(null);
        
        if(user.getId() == null){
            String hash = passwordEncrypt.hashPassword(user.getPassword());
            user.setPassword(hash);
            user.setActive(true);
            
            userDao.save(user);
            
//            AttachmentResultDisplayObject results = userAttachmentImagesComponent.attachementPhoto(user);
        
//            if (results.getUpdated()) {
//                user.setPhoto(results.getPath());
//            }
            
        }else{
            this.update(user);
        }
        
        return user;
    }
    
    @Override
    public boolean update(User user){
        
//        AttachmentResultDisplayObject results = userAttachmentImagesComponent.attachementPhoto(user);
//        
//        if (results.getUpdated()) {
//            user.setPhoto(results.getPath());
//            this.updatePhoto(user);
//        }
        
        return userDao.update(user);
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
    
}
