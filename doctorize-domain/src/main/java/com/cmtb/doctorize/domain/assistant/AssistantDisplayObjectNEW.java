/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.domain.assistant;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author gealtec
 */
public class AssistantDisplayObjectNEW {
    
    private Long id;
    private String email;
    private Long doctorId;
    private String code;
    private String name;
    private String password;
    private String imageData;
    private String cellphone;
    private String photo;
    private Set<AssistantDoctorOfficeDisplayObject> offices = new HashSet<>();

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
     * @return the offices
     */
    public Set<AssistantDoctorOfficeDisplayObject> getOffices() {
        return offices;
    }

    /**
     * @param offices the offices to set
     */
    public void setOffices(Set<AssistantDoctorOfficeDisplayObject> offices) {
        this.offices = offices;
    }
}
