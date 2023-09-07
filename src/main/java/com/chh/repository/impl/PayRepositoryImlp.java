/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chh.repository.impl;

import com.chh.pojos.Pay;
import com.chh.repository.PayRepository;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.HibernateException;

import org.hibernate.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author huynh
 */
@Repository
@Transactional
public class PayRepositoryImlp implements PayRepository{
    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    
    @Override
    public List<Pay> findAll() {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Query q = session.createQuery("FROM Pay");
        List<Pay> pays = q.getResultList();
        return pays;
    }
    
    
    @Override
    public void delete(int id) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        String hql = "DELETE FROM Pay WHERE payId=:id";
        Query query = session.createQuery(hql);
        query.setParameter("id", id);
        query.executeUpdate();
    }


    @Override
    public boolean add(Pay pay) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try{
            session.save(pay);
            return true;
        }catch (HibernateException ex){
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public List<Pay> findById(int id) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Query q = session.createQuery("FROM Pay WHERE payId=:id");
        q.setParameter("id", id);
        List<Pay> pays = q.getResultList();
        return pays;
    }
    
    @Override
    public void setPayStatus(Pay pay, boolean isStatus) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Query q = session.createQuery("UPDATE Pay S SET S.status=:isStatus WHERE S.payId=:idPay");
        q.setParameter("isStatus", isStatus);
        q.setParameter("idPay", pay.getPayId());
        
        q.executeUpdate();
        
    }

}
