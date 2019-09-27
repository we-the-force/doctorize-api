/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.domain.user;

import com.cmtb.doctorize.domain.assistant.AssistantDisplayObjectNEW;

/**
 *
 * @author pc
 */
public class LoginContainerDisplayObject {
    
    private User doctor;
    private AssistantDisplayObjectNEW assistant;
    private Byte roleId;

    /**
     * @return the doctor
     */
    public User getDoctor() {
        return doctor;
    }

    /**
     * @param doctor the doctor to set
     */
    public void setDoctor(User doctor) {
        this.doctor = doctor;
    }

    /**
     * @return the assistant
     */
    public AssistantDisplayObjectNEW getAssistant() {
        return assistant;
    }

    /**
     * @param assistant the assistant to set
     */
    public void setAssistant(AssistantDisplayObjectNEW assistant) {
        this.assistant = assistant;
    }

    /**
     * @return the roleId
     */
    public Byte getRoleId() {
        return roleId;
    }

    /**
     * @param roleId the roleId to set
     */
    public void setRoleId(Byte roleId) {
        this.roleId = roleId;
    }
}
