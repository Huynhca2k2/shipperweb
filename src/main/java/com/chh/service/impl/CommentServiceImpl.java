/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chh.service.impl;

import com.chh.pojos.Comment;
import com.chh.pojos.Shipper;
import com.chh.pojos.User;
import com.chh.repository.CommentRepository;
import com.chh.repository.ShipperRepository;
import com.chh.repository.UserRepository;

import com.chh.service.CommentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author huynh
 */
@Service
public class CommentServiceImpl implements CommentService{
    
    @Autowired
    public CommentRepository commentRepository;
    
    @Autowired
    public UserRepository userRepository;
    
    @Autowired
    public ShipperRepository shipperRepository;

    @Override
    public List<Comment> findAll() {
        return this.commentRepository.findAll();
    }

    @Override
    public List<Comment> findById(int id) {
        return this.commentRepository.findById(id);
    }

    @Override
    public boolean add(Comment comment, int idUser, int idShipper) {
        User user = (User) userRepository.findById(idUser).get(0);
        Shipper ship = (Shipper) shipperRepository.findById(idShipper).get(0);
        
        comment.setUser(user);
        comment.setShipper(ship);
        return this.commentRepository.add(comment);
    }

    @Override
    public void delete(int id) {
        this.commentRepository.delete(id);
    }
    
    @Override
    public void updateComment(String content, int idComm){
        this.commentRepository.updateComment(content, idComm);
    }
}
