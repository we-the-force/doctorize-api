/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.domain.assistant;

import com.cmtb.doctorize.domain.shared.Permissions;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author pc
 */
public class AssistantDisplayObject {
    
    private String email;
    private Long doctorId;
    private Long officeId;
    private String code;
    private String name;
    private String password;
    private String imageData;
    private Set<Permissions> permissions = new HashSet<>();

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the doctorId
     */
    public Long getDoctorId() {
        return doctorId;
    }

    /**
     * @param doctorId the doctorId to set
     */
    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    /**
     * @return the officeId
     */
    public Long getOfficeId() {
        return officeId;
    }

    /**
     * @param officeId the officeId to set
     */
    public void setOfficeId(Long officeId) {
        this.officeId = officeId;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the imageData
     */
    public String getImageData() {
        return imageData;
    }

    /**
     * @param imageData the imageData to set
     */
    public void setImageData(String imageData) {
        this.imageData = imageData;
    }

    /**
     * @return the permissions
     */
    public Set<Permissions> getPermissions() {
        return permissions;
    }

    /**
     * @param permissions the permissions to set
     */
    public void setPermissions(Set<Permissions> permissions) {
        this.permissions = permissions;
    }
}
