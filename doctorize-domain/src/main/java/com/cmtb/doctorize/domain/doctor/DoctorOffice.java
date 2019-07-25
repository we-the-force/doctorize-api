/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.domain.doctor;

import com.cmtb.doctorize.domain.user.User;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

/**
 *
 * @author pc
 */
@Entity
@Table(name = "`DoctorOffice`")
public class DoctorOffice implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doctorOffice_seq_gen")
    @SequenceGenerator(name = "doctorOffice_seq_gen", sequenceName = "doctorOffice_seq_gen", initialValue = 1, allocationSize = 1)
    private Long id;
    
    @Column(name = "`name`")
    private String name;
    
    @Column(name = "`email`")
    private String email;
    
    @Column(name = "`phone`")
    private String phone;
    
    @Column(name = "`hospital`")
    private String hospital;
    
    @Column(name = "`number`")
    private String number;
    
    @Column(name = "`address`")
    private String address;
    
    @Column(name = "lat")
    private Double lat;

    @Column(name = "lng")
    private Double lng;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "office")
    private Set<AvailableDays> availableDays = new HashSet<>();
    
    @Column(name = "startTime")
    private String startTime;

    @Column(name = "closeTime")
    private String closeTime;
    
    @Column(name = "lunchStartTime")
    private String lunchStartTime;

    @Column(name = "lunchCloseTime")
    private String lunchCloseTime;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "doctorOffice")
    private Set<UserDoctorOffice> userDoctorOffices = new HashSet<>();
    
    @Column(name = "`status`")
    private Byte status;
    
    @JoinColumn(name = "`duration`")
    private Short duration;

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
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the hospital
     */
    public String getHospital() {
        return hospital;
    }

    /**
     * @param hospital the hospital to set
     */
    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    /**
     * @return the number
     */
    public String getNumber() {
        return number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the lat
     */
    public Double getLat() {
        return lat;
    }

    /**
     * @param lat the lat to set
     */
    public void setLat(Double lat) {
        this.lat = lat;
    }

    /**
     * @return the lng
     */
    public Double getLng() {
        return lng;
    }

    /**
     * @param lng the lng to set
     */
    public void setLng(Double lng) {
        this.lng = lng;
    }

    /**
     * @return the availableDays
     */
    public Set<AvailableDays> getAvailableDays() {
        return availableDays;
    }

    /**
     * @param availableDays the availableDays to set
     */
    public void setAvailableDays(Set<AvailableDays> availableDays) {
        this.availableDays = availableDays;
    }

    /**
     * @return the startTime
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the closeTime
     */
    public String getCloseTime() {
        return closeTime;
    }

    /**
     * @param closeTime the closeTime to set
     */
    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    /**
     * @return the lunchStartTime
     */
    public String getLunchStartTime() {
        return lunchStartTime;
    }

    /**
     * @param lunchStartTime the lunchStartTime to set
     */
    public void setLunchStartTime(String lunchStartTime) {
        this.lunchStartTime = lunchStartTime;
    }

    /**
     * @return the lunchCloseTime
     */
    public String getLunchCloseTime() {
        return lunchCloseTime;
    }

    /**
     * @param lunchCloseTime the lunchCloseTime to set
     */
    public void setLunchCloseTime(String lunchCloseTime) {
        this.lunchCloseTime = lunchCloseTime;
    }

    /**
     * @return the userDoctorOffices
     */
    public Set<UserDoctorOffice> getUserDoctorOffices() {
        return userDoctorOffices;
    }

    /**
     * @param userDoctorOffices the userDoctorOffices to set
     */
    public void setUserDoctorOffices(Set<UserDoctorOffice> userDoctorOffices) {
        this.userDoctorOffices = userDoctorOffices;
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
     * @return the duration
     */
    public Short getDuration() {
        return duration;
    }

    /**
     * @param duration the duration to set
     */
    public void setDuration(Short duration) {
        this.duration = duration;
    }
}
