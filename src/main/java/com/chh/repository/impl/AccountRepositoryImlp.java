/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chh.repository.impl;

import com.chh.pojos.Account;
import com.chh.repository.AccountRepository;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.HibernateException;

import org.hibernate.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author huynh
 */
@Repository
@Transactional
public class AccountRepositoryImlp implements AccountRepository{
    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public List<Account> findById(int id) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Query q = session.createQuery("FROM Account WHERE accountId=:id");
        q.setParameter("id", id);
        List<Account> accs = q.getResultList();
        return accs;
    }
    
    @Override
    public List<Account> findByUsername(String username) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Query q = session.createQuery("FROM Account WHERE username=:username");
        q.setParameter("username", username);
        List<Account> accs = q.getResultList();
        return accs;
        
    }
    
    @Override
    public List<Account> findAll() {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Query q = session.createQuery("FROM Account");
        List<Account> accs = q.getResultList();
        return accs;
    }
    
    @Override
    public void updateAccount(String username,
                              String password,
                              String fullName,
                              String email,
                              String phone,
                              String address,
                              int idAcc) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Query q = session.createQuery("UPDATE Account A SET A.username=:username, A.fullName=:fullName, A.password=:password, A.email=:email, A.phone=:phone, A.address=:address WHERE A.accountId=:idAcc");
        q.setParameter("username", username);
        q.setParameter("password", password);
        q.setParameter("fullName", fullName);
        q.setParameter("email", email);
        q.setParameter("phone", phone);
        q.setParameter("address", address);
        q.setParameter("idAcc", idAcc);
        
        q.executeUpdate();
        
    }
    
    @Override
    public void setRoleAccount(Account account, String roleAcc) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Query q = session.createQuery("UPDATE Account A SET A.roleAccount=:roleAcc WHERE A.accountId=:idAcc");
        q.setParameter("roleAcc", roleAcc);
        q.setParameter("idAcc", account.getAccountId());
        
        q.executeUpdate();
        
    }
    
    @Override
    public void setMoney(Account account, BigDecimal money) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Query q = session.createQuery("UPDATE Account A SET A.money=:money WHERE A.accountId=:idAcc");
        q.setParameter("money", money);
        q.setParameter("idAcc", account.getAccountId());
        
        q.executeUpdate();
        
    }

    @Override
    public boolean checkLogin(Account account) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        
        String hql = "SELECT password FROM Account WHERE username=:username";
        Query query = session.createQuery(hql);
        query.setParameter("username", account.getUsername());
        
        //check truong hop duoi database ko co 
        List<String> passList = query.getResultList();
        if(passList.isEmpty())
            return false;
        else{
            //check mat khau ban dau vs mat khau da bam
            if(BCrypt.checkpw(account.getPassword(), passList.get(0)) )
                return true;
            else
                return false;
        }
            
    }
    
    @Override
    public boolean add(Account account){
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try{
            session.save(account);
            return true;
        }catch (HibernateException ex){
            System.out.println(ex.getMessage());
        }
        return false;
        
    }

    @Override
    public void delete(int id) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        String hql = "DELETE FROM Account WHERE accountId=:id";
        Query query = session.createQuery(hql);
        query.setParameter("id", id);
        query.executeUpdate();
    }


}
