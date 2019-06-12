/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.core.user;

import com.cmtb.doctorize.domain.user.User;
import com.cmtb.doctorize.domain.utilities.AttachmentResultDisplayObject;

/**
 *
 * @author pc
 */
public interface UserAttachmentImagesComponent {

    public AttachmentResultDisplayObject attachementPhoto(User user);
    
}
