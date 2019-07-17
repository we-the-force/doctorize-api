/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.domain.shared;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author CMTB
 */
public enum ProcessAttachmentEnum {
    USER(1, "userdetails", "png-", (2L * 1024L * 1024L)),
    PATIENT(2, "patient", "png-", (2L * 1024L * 1024L));

    private final String processName;
    private final Integer processId;
    private String permittedExtensions;
    private Long maxSize;

    ProcessAttachmentEnum(Integer processId, String processName,
            String permittedExtensions, Long maxSize) {
        this.processId = processId;
        this.processName = processName;
        this.permittedExtensions = permittedExtensions;
        this.maxSize = maxSize;
    }

    public String getProcessName() {
        return this.processName;
    }

    public Integer getProcessId() {
        return processId;
    }

    public List<String> getPermittedExtensions() {
        String[] extensions = permittedExtensions.split("-");

        List<String> listExtensions = new ArrayList<>(extensions.length);
        listExtensions.addAll(Arrays.asList(extensions));

        return listExtensions;
    }

    public Long getMaxSize() {
        return maxSize;
    }

    private static final Map<String, ProcessAttachmentEnum> processAttachments = new HashMap<>();

    //this is new
    public static Map<String, ProcessAttachmentEnum> getProcessAttachments() {
        return processAttachments;
    }

    static {
        processAttachments.put(
                String.valueOf(ProcessAttachmentEnum.USER.processId),
                ProcessAttachmentEnum.USER);
        processAttachments.put(
                String.valueOf(ProcessAttachmentEnum.PATIENT.processId),
                ProcessAttachmentEnum.PATIENT);

    }

}
