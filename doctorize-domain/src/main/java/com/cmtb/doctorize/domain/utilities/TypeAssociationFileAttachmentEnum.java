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
public enum TypeAssociationFileAttachmentEnum {
    HAS_ONLY_FILE  ("has_only_file"),  //calls constructor with value 3    
    HAS_MANY_FILE  ("has_many_file"),  //calls constructor with value 3    
    ;

    private final String  typeAssociationFile;

    TypeAssociationFileAttachmentEnum(String typeAssociationFile) {
        this.typeAssociationFile = typeAssociationFile;
    }
    
    public String getTypeAssociationName() {
        return this.typeAssociationFile;
    }
    
}
