/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.rest.user;

import com.cmtb.doctorize.core.user.UserDomain;
import com.cmtb.doctorize.core.user.UserOrchestrator;
import com.cmtb.doctorize.domain.shared.ConfirmationCodeExceptoin;
import com.cmtb.doctorize.domain.shared.ItemNotFoundException;
import com.cmtb.doctorize.domain.assistant.AssistantDisplayObject;
import com.cmtb.doctorize.domain.assistant.AssistantDisplayObjectNEW;
import com.cmtb.doctorize.domain.user.ChangePasswordDisplayObject;
import com.cmtb.doctorize.domain.user.LoginDisplayObject;
import com.cmtb.doctorize.domain.user.RoleEnum;
import com.cmtb.doctorize.domain.user.User;
import com.cmtb.doctorize.domain.user.UserConfirmedException;
import com.cmtb.doctorize.domain.user.UserDisabledException;
import com.cmtb.doctorize.domain.user.UserDisplayObject;
import com.cmtb.doctorize.domain.user.UserDuplicateException;
import com.cmtb.doctorize.domain.shared.NotFoundException;
import com.cmtb.doctorize.domain.user.LoginContainerDisplayObject;
import com.cmtb.doctorize.domain.user.UserNotMatchPasswordException;
import com.cmtb.doctorize.domain.user.UserUnconfirmedException;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
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
            
            LoginContainerDisplayObject result = userDomain.login(loginDisplayObject);
            
            if(result.getRoleId().equals(RoleEnum.DOCTOR.getId())){
                return new ResponseEntity(result.getDoctor(), HttpStatus.OK);
            }else{
                return new ResponseEntity(result.getAssistant(), HttpStatus.OK);
            }
            
        } catch (NotFoundException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (UserDisabledException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/account/password/reset", method = RequestMethod.POST)
    public ResponseEntity<?> forgotpassword(@RequestBody LoginDisplayObject loginDisplayObject) {
        try {
            
            Boolean result = userDomain.forgotPassword(loginDisplayObject.getEmail());

            return new ResponseEntity(result, HttpStatus.OK);
            
        } catch (NotFoundException ex) {
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
            
        } catch (NotFoundException ex) {
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
        } catch (NotFoundException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/account/confirmation", method = RequestMethod.POST)
    public ResponseEntity<?> confirmationAccount(@RequestBody ChangePasswordDisplayObject changePasswordDisplayObject) {
        try {

            Boolean result = userOrchestrator.confirmationAccount(changePasswordDisplayObject);
            
            return new ResponseEntity(result, HttpStatus.OK);
        } catch (UserConfirmedException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.CONFLICT);
        } catch (NotFoundException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (ConfirmationCodeExceptoin ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/account/confirmation/assistant", method = RequestMethod.POST)
    public ResponseEntity<?> confirmationAssistantAccount(@RequestBody AssistantDisplayObject assistantDisplayObject) {
        try {

            Boolean result = userOrchestrator.confirmationAssistantAccount(assistantDisplayObject);
            
            return new ResponseEntity(result, HttpStatus.OK);
        } catch (UserConfirmedException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.CONFLICT);
        } catch (NotFoundException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (ConfirmationCodeExceptoin ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/doctors", method = RequestMethod.POST)
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
    
    @RequestMapping(value = "/doctors/{doctorId}", method = RequestMethod.PATCH)
    public ResponseEntity<?> patch(@PathVariable("doctorId") Long doctorId, @RequestBody Map<String, Object> userMap) {
        try {
            userMap.put("id", doctorId);
            Boolean result = userOrchestrator.patch(userMap);
            return new ResponseEntity(result, HttpStatus.OK);
        } catch (ItemNotFoundException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/doctors/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable("id") Long userId) {
        try {

            UserDisplayObject result = userDomain.getById(userId);
            return new ResponseEntity(result, HttpStatus.OK);

        } catch (UserUnconfirmedException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (ItemNotFoundException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/doctors/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Long userId) {
        try {

            Boolean result = userOrchestrator.delete(userId);
            
            return new ResponseEntity(result, HttpStatus.OK);

        } catch (NotFoundException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/doctors/{doctorId}/assistants", method = RequestMethod.POST)
    public ResponseEntity<?> inviteAssistant(@PathVariable("doctorId") Long doctorId, @RequestBody AssistantDisplayObject assitantDO) {
        try {
            assitantDO.setDoctorId(doctorId);
            Boolean result = userOrchestrator.inviteAssistant(assitantDO);
            return new ResponseEntity(result, HttpStatus.OK);
        }catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/doctors/{doctorId}/assistants", method = RequestMethod.GET)
    public ResponseEntity<?> getListByDoctorId(@PathVariable("doctorId") Long doctorId) {
        try {

            List<AssistantDisplayObjectNEW> result = userDomain.getListByDoctorId(doctorId);
            return new ResponseEntity(result, HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/doctors/{doctorId}/assistants/{assistantId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAssistant(@PathVariable("doctorId") Long doctorId, @PathVariable("assistantId") Long assistantId) {
        try {

            Boolean result = userOrchestrator.deleteAssistantByIdAndDoctor(assistantId, doctorId);
            
            return new ResponseEntity(result, HttpStatus.OK);

        } catch (NotFoundException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "doctors/{doctorId}/assistants/{assistantId}", method = RequestMethod.GET)
    public ResponseEntity<?> getAssistantById(@PathVariable("doctorId") Long doctorId, @PathVariable("assistantId") Long assistantId) {
        try {

            AssistantDisplayObjectNEW result = userDomain.getAssistantByIdAndDoctor(assistantId, doctorId);
            return new ResponseEntity(result, HttpStatus.OK);

        } catch (ItemNotFoundException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/doctors/{doctorId}/assistants/{assistantId}", method = RequestMethod.PATCH)
    public ResponseEntity<?> updateAsistant(@PathVariable("doctorId") Long doctorId, @PathVariable("assistantId") Long assistantId,
            @RequestBody AssistantDisplayObjectNEW assitantDO) {
        try {
            assitantDO.setId(assistantId);
            AssistantDisplayObjectNEW result = userOrchestrator.addUpdateAssistantDoctorOffice(assitantDO);
            
            return new ResponseEntity(result, HttpStatus.OK);

        } catch (NotFoundException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
