/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.core.catalogs;

import com.cmtb.doctorize.domain.catalogs.Diagnostic;

/**
 *
 * @author pc
 */
public interface DiagnosticOrchestrator {

    public Boolean delete(Long diagnosticId);

    public Diagnostic save(Diagnostic diagnostic);

    public Boolean update(Diagnostic diagnostic);
    
}
