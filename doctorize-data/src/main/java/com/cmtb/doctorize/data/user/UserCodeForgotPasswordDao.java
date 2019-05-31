/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.data.user;

import com.cmtb.doctorize.domain.user.ChangePasswordDisplayObject;
import com.cmtb.doctorize.domain.user.UserCodeForgotPassword;

/**
 *
 * @author pc
 */
public interface UserCodeForgotPasswordDao {

    public void deleteCodeByUserId(Long userId);
    
    public Long createCodeChangePassword(UserCodeForgotPassword userCodeForgotPassword);
    
    public UserCodeForgotPassword existAssociatedCodeByPatient(ChangePasswordDisplayObject changePasswordDisplayObject);
    
}
