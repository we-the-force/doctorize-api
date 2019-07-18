/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.domain.shared;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author pc
 */
public enum MaritalStatusEnum {
    NONE((byte) 0, "None"),
    SINGLE((byte) 1, "Soltero(a)"),
    MARRIED((byte) 2, "Casado(a)"),
    DIVORCED((byte) 3, "Divorciado(a)"),
    WIDOWER((byte) 4, "Viudo(a)"),
    FREE_UNION((byte) 5, "Unión Libre");

    private final Byte maritalStatusId;
    private final String maritalStatusName;

    MaritalStatusEnum(Byte maritalStatus, String maritalStatusName) {
        this.maritalStatusId = maritalStatus;
        this.maritalStatusName = maritalStatusName;
    }

    public Byte getMaritalStatusId() {
        return maritalStatusId;
    }

    public String getMaritalStatusName() {
        return maritalStatusName;
    }

    private static final Map<String, MaritalStatusEnum> maritalStatus = new HashMap<>();

    //this is new
    public static Map<String, MaritalStatusEnum> getMaritalStatus() {
        return maritalStatus;
    }

    static {
        maritalStatus.put(String.valueOf(MaritalStatusEnum.SINGLE.maritalStatus), MaritalStatusEnum.SINGLE);
        maritalStatus.put(String.valueOf(MaritalStatusEnum.MARRIED.maritalStatus), MaritalStatusEnum.MARRIED);
        maritalStatus.put(String.valueOf(MaritalStatusEnum.DIVORCED.maritalStatus), MaritalStatusEnum.DIVORCED);
        maritalStatus.put(String.valueOf(MaritalStatusEnum.WIDOWER.maritalStatus), MaritalStatusEnum.WIDOWER);
        maritalStatus.put(String.valueOf(MaritalStatusEnum.FREE_UNION.maritalStatus), MaritalStatusEnum.FREE_UNION);
    }

    public static List<SimpleDisplayObject> getListMaritalStatus() {
        List<SimpleDisplayObject> list = new ArrayList<>();

        for (Map.Entry<String, MaritalStatusEnum> entry : getMaritalStatus().entrySet()) {
            String key = entry.getKey();
            MaritalStatusEnum value = entry.getValue();
            SimpleDisplayObject DO = new SimpleDisplayObject();
            DO.setId((long) value.getMaritalStatusId());
            DO.setName(value.getMaritalStatusName());
            list.add(DO);
        }
        return list;
    }
}
