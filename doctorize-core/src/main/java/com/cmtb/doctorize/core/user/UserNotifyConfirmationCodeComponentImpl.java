/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.core.user;

import com.cmtb.doctorize.domain.user.ChangePasswordDisplayObject;
import com.cmtb.doctorize.domain.utilities.Email;
import com.cmtb.doctorize.domain.utilities.EmailProcessEnum;
import com.cmtb.doctorize.utilities.email.EmailUtilsComponent;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("UserNotifyConfirmationCodeComponent")
public class UserNotifyConfirmationCodeComponentImpl implements UserNotifyConfirmationCodeComponent {

    @Autowired
    private EmailUtilsComponent emailUtils;

    @Value("${doctorize.base.url.api}")
    private String BASE_URL_API;
    
    @Value("${doctorize.url.confirm_account}")
    private String URL_CONFIRM_PASS;
    
    @Value("${doctorize.url.confirm_account2}")
    private String URL_CONFIRM_PASS2;

    @Override
    public void notify(ChangePasswordDisplayObject changePasswordDisplayObject) {
        Email email = new Email();

        email.setTo(changePasswordDisplayObject.getEmail());
        email.setEmailProcess(EmailProcessEnum.USER_SEND_CONFIRMATION_CODE);

        Map<String, String> content = new HashMap<>();
        content.put("code", changePasswordDisplayObject.getCode());
        content.put("user_name", changePasswordDisplayObject.getUser().getName());
        content.put("user_email", changePasswordDisplayObject.getUser().getEmail());
        content.put("url_confirm_password", URL_CONFIRM_PASS + changePasswordDisplayObject.getUser().getEmail() + URL_CONFIRM_PASS2 + changePasswordDisplayObject.getCode());
        
        email.setContent(content);

        emailUtils.send(email);
    }

}
