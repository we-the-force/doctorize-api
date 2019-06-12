/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.domain.shared;

import java.io.File;

/**
 *
 * @author CMTB
 */
public enum ResourcesEnum {
    
    
    
    ROOT_FOLDER ("files"),  //calls constructor with value 3
    ROOT_FILES  ("opt" + File.separator + "tomcat" + File.separator + "webapps"+ File.separator + "doctorize-rest-core" + File.separator + 
            "WEB-INF" + File.separator + "classes" + File.separator + "public" + File.separator),  //calls constructor with value 3
    
    ;

    private final String resource;

    ResourcesEnum(String resource) {
        this.resource = resource;
    }
    
    public String getResourceName() {
        return this.resource;
    }
    
}