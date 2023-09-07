/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chh.service.impl;

import com.chh.pojos.Account;
import com.chh.repository.AccountRepository;
import com.chh.repository.UserRepository;

import com.chh.service.AccountService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
/**
 *
 * @author huynh
 */
@Service("userDetailsService")
public class AccountServiceImpl implements AccountService{
    
    @Autowired
    public AccountRepository accountRepository;
    
    @Autowired
    public UserRepository userRepository;
    
    @Autowired
    private BCryptPasswordEncoder PasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account acc = this.accountRepository.findByUsername(username).get(0);
        if(acc == null)
            throw new UsernameNotFoundException("khong ton tai!!!");
        
        Set<GrantedAuthority> auth = new HashSet<>();
        auth.add(new SimpleGrantedAuthority(acc.getRoleAccount()));
        return new User(acc.getUsername(),
                        acc.getPassword(),
                        auth);
    }

    @Override
    public List<Account> findAll() {
        return this.accountRepository.findAll();
    }
    
    @Override
    public List<Account> findById(int id) {
        return this.accountRepository.findById(id);
    }
    
    @Override
    public List<Account> findByUsername(String username) {
        return this.accountRepository.findByUsername(username);
    }

    @Override
    public void setRoleAccount(Account account, String roleAcc) {
        this.accountRepository.setRoleAccount(account, roleAcc);
    }
    
    @Override
    public void setMoney(Account account, BigDecimal money) {
        this.accountRepository.setMoney(account, money);
    }
    
    @Override
    public boolean checkLogin(Account acc){
        return this.accountRepository.checkLogin(acc);
    }
    
    @Override
    public boolean add(Account account){
        //bam mat khau truoc khi dua xuong database
        String pass = account.getPassword();
        account.setPassword(this.PasswordEncoder.encode(pass));
        account.setRoleAccount(account.USER);
        
        return this.accountRepository.add(account);
    }

    @Override
    public void delete(int id) {
        this.accountRepository.delete(id);
    }
    
    @Override
    public void updateAccount(String username, String password, String fullName, String email, String phone, String address, int idAcc){
        String hashPass = this.PasswordEncoder.encode(password);
        this.accountRepository.updateAccount(username, hashPass, fullName, email, phone, address, idAcc);
    }
}
