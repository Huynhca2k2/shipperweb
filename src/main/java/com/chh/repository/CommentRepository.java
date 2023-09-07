/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.chh.repository;

import com.chh.pojos.Comment;
import java.util.List;

/**
 *
 * @author huynh
 */
public interface CommentRepository{
    List<Comment> findAll();
    List<Comment> findById(int id);
    boolean add(Comment comment);
    void delete(int id);
    void updateComment(String content, int idComm);
}
