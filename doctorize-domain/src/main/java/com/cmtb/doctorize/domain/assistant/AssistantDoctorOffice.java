/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.domain.assistant;

import com.cmtb.doctorize.domain.doctor.DoctorOffice;
import com.cmtb.doctorize.domain.shared.Permissions;
import com.cmtb.doctorize.domain.user.User;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author gealtec
 */
@Entity
@Table(name = "`AssistantDoctorOffice`")
public class AssistantDoctorOffice implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "`assistantId`")
    private User assistant;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "`doctorId`")
    private User doctor;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "`doctorOfficeId`")
    private DoctorOffice doctorOffice;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "`AssistantDoctorOfficePermissions`", joinColumns = {
        @JoinColumn(name = "`assistantDoctorOfficeId`", nullable = false, updatable = false)},
            inverseJoinColumns = {
                @JoinColumn(name = "`permissionId`",
                        nullable = false, updatable = false)})
    private Set<Permissions> permissions = new HashSet<>();

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the assistant
     */
    public User getAssistant() {
        return assistant;
    }

    /**
     * @param assistant the assistant to set
     */
    public void setAssistant(User assistant) {
        this.assistant = assistant;
    }

    /**
     * @return the doctorOffice
     */
    public DoctorOffice getDoctorOffice() {
        return doctorOffice;
    }

    /**
     * @param doctorOffice the doctorOffice to set
     */
    public void setDoctorOffice(DoctorOffice doctorOffice) {
        this.doctorOffice = doctorOffice;
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
    
}
