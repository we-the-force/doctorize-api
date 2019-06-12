/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.core.shared;

import com.cmtb.doctorize.domain.shared.ModuleEnum;
import com.cmtb.doctorize.domain.shared.ProcessAttachmentEnum;
import com.cmtb.doctorize.domain.utilities.AttachmentDisplayObject;


/**
 *
 * @author pc
 */
public interface AttachmentComponent {

    public String attachment(AttachmentDisplayObject file, Boolean deleteBeforeAttachment);

    public boolean deleteAttachementByPath(String path);
    
    public boolean deleteAttachementAndDirectoryByPath(String path);

    public boolean deleteAttachmentWhenRelationIsOnlyFile(Long identifierId, ModuleEnum module, ProcessAttachmentEnum process);
    
}
