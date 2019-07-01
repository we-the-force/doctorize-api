/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.domain.doctor;

import com.cmtb.doctorize.domain.user.User;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author pc
 */
@Entity
@Table(name = "`UserDoctorOffice`")
public class UserDoctorOffice implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userDoctorOffice_seq_gen")
    @SequenceGenerator(name = "userDoctorOffice_seq_gen", sequenceName = "userDoctorOffice_seq_gen", initialValue = 1, allocationSize = 1)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "`userId`", nullable = true)
    private User user;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "`doctorOfficeId`", nullable = true)
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
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
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
