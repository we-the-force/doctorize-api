/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.domain.shared;

/**
 *
 * @author CMTB
 */
public enum ModuleEnum {
    USER("user"),
    PATIENT("patient");

    private final String module;

    ModuleEnum(String module) {
        this.module = module;
    }

    public String getModuleName() {
        return this.module;
    }
}
