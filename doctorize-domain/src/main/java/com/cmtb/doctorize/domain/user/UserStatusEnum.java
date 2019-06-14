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
public enum UserStatusEnum {
    
    DISABLE((byte) 0, "Inactivo"),
    ACTIVE((byte) 1, "Activo"),
    UNCONFIRMED((byte) 2, "No confirmado");

    private final Byte statusId;
    private final String statusName;

    UserStatusEnum(Byte statusId, String statusName) {
        this.statusId = statusId;
        this.statusName = statusName;
    }

    public Byte getId() {
        return statusId;
    }

    public String getName() {
        return statusName;
    }
}
