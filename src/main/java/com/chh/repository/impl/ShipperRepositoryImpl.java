/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chh.repository.impl;

import com.chh.pojos.Account;
import com.chh.pojos.Auction;
import com.chh.pojos.Shipper;
import com.chh.repository.ShipperRepository;
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
public class ShipperRepositoryImpl implements ShipperRepository{
    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    
    @Override
    public boolean add(Shipper shipper) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try{
            session.save(shipper);
            return true;
        }catch (HibernateException ex){
            System.out.println(ex.getMessage());
        }
        return false;
    }
    
    @Override
    public List<Shipper> findAll() {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Query q = session.createQuery("FROM Shipper");
        List<Shipper> shippers = q.getResultList();
        return shippers;
    }
    
    @Override
    public List<Shipper> findById(int id) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Query q = session.createQuery("FROM Shipper WHERE shipperId=:id");
        q.setParameter("id", id);
        List<Shipper> ships = q.getResultList();
        return ships;
    }
    
    @Override
    public void setAuctionId(Shipper ship, int idAuc) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Query q = session.createQuery("UPDATE Shipper S SET S.auction.auctionId=:idAuc WHERE S.shipperId=:idShip");
        q.setParameter("idAuc", idAuc);
        q.setParameter("idShip", ship.getShipperId());
        q.executeUpdate();
    }
    
    @Override
    public void setStatus(Shipper ship, boolean isSeleted) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Query q = session.createQuery("UPDATE Shipper S SET S.status=:isSeleted WHERE S.shipperId=:idShip");
        q.setParameter("isSeleted", isSeleted);
        q.setParameter("idShip", ship.getShipperId());
        
        q.executeUpdate();
        
    }
    
    @Override
    public void setDealShipper(Shipper ship, BigDecimal deal) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Query q = session.createQuery("UPDATE Shipper S SET S.dealShipper=:deal WHERE S.shipperId=:idShip");
        q.setParameter("deal", deal);
        q.setParameter("idShip", ship.getShipperId());
        
        q.executeUpdate();
        
    }
    
    @Override
    public void setIsShipper(Shipper ship, boolean isShip) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Query q = session.createQuery("UPDATE Shipper S SET S.isShipper=:isShip WHERE S.shipperId=:idShip");
        q.setParameter("isShip", isShip);
        q.setParameter("idShip", ship.getShipperId());
        
        q.executeUpdate();
        
    }
    
    @Override
    public void setAccount(Shipper ship, Account acc) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Query q = session.createQuery("UPDATE Shipper S SET S.account=:acc WHERE S.shipperId=:idShip");
        q.setParameter("acc", acc);
        q.setParameter("idShip", ship.getShipperId());
        
        q.executeUpdate();
        
    }
    
    @Override
    public void setIdAccountTmp(Shipper ship, int accountIdTmp) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Query q = session.createQuery("UPDATE Shipper S SET S.accountIdTmp=:accountIdTmp WHERE S.shipperId=:idShip");
        q.setParameter("accountIdTmp", accountIdTmp);
        q.setParameter("idShip", ship.getShipperId());
        
        q.executeUpdate();
        
    }
    
    @Override
    public void updateShipper(String avatar,String cmnd, String currentPos,int idShip){
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Query q = session.createQuery("UPDATE Shipper S SET S.avatar=:avatar, S.cmnd=:cmnd, S.currentPos=:currentPos WHERE S.shipperId=:idShip");
        q.setParameter("avatar", avatar);
        q.setParameter("cmnd", cmnd);
        q.setParameter("currentPos", currentPos);
        q.setParameter("idShip", idShip);
        
        q.executeUpdate();
    }
}
