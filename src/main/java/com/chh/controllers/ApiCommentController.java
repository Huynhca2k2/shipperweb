/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chh.controllers;

import com.chh.pojos.Comment;
import com.chh.service.AccountService;
import com.chh.service.CommentService;
import com.chh.service.ShipperService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author huynh
 */
@RestController
@RequestMapping("/api")
public class ApiCommentController {
    @Autowired
    private CommentService commentService;
    
    @Autowired
    private ShipperService shipperService;
    
    /*GET ALL COMMENT*/
    @RequestMapping(value = "/comments", method = RequestMethod.GET)
    public ResponseEntity<List<Comment>> getAllComment() {
        return new ResponseEntity<>(
              (List<Comment>)this.commentService.findAll(),
              HttpStatus.OK);
    }

    /*GET COMMENT BY ID*/
    @RequestMapping(value = "/comments/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getCommentById(@PathVariable int id) {
        List<Comment> comms = this.commentService.findById(id);
        if (!comms.isEmpty()) {
            return new ResponseEntity<>(comms, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Not Found Comment", HttpStatus.NO_CONTENT);
        }
    }
    
    /*DELETE COMMENT*/
    @RequestMapping(value = "/comments/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteCommentById(@PathVariable int id) {
        this.commentService.delete(id);
        return new ResponseEntity<>("Deleted!", HttpStatus.OK);
    }
    
    /*PUT COMMENT*/
    @RequestMapping(value = "/comments/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> putCommentById(@PathVariable int id, @RequestBody Comment comm) {
        commentService.updateComment(comm.getContent(), id);
        return new ResponseEntity<>("Updated!", HttpStatus.OK);
    }
    
  
}
