/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chh.service.impl;

import com.chh.pojos.Account;
import com.chh.pojos.User;
import com.chh.repository.AccountRepository;

import com.chh.repository.UserRepository;
import com.chh.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author huynh
 */
@Service
public class UserServiceImpl implements UserService{
    
    @Autowired
    public UserRepository userRepository;
    
    @Autowired
    public AccountRepository accountRepository;
    
    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public boolean add(User user, String username) {
        Account acc = this.accountRepository.findByUsername(username).get(0);
        if(acc == null)
            throw new UsernameNotFoundException("khong ton tai!!!");
        user.setAccount(acc);
        return this.userRepository.add(user);
    }
    
    @Override
    public void delete(int id) {
        this.userRepository.delete(id);
    }
    
//    @Override
//    public User findByIdAccount(int idAccount) {
//        return this.userRepository.findByIdAccount(idAccount);
//    }
    
    @Override
    public List<User> findById(int id) {
        return this.userRepository.findById(id);
    }
}
