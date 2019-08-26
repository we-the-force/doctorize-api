/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.core.catalogs;

import com.cmtb.doctorize.data.catalogs.DiagnosticDao;
import com.cmtb.doctorize.domain.catalogs.Diagnostic;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 *
 * @author pc
 */
@Component(value = "DiagnosticDomain")
public class DiagnosticDomainImpl implements DiagnosticDomain {
    
    @Resource(name = "DiagnosticDao")
    private DiagnosticDao diagnosticDao;
    
    @Override
    public Diagnostic save(Diagnostic diagnostic){
        return diagnosticDao.save(diagnostic);
    }
    
    @Override
    public Diagnostic get(Long diagnosticId) {
        return diagnosticDao.get(diagnosticId);
    }
    
    @Override
    public List<Diagnostic> getList() {
        return diagnosticDao.getList();
    }
    
    @Override
    public Boolean update(Diagnostic diagnostic){
        return diagnosticDao.update(diagnostic);
    }
    
    @Override
    public Boolean delete(Long diagnosticId){
        return diagnosticDao.delete(diagnosticId);
    }
}
