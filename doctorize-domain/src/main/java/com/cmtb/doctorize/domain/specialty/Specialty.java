/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.domain.specialty;

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
 * @author gealtec
 */
@Entity
@Table(name = "`Specialty`")
public class Specialty implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "specialty_seq_gen")
    @SequenceGenerator(name = "specialty_seq_gen", sequenceName = "specialty_seq_gen", initialValue = 1, allocationSize = 1)
    private Long id;
    
    @Column(name = "`name`")
    private String name;

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
}
