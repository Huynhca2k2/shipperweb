/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chh.service.impl;

import com.chh.service.JwtService;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 *
 * @author huynh
 */
@Service
public class JwtServiceImlp implements JwtService{
    
    @Autowired
    private Environment env;
    
    private static final String USERNAME = "username";
    
    @Override
    public String generateTokenLogin(String username){
        String token = null;
        
        try{
            //create HMAC signer
            JWSSigner signer = new MACSigner(generateShareSecret());
            
            JWTClaimsSet.Builder builder = new JWTClaimsSet.Builder();
            builder.claim(USERNAME, username);
            builder.expirationTime(generateExpirationDate());
            
            JWTClaimsSet claimSet = builder.build();
            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimSet);
            
            //Apply the HMAC protection
            signedJWT.sign(signer);
            
            // Serialize to compact form, produces something like
            // eyJhbGciOiJIUzI1NiJ9.SGVsbG8sIHdvcmxkIQ.onO9Ihudz3WkiauDO2Uhyuz0Y18UASXlSc1eS0NkWyA
            token = signedJWT.serialize();
            
        }catch (JOSEException e){
        }
        return token;
    }
    
    private JWTClaimsSet getClaimsFromToken(String token){
        JWTClaimsSet claims = null;
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            JWSVerifier verifier = new MACVerifier(generateShareSecret());
            if(signedJWT.verify(verifier)){
                claims = signedJWT.getJWTClaimsSet();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return claims;
    }

    private Date generateExpirationDate(){
        int expire_time = Integer.parseInt(env.getProperty("expire_time"));
        return new Date(System.currentTimeMillis() + expire_time);
    }
    
    private Date getExpirationDateFromToken(String token){
        Date expiration = null;
        JWTClaimsSet claims = getClaimsFromToken(token);
        expiration = claims.getExpirationTime();
        return expiration;
    }
    
    @Override
    public String getUsernameFromToken(String token){
        String username = null;
        try {
            JWTClaimsSet claims = getClaimsFromToken(token);
            username = claims.getStringClaim(USERNAME);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return username;
    }
    
    private byte[] generateShareSecret(){
        //genegate 256bit 32byte share secret
        byte[] sharedSecret = new byte[32];
        String secret_key = env.getProperty("secret_key");
        sharedSecret = secret_key.getBytes();
        return sharedSecret;
    }
    
    private Boolean isTokenExpired(String token){
        Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }
    
    @Override
    public Boolean validateTokenLogin(String token){
        if(token == null || token.trim().length() == 0){
            return false;
        }
        String username = getUsernameFromToken(token);
        
        if(username == null || username.isEmpty()){
            return false;
        }
        if(isTokenExpired(token)){
            return false;
        }
        return true;
    }
}
