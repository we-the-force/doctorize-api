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

@Component("UserNotifyCodeChangePasswordComponent")
public class UserNotifyCodeChangePasswordComponentImpl implements UserNotifyCodeChangePasswordComponent {

    @Autowired
    private EmailUtilsComponent emailUtils;

    @Value("${doctorize.url.email.header}")
    private String URL_EMAIL_HEADER;

    @Value("${doctorize.url.email.footer}")
    private String URL_EMAIL_FOOTER;

    @Value("${doctorize.email.copyright}")
    private String URL_EMAIL_COPYRIGHT;
    
    @Value("${doctorize.email.contact}")
    private String EMAIL_CONTACT;
            
    @Value("${doctorize.url.facebook}")
    private String URL_FACEBOOK;
    
    @Value("${doctorize.url.instagram}")
    private String URL_INSTAGRAM;
    
    @Value("${doctorize.url.facebook_image}")
    private String URL_FACEBOOK_IMAGE;
    
    @Value("${doctorize.url.instagram_image}")
    private String URL_INSTAGRAM_IMAGE;
    
    @Value("${doctorize.email.contact_label}")
    private String EMAIL_CONTACT_LABEL;

    @Value("${doctorize.base.url.api}")
    private String BASE_URL_API;
    
    @Value("${doctorize.url.confirm_password}")
    private String URL_CONFIRM_PASS;
    
    @Value("${doctorize.url.confirm_password2}")
    private String URL_CONFIRM_PASS2;

    @Override
    public void notify(ChangePasswordDisplayObject changePasswordDisplayObject) {
        Email email = new Email();

        email.setTo(changePasswordDisplayObject.getEmail());
        email.setEmailProcess(EmailProcessEnum.USER_SEND_CODE_CHANGE_PASSWORD);

        Map<String, String> content = new HashMap<>();
        content.put("url_email_header", BASE_URL_API + URL_EMAIL_HEADER);
        content.put("url_email_footer", BASE_URL_API + URL_EMAIL_FOOTER);
        content.put("email_copyright", URL_EMAIL_COPYRIGHT);
        content.put("email_copyright", URL_EMAIL_COPYRIGHT);
//        content.put("email_contact_label", EMAIL_CONTACT_LABEL);
        content.put("email_contact", EMAIL_CONTACT_LABEL + " " + EMAIL_CONTACT);
        content.put("url_facebook", URL_FACEBOOK);
        content.put("url_instagram", URL_INSTAGRAM);
        content.put("url_facebook_image", BASE_URL_API + URL_FACEBOOK_IMAGE);
        content.put("url_instagram_image", BASE_URL_API + URL_INSTAGRAM_IMAGE);
        content.put("code", changePasswordDisplayObject.getCode());
        content.put("user_name", changePasswordDisplayObject.getUser().getName());
        content.put("user_email", changePasswordDisplayObject.getUser().getEmail());
        content.put("url_confirm_password", URL_CONFIRM_PASS + changePasswordDisplayObject.getUser().getEmail() + URL_CONFIRM_PASS2 + changePasswordDisplayObject.getCode());
        
        
        email.setContent(content);

        emailUtils.send(email);
    }

}
