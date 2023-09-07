/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.chh.repository;

import com.chh.pojos.Account;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author huynh
 */
public interface AccountRepository{
    List<Account> findAll();
    List<Account> findById(int id);
    List<Account> findByUsername(String username);
    boolean add(Account account);
    void delete(int id);
    void setRoleAccount(Account account, String roleAcc);
    boolean checkLogin(Account account);
    void setMoney(Account account, BigDecimal money);
    void updateAccount(String username, String password, String fullName, String email, String phone, String address, int idAcc);
}
