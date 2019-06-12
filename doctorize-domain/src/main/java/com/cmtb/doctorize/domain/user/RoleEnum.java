/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.domain.user;

/**
 *
 * @author pc
 */
public enum RoleEnum {
    
    NONE((byte) 0, "None"),
    
    DOCTOR((byte) 1, "Doctor"),
    ASSISTANT((byte) 2, "Asistente");

    private final Byte roleId;
    private final String roleName;

    RoleEnum(Byte roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public Byte getId() {
        return roleId;
    }

    public String getName() {
        return roleName;
    }
}
