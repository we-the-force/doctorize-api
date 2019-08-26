/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.core.catalogs;

import com.cmtb.doctorize.domain.catalogs.Diagnostic;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pc
 */
@Component(value = "DiagnosticOrchestrator")
public class DiagnosticOrchestratorImpl implements DiagnosticOrchestrator {
    
    @Resource(name = "DiagnosticDomain")
    private DiagnosticDomain diagnosticDomain;
    
    @Transactional
    @Override
    public Diagnostic save(Diagnostic diagnostic){
        return diagnosticDomain.save(diagnostic);
    }
    
    @Transactional
    @Override
    public Boolean delete(Long diagnosticId){
        return diagnosticDomain.delete(diagnosticId);
    }
    
    @Transactional
    @Override
    public Boolean update(Diagnostic diagnostic){
        return diagnosticDomain.update(diagnostic);
    }
}
