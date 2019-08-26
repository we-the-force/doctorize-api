/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.core.catalogs;

import com.cmtb.doctorize.domain.catalogs.Diagnostic;
import java.util.List;

/**
 *
 * @author pc
 */
public interface DiagnosticDomain {

    public Boolean delete(Long diagnosticId);

    public Diagnostic get(Long diagnosticId);

    public List<Diagnostic> getList();

    public Diagnostic save(Diagnostic diagnostic);

    public Boolean update(Diagnostic diagnostic);
    
}
