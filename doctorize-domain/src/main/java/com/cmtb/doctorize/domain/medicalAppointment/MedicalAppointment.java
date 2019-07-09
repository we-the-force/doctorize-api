/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.domain.medicalAppointment;

import com.cmtb.doctorize.domain.doctor.DoctorOffice;
import com.cmtb.doctorize.domain.user.User;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author pc
 */
@Entity
@Table(name = "`MedicalAppointment`")
public class MedicalAppointment implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "medicalAppointment_seq_gen")
    @SequenceGenerator(name = "medicalAppointment_seq_gen", sequenceName = "medicalAppointment_seq_gen", initialValue = 1, allocationSize = 1)
    private Long id;
    
    @Column(name = "`email`")
    private String email;
    
    @Column(name = "`name`")
    private String name;
    
    @Column(name = "`phone`")
    private String phone;
    
    @Column(name = "`date`", columnDefinition = "timestamp with time zone")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date date;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "`doctorId`")
    private User doctor;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "`doctorOfficeId`")
    private DoctorOffice doctorOffice;

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
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
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
    
}
