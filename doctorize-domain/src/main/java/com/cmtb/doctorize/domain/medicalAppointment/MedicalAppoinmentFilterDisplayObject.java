/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.domain.medicalAppointment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author pc
 */
public class MedicalAppoinmentFilterDisplayObject {
    
    private Long doctorId;
    private List<String> filter = new ArrayList<>();
    private List<String> search = new ArrayList<>();
    private Long office;
    private String email;
    private Date startDate;
    private Date endDate;

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
     * @return the filter
     */
    public List<String> getFilter() {
        return filter;
    }

    /**
     * @param filter the filter to set
     */
    public void setFilter(List<String> filter) {
        this.filter = filter;
    }

    /**
     * @return the search
     */
    public List<String> getSearch() {
        return search;
    }

    /**
     * @param search the search to set
     */
    public void setSearch(List<String> search) {
        this.search = search;
    }

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the office
     */
    public Long getOffice() {
        return office;
    }

    /**
     * @param office the office to set
     */
    public void setOffice(Long office) {
        this.office = office;
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
}
