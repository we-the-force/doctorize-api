/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.domain.doctor;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pc
 */
public class DoctorOfficeDisplayObject {
    
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String hospital;
    private String number;
    private String address;
    private Double lat;
    private Double lng;
    private String startTime;
    private String closeTime;
    private Long userId;
    private List<Byte> days = new ArrayList<>();
    private String lunchStartTime;
    private String lunchCloseTime; 
    private Short duration;

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
     * @return the userId
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @return the days
     */
    public List<Byte> getDays() {
        return days;
    }

    /**
     * @param days the days to set
     */
    public void setDays(List<Byte> days) {
        this.days = days;
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
