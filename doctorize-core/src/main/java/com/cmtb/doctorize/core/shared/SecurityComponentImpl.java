/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.core.shared;

import com.cmtb.doctorize.domain.user.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import io.jsonwebtoken.*;
/**
 *
 * @author Cristopher
 */
@Component(value = "SecurityComponent")
public class SecurityComponentImpl implements SecurityComponent {
    
    @Override
    public String createToken(User user){
        
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("Doctorize");
        
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        
        JwtBuilder builder = Jwts.builder()
        .setSubject(user.getId().toString())
        .signWith(signatureAlgorithm, signingKey);
        
        return builder.compact();
    }
    
//    public CurrentInfo ensureAuthenticated(String jwt){
//        
//        try{
//            Claims claims = Jwts.parser()         
//            .setSigningKey(DatatypeConverter.parseBase64Binary("MyKinup"))
//            .parseClaimsJws(jwt).getBody();
//
//            String[] ids = claims.getSubject().split("-");
//
//            CurrentInfo currentInfo = new CurrentInfo();
//            Employee employee = new Employee();
//            employee.setId(Long.parseLong(ids[0]));
//
//            currentInfo.setCurrentEmployee(employee);
//
//            Company company = new Company();
//            company.setId(Long.parseLong(ids[1]));
//
//            currentInfo.setCurrentCompany(company);
//
//            return currentInfo;
//        } catch(Exception e){
//            throw new RestrictedAccessException();
//        }
//        
//    }
}
