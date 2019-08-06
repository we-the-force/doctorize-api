/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.domain.patient;

import com.cmtb.doctorize.domain.doctor.DoctorOffice;
import com.cmtb.doctorize.domain.medicalAppointment.MedicalAppointment;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

/**
 *
 * @author pc
 */
@Entity
@Table(name = "`Patient`")
public class Patient implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patient_seq_gen")
    @SequenceGenerator(name = "patient_seq_gen", sequenceName = "patient_seq_gen", initialValue = 1, allocationSize = 1)
    private Long id;
    
    @Column(name = "`name`")
    private String name;
    
    @Column(name = "`email`", nullable = false, unique = true)
    private String email;
    
    @Column(name = "`cellphone`")
    private String cellphone;
    
    @Column(name = "`birthdate`")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date birthdate;
    
    @Column(name = "`gender`")
    private Byte gender;
    
    @Column(name = "`maritalStatus`")
    private Byte maritalStatus;
    
    @Column(name = "`weight`")
    private Integer weight;
    
    @Column(name = "`height`")
    private Integer height;
    
    @Column(name = "`bloodtype`")
    private Byte bloodType;
    
    @Column(name = "`bloodPressure`")
    private String bloodPressure;
    
    @Column(name = "`photo`")
    private String photo;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "patient")
    private Set<MedicalAppointment> medicalAppointments = new HashSet<>();
    
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "`userId`")
//    private User doctor;
//    
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "`doctorOfficeId`")
//    private DoctorOffice doctorOffice;
//    
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "medicalAppointmentId")
//    private MedicalAppointment medicalAppointment;
    
    @Transient
    private String imageData;

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

    /**
     * @return the birthdate
     */
    public Date getBirthdate() {
        return birthdate;
    }

    /**
     * @param birthdate the birthdate to set
     */
    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    /**
     * @return the gender
     */
    public Byte getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(Byte gender) {
        this.gender = gender;
    }

    /**
     * @return the maritalStatus
     */
    public Byte getMaritalStatus() {
        return maritalStatus;
    }

    /**
     * @param maritalStatus the maritalStatus to set
     */
    public void setMaritalStatus(Byte maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    /**
     * @return the weight
     */
    public Integer getWeight() {
        return weight;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    /**
     * @return the height
     */
    public Integer getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(Integer height) {
        this.height = height;
    }

    /**
     * @return the bloodType
     */
    public Byte getBloodType() {
        return bloodType;
    }

    /**
     * @param bloodType the bloodType to set
     */
    public void setBloodType(Byte bloodType) {
        this.bloodType = bloodType;
    }

    /**
     * @return the bloodPressure
     */
    public String getBloodPressure() {
        return bloodPressure;
    }

    /**
     * @param bloodPressure the bloodPressure to set
     */
    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
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

//    /**
//     * @return the doctor
//     */
//    public User getDoctor() {
//        return doctor;
//    }
//
//    /**
//     * @param doctor the doctor to set
//     */
//    public void setDoctor(User doctor) {
//        this.doctor = doctor;
//    }
//
//    /**
//     * @return the doctorOffice
//     */
//    public DoctorOffice getDoctorOffice() {
//        return doctorOffice;
//    }
//
//    /**
//     * @param doctorOffice the doctorOffice to set
//     */
//    public void setDoctorOffice(DoctorOffice doctorOffice) {
//        this.doctorOffice = doctorOffice;
//    }
//
//    /**
//     * @return the medicalAppointment
//     */
//    public MedicalAppointment getMedicalAppointment() {
//        return medicalAppointment;
//    }
//
//    /**
//     * @param medicalAppointment the medicalAppointment to set
//     */
//    public void setMedicalAppointment(MedicalAppointment medicalAppointment) {
//        this.medicalAppointment = medicalAppointment;
//    }

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
     * @return the medicalAppointments
     */
    public Set<MedicalAppointment> getMedicalAppointments() {
        return medicalAppointments;
    }

    /**
     * @param medicalAppointments the medicalAppointments to set
     */
    public void setMedicalAppointments(Set<MedicalAppointment> medicalAppointments) {
        this.medicalAppointments = medicalAppointments;
    }
}
