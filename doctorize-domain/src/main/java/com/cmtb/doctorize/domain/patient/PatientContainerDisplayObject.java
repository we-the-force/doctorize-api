/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.domain.patient;

import com.cmtb.doctorize.domain.shared.SimpleDisplayObject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pc
 */
public class PatientContainerDisplayObject {
    
    private List<SimpleDisplayObject> genders = new ArrayList<>();
    private List<SimpleDisplayObject> bloodTypes = new ArrayList<>();
    private List<SimpleDisplayObject> maritalStatus = new ArrayList<>();
//    private PatientDisplayObject patient;

    /**
     * @return the genders
     */
    public List<SimpleDisplayObject> getGenders() {
        return genders;
    }

    /**
     * @param genders the genders to set
     */
    public void setGenders(List<SimpleDisplayObject> genders) {
        this.genders = genders;
    }

    /**
     * @return the bloodTypes
     */
    public List<SimpleDisplayObject> getBloodTypes() {
        return bloodTypes;
    }

    /**
     * @param bloodTypes the bloodTypes to set
     */
    public void setBloodTypes(List<SimpleDisplayObject> bloodTypes) {
        this.bloodTypes = bloodTypes;
    }

    /**
     * @return the maritalStatus
     */
    public List<SimpleDisplayObject> getMaritalStatus() {
        return maritalStatus;
    }

    /**
     * @param maritalStatus the maritalStatus to set
     */
    public void setMaritalStatus(List<SimpleDisplayObject> maritalStatus) {
        this.maritalStatus = maritalStatus;
    }
}
