/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.chh.repository;

import com.chh.pojos.User;
import java.util.List;

/**
 *
 * @author huynh
 */
public interface UserRepository {
    List<User> findAll();
    boolean add(User user);
    void delete(int id);
    List<User> findById(int id);
    //User findByIdAccount(int idAccount);
}
