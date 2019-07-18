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
public enum GenderEnum {
    
    NONE((byte) 0, "None"),
    FEMALE((byte) 1, "Femenino"),
    MALE((byte) 2, "Masculino");

    private final Byte gender;
    private final String genderName;
    private List<SimpleDisplayObject> listGenders;

    GenderEnum(Byte gender, String genderName) {
        this.gender = gender;
        this.genderName = genderName;
    }

    public Byte getId() {
        return gender;
    }

    public String getGenderName() {
        return genderName;
    }

    private static final Map<String, GenderEnum> genders = new HashMap<>();

    //this is new
    public static Map<String, GenderEnum> getGenders() {
        return genders;
    }

    static {
        genders.put(String.valueOf(GenderEnum.FEMALE.gender), GenderEnum.FEMALE);
        genders.put(String.valueOf(GenderEnum.MALE.gender), GenderEnum.MALE);
    }

    public static List<SimpleDisplayObject> getListGenders() {
        List<SimpleDisplayObject> list = new ArrayList<>();

        for (Map.Entry<String, GenderEnum> entry : genders.entrySet()) {
            String key = entry.getKey();
            GenderEnum value = entry.getValue();

            SimpleDisplayObject DO = new SimpleDisplayObject();
            DO.setId((long) value.getId());
            DO.setName(value.getGenderName());
            list.add(DO);
        }
        return list;
    }
}
