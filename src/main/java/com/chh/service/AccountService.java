/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.chh.service;

import com.chh.pojos.Account;
import com.chh.pojos.User;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;


/**
 *
 * @author huynh
 */
public interface AccountService extends UserDetailsService{
    List<Account> findAll();
    List<Account> findById(int id);
    List<Account> findByUsername(String username);
    boolean add(Account account);
    void delete(int id);
    void setRoleAccount(Account account, String roleAccount);
    boolean checkLogin(Account account);
    void setMoney(Account account, BigDecimal money);
    void updateAccount(String username, String password, String fullName, String email, String phone, String address, int idAcc);

}
