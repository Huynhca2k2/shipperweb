/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chh.repository.impl;

import com.chh.pojos.Product;
import com.chh.repository.ProductRepository;
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
public class ProductRepositoryImlp implements ProductRepository{
    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    
    @Override
    public List<Product> findAll() {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Query q = session.createQuery("FROM Product");
        List<Product> prods = q.getResultList();
        return prods;
    }
    
    
    @Override
    public void delete(int id) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        String hql = "DELETE FROM Product WHERE productId=:id";
        Query query = session.createQuery(hql);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public boolean add(Product product) {
    Session session = this.sessionFactory.getObject().getCurrentSession();
        try{
            session.save(product);
            return true;
        }catch (HibernateException ex){
            System.out.println(ex.getMessage());
        }
        return false;}

    @Override
    public List<Product> findById(int id) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Query q = session.createQuery("FROM Product WHERE productId=:id");
        q.setParameter("id", id);
        List<Product> prods = q.getResultList();
        return prods;
    }

    @Override
    public void setAuction(Product prod, int idAuc) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Query q = session.createQuery("UPDATE Product P SET P.auction.auctionId=:idAuc WHERE P.productId=:idProd");
        q.setParameter("idAuc", idAuc);
        q.setParameter("idProd", prod.getProductId());
        
        q.executeUpdate();
        
    }
    
    @Override
    public void setUserId(Product prod, int idUser) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Query q = session.createQuery("UPDATE Product P SET P.user.userId=:idUser WHERE P.productId=:idProd");
        q.setParameter("idUser", idUser);
        q.setParameter("idProd", prod.getProductId());
        
        q.executeUpdate();
        
    }
    
    @Override
    public void updateProduct(String nameproduct, String image, int idProd){
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Query q = session.createQuery("UPDATE Product P SET P.nameproduct=:nameproduct, P.image=:imgage WHERE P.productId=:idProd");
        q.setParameter("nameproduct", nameproduct);
        q.setParameter("image", image);
        q.setParameter("idProd", idProd);
        
        q.executeUpdate();
    }
    
    @Override
    public void setDealProduct(Product product, BigDecimal deal) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Query q = session.createQuery("UPDATE Product S SET S.deal=:deal WHERE S.productId=:idProd");
        q.setParameter("deal", deal);
        q.setParameter("idProd", product.getProductId());
        
        q.executeUpdate();
        
    }
}
