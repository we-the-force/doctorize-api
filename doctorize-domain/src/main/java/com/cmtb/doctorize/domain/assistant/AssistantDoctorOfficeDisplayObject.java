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
 * @author gealtec
 */
public class AssistantDoctorOfficeDisplayObject {
    
    private Long officeId;
    private Long doctorId;
    private String officeName;
    private Set<Permissions> permissions = new HashSet<>();

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
     * @return the officeName
     */
    public String getOfficeName() {
        return officeName;
    }

    /**
     * @param officeName the officeName to set
     */
    public void setOfficeName(String officeName) {
        this.officeName = officeName;
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
    
}
