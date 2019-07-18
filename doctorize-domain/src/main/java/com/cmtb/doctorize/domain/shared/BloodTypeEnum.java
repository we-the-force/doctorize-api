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
public enum BloodTypeEnum {
    
    NONE((byte) 0, "None"),
    A_POSITIVE((byte) 1, "A+"),
    A_NEGATIVE((byte) 2, "A-"),
    B_POSITIVE((byte) 3, "B+"),
    B_NEGATIVE((byte) 4, "B-"),
    AB_POSITIVE((byte) 5, "AB+"),
    AB_NEGATIVE((byte) 6, "AB-"),
    O_POSITIVE((byte) 7, "O+"),
    O_NEGATIVE((byte) 8, "O-");

    private final Byte bloodType;
    private final String bloodTypeName;

    BloodTypeEnum(Byte bloodType, String boodTypeName) {
        this.bloodType = bloodType;
        this.bloodTypeName = boodTypeName;
    }

    public Byte getBloodType() {
        return bloodType;
    }

    public String getBloodTypeName() {
        return bloodTypeName;
    }

    private static final Map<String, BloodTypeEnum> bloodTypes = new HashMap<>();

    //this is new
    public static Map<String, BloodTypeEnum> getBloodTypes() {
        return bloodTypes;
    }

    static {
        bloodTypes.put(String.valueOf(BloodTypeEnum.A_POSITIVE.bloodType), BloodTypeEnum.A_POSITIVE);
        bloodTypes.put(String.valueOf(BloodTypeEnum.A_NEGATIVE.bloodType), BloodTypeEnum.A_NEGATIVE);
        bloodTypes.put(String.valueOf(BloodTypeEnum.B_POSITIVE.bloodType), BloodTypeEnum.B_POSITIVE);
        bloodTypes.put(String.valueOf(BloodTypeEnum.B_NEGATIVE.bloodType), BloodTypeEnum.B_NEGATIVE);
        bloodTypes.put(String.valueOf(BloodTypeEnum.AB_POSITIVE.bloodType), BloodTypeEnum.AB_POSITIVE);
        bloodTypes.put(String.valueOf(BloodTypeEnum.AB_NEGATIVE.bloodType), BloodTypeEnum.AB_NEGATIVE);
        bloodTypes.put(String.valueOf(BloodTypeEnum.O_POSITIVE.bloodType), BloodTypeEnum.O_POSITIVE);
        bloodTypes.put(String.valueOf(BloodTypeEnum.O_NEGATIVE.bloodType), BloodTypeEnum.O_NEGATIVE);
    }

    public static List<SimpleDisplayObject> getListBloodType() {
        List<SimpleDisplayObject> list = new ArrayList<>();

        for (Map.Entry<String, BloodTypeEnum> entry : getBloodTypes().entrySet()) {
            String key = entry.getKey();
            BloodTypeEnum value = entry.getValue();
            SimpleDisplayObject DO = new SimpleDisplayObject();
            DO.setId((long) value.getBloodType());
            DO.setName(value.getBloodTypeName());
            list.add(DO);
        }
        return list;
    }
}
