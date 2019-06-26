/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.domain.doctor;

import java.io.Serializable;
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

/**
 *
 * @author pc
 */
@Entity
@Table(name = "`AvailableDays`")
public class AvailableDays implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "availabledays_seq_gen")
    @SequenceGenerator(name = "availabledays_seq_gen", sequenceName = "availabledays_seq_gen", initialValue = 1)
    private Long id;
    
    @Column(name = "day")
    private Byte day;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "`doctorOfficeId`")
    private DoctorOffice office;

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
     * @return the day
     */
    public Byte getDay() {
        return day;
    }

    /**
     * @param day the day to set
     */
    public void setDay(Byte day) {
        this.day = day;
    }

    /**
     * @return the office
     */
    public DoctorOffice getOffice() {
        return office;
    }

    /**
     * @param office the office to set
     */
    public void setOffice(DoctorOffice office) {
        this.office = office;
    }
}
