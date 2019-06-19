/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.domain.doctor;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
    
    @Column(name = "lat")
    private Double lat;

    @Column(name = "lng")
    private Double lng;
}
