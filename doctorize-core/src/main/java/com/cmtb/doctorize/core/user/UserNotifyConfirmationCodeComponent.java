/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.core.user;

import com.cmtb.doctorize.domain.user.ChangePasswordDisplayObject;

/**
 *
 * @author pc
 */
public interface UserNotifyConfirmationCodeComponent {

    public void notify(ChangePasswordDisplayObject changePasswordDisplayObject);
    
}
