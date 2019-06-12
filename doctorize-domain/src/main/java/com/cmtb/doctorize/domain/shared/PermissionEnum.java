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
public enum PermissionEnum {
    UNCHECKED(-1),
    NONE(0),
    SCHEDULE(1),
    PATIENTS(2),
    MEDICAL_RECORD(3),
    PAYMENTS(4);

    private int permissionId;

    public int getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }

    PermissionEnum(int permissionId) {
        this.permissionId = permissionId;
    }

}
