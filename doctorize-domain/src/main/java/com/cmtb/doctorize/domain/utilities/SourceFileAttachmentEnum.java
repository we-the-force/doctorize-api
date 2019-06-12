/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.domain.utilities;

/**
 *
 * @author CMTB
 */
public enum SourceFileAttachmentEnum {
    DATA_IMAGE_64("data:image/png;base64,"),   
    FILE("file");

    private final String sourceFileAttachment;

    SourceFileAttachmentEnum(String sourceFileAttachment) {
        this.sourceFileAttachment = sourceFileAttachment;
    }

    public String getSourceFileAttachmentName() {
        return this.sourceFileAttachment;
    }

}
