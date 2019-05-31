/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.utilities;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author pc
 */
@Component
public class PasswordEncrypt {
    
    @Value("${doctorize.phrase.password.workload}")
    private int PHRASE_PASSWORD_WORKLOADCIPHER;

    public String hashPassword(String phrase) {
        String salt = BCrypt.gensalt(PHRASE_PASSWORD_WORKLOADCIPHER);
        String hashed_password = BCrypt.hashpw(phrase, salt);

        return (hashed_password);
    }

    public boolean checkPassword(String phrase, String phraseHash) {
        boolean password_verified = false;

        if (null == phraseHash || !phraseHash.startsWith("$2a$")) {
            throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");
        }

        password_verified = BCrypt.checkpw(phrase, phraseHash);

        return (password_verified);
    }
}
