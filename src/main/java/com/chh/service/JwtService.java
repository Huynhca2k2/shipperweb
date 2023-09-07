/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.chh.service;
/**
 *
 * @author huynh
 */
public interface JwtService {
    String generateTokenLogin(String username);
    String getUsernameFromToken(String token);
    Boolean validateTokenLogin(String token);
}
