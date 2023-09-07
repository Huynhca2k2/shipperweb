/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chh.repository.impl;

import com.chh.pojos.Auction;
import com.chh.repository.AuctionRepository;
import java.math.BigDecimal;
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
public class AuctionRepositoryImlp implements AuctionRepository{
    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    
    @Override
    public List<Auction> findAll() {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Query q = session.createQuery("FROM Auction");
        List<Auction> aucs = q.getResultList();
        return aucs;
    }
    
    
    @Override
    public void delete(int id) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        String hql = "DELETE FROM Auction WHERE auctionId=:id";
        Query query = session.createQuery(hql);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public boolean add(Auction auction) {
    Session session = this.sessionFactory.getObject().getCurrentSession();
        try{
            session.save(auction);
            return true;
        }catch (HibernateException ex){
            System.out.println(ex.getMessage());
        }
        return false;}

    @Override
    public List<Auction> findById(int id) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Query q = session.createQuery("FROM Auction WHERE auctionId=:id");
        q.setParameter("id", id);
        List<Auction> aucs = q.getResultList();
        return aucs;
    }
    
    @Override
    public void setDealAuction(Auction auction, BigDecimal deal) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Query q = session.createQuery("UPDATE Auction S SET S.deal=:deal WHERE S.auctionId=:idAuc");
        q.setParameter("deal", deal);
        q.setParameter("idAuc", auction.getAuctionId());
        
        q.executeUpdate();
        
    }
}
