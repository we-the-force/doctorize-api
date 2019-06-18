/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.core.user;

import com.cmtb.doctorize.domain.user.AssistantDisplayObject;
import com.cmtb.doctorize.domain.utilities.Email;
import com.cmtb.doctorize.domain.utilities.EmailProcessEnum;
import com.cmtb.doctorize.utilities.email.EmailUtilsComponent;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("AssistantNotifyConfirmationCodeComponent")
public class AssistantNotifyConfirmationCodeComponentImpl implements AssistantNotifyConfirmationCodeComponent {

    @Autowired
    private EmailUtilsComponent emailUtils;

    @Value("${doctorize.base.url.api}")
    private String BASE_URL_API;
    
    @Value("${doctorize.url.confirm_account}")
    private String URL_CONFIRM_PASS;
    
    @Value("${doctorize.url.confirm_account2}")
    private String URL_CONFIRM_PASS2;

    @Override
    public void notify(AssistantDisplayObject assistantDisplayObject) {
        Email email = new Email();

        email.setTo(assistantDisplayObject.getEmail());
        email.setEmailProcess(EmailProcessEnum.ASSISTANT_SEND_CONFIRMATION_CODE);

        Map<String, String> content = new HashMap<>();
        content.put("code", assistantDisplayObject.getCode());
        content.put("user_name", assistantDisplayObject.getName());
        content.put("user_email", assistantDisplayObject.getEmail());
        content.put("url_confirm_password", URL_CONFIRM_PASS + assistantDisplayObject.getEmail() + URL_CONFIRM_PASS2 + assistantDisplayObject.getCode());
        
        email.setContent(content);

        emailUtils.send(email);
    }

}
