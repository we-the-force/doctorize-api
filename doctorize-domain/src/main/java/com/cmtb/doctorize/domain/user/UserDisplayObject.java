/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.domain.user;

import com.cmtb.doctorize.domain.shared.Permissions;
import com.cmtb.doctorize.domain.specialty.Specialty;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author pc
 */
public class UserDisplayObject {
    
    private Long id;
    private String name;
    private String email;
    private String cellphone;
//    private Byte roleId;
    private String photo;
    private Byte status;
    private Specialty specialty;
    private Set<Permissions> permissions = new HashSet<>();
//    private String doctorOfficeName;

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
     * @return the cellphone
     */
    public String getCellphone() {
        return cellphone;
    }

    /**
     * @param cellphone the cellphone to set
     */
    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

//    /**
//     * @return the roleId
//     */
//    public Byte getRoleId() {
//        return roleId;
//    }
//
//    /**
//     * @param roleId the roleId to set
//     */
//    public void setRoleId(Byte roleId) {
//        this.roleId = roleId;
//    }

    /**
     * @return the photo
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * @param photo the photo to set
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    /**
     * @return the status
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * @return the specialty
     */
    public Specialty getSpecialty() {
        return specialty;
    }

    /**
     * @param specialty the specialty to set
     */
    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
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

//    /**
//     * @return the doctorOfficeName
//     */
//    public String getDoctorOfficeName() {
//        return doctorOfficeName;
//    }
//
//    /**
//     * @param doctorOfficeName the doctorOfficeName to set
//     */
//    public void setDoctorOfficeName(String doctorOfficeName) {
//        this.doctorOfficeName = doctorOfficeName;
//    }
}
