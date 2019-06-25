/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.rest.user;

import com.cmtb.doctorize.core.user.UserDomain;
import com.cmtb.doctorize.core.user.UserOrchestrator;
import com.cmtb.doctorize.domain.user.AssistantDisplayObject;
import com.cmtb.doctorize.domain.user.ChangePasswordDisplayObject;
import com.cmtb.doctorize.domain.user.LoginDisplayObject;
import com.cmtb.doctorize.domain.user.RoleEnum;
import com.cmtb.doctorize.domain.user.User;
import com.cmtb.doctorize.domain.user.UserDuplicateException;
import com.cmtb.doctorize.domain.user.UserNotFoundException;
import com.cmtb.doctorize.domain.user.UserNotMatchPasswordException;
import javax.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author pc
 */
@CrossOrigin
@RestController
public class UserService {
    
    @Resource(name = "UserOrchestrator")
    private UserOrchestrator userOrchestrator;
    
    @Resource(name = "UserDomain")
    UserDomain userDomain;
    
    @RequestMapping(value = "/account/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody LoginDisplayObject loginDisplayObject) {
        try {        
            
            User result = userDomain.login(loginDisplayObject);

            return new ResponseEntity(result, HttpStatus.OK);
            
        } catch (UserNotFoundException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/account/password/reset", method = RequestMethod.POST)
    public ResponseEntity<?> forgotpassword(@RequestBody LoginDisplayObject loginDisplayObject) {
        try {
            
            Boolean result = userDomain.forgotPassword(loginDisplayObject.getEmail());

            return new ResponseEntity(result, HttpStatus.OK);
            
        } catch (UserNotFoundException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/account/password/change", method = RequestMethod.POST)
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordDisplayObject changePasswordDisplayObject) {
        try {

            Boolean result = userOrchestrator.changePassword(changePasswordDisplayObject);
            
            return new ResponseEntity(result, HttpStatus.OK);
            
        } catch (UserNotFoundException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        } catch (UserNotMatchPasswordException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/account/password/confirmation", method = RequestMethod.POST)
    public ResponseEntity<?> resetPassword(@RequestBody ChangePasswordDisplayObject changePasswordDisplayObject) {
        try {

            Boolean result = userOrchestrator.resetPassword(changePasswordDisplayObject);
            
            return new ResponseEntity(result, HttpStatus.OK);
        } catch (UserNotFoundException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/user/save", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody User user) {
        try {
            user.setRoleId(RoleEnum.DOCTOR.getId());
            User result = userOrchestrator.save(user);
            return new ResponseEntity(result, HttpStatus.OK);
        } catch (UserDuplicateException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/user/inviteAssistant", method = RequestMethod.POST)
    public ResponseEntity<?> inviteAssistant(@RequestBody AssistantDisplayObject assitantDO) {
        try {
            Boolean result = userOrchestrator.inviteAssistant(assitantDO);
            return new ResponseEntity(result, HttpStatus.OK);
        }catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/account/confirmation", method = RequestMethod.POST)
    public ResponseEntity<?> confirmationAccount(@RequestBody ChangePasswordDisplayObject changePasswordDisplayObject) {
        try {

            Boolean result = userOrchestrator.confirmationAccount(changePasswordDisplayObject);
            
            return new ResponseEntity(result, HttpStatus.OK);
        } catch (UserNotFoundException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/account/confirmationAssistant", method = RequestMethod.POST)
    public ResponseEntity<?> confirmationAssistantAccount(@RequestBody AssistantDisplayObject assistantDisplayObject) {
        try {

            Boolean result = userOrchestrator.confirmationAssistantAccount(assistantDisplayObject);
            
            return new ResponseEntity(result, HttpStatus.OK);
        } catch (UserNotFoundException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/user/delete", params = {"userId"}, method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@RequestParam Long userId) {
        try {

            Boolean result = userOrchestrator.delete(userId);
            
            return new ResponseEntity(result, HttpStatus.OK);

        } catch (UserNotFoundException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
