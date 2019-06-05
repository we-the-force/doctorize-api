/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.core.shared;

import com.cmtb.doctorize.domain.user.User;

/**
 *
 * @author pc
 */
public interface SecurityComponent {

    public String createToken(User user);
    
}
