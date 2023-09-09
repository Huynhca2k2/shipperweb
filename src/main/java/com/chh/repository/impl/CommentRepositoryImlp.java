/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chh.repository.impl;

import com.chh.pojos.Comment;
import com.chh.repository.CommentRepository;
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
public class CommentRepositoryImlp implements CommentRepository{
    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    
    @Override
    public List<Comment> findAll() {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Query q = session.createQuery("FROM Comment");
        List<Comment> comms = q.getResultList();
        return comms;
    }
    
    
    @Override
    public void delete(int id) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        String hql = "DELETE FROM Comment WHERE commentId=:id";
        Query query = session.createQuery(hql);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public boolean add(Comment comment) {
    Session session = this.sessionFactory.getObject().getCurrentSession();
        try{
            session.save(comment);
            return true;
        }catch (HibernateException ex){
            System.out.println(ex.getMessage());
        }
        return false;}

    @Override
    public List<Comment> findById(int id) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Query q = session.createQuery("FROM Comment WHERE commentId=:id");
        q.setParameter("id", id);
        List<Comment> comms = q.getResultList();
        return comms;
    }
    
    @Override
    public void updateComment(String content, int idComm){
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Query q = session.createQuery("UPDATE Comment C SET C.content=:content WHERE C.commentId=:idComm");
        q.setParameter("content", content);
        q.setParameter("idComm", idComm);
        
        q.executeUpdate();
    }

//    @Override
//    public List<Comment> getCommentsByShipperId(int idShip, int page) {
//        Session session = this.sessionFactory.getObject().getCurrentSession();
//        CriteriaBuilder builder = session.getCriteriaBuilder();
//        CriteriaQuery<Comment> query = builder.createQuery(Comment.class);
//        Root root = query.from(Comment.class);
//        
//        query = query.where(builder.equal(root.get("shipper"), idShip));
//        query = query.orderBy(builder.desc(root.get("commentId")));
//        
//        Query q = session.createQuery(query);
//        
//        int max = 5;
//        q.setMaxResults(max);
//        q.setFirstResult((page - 1) * max);
//        
//        return q.getResultList();
//    }
    

}
