/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chh.controllers;

import com.chh.pojos.Account;
import com.chh.pojos.Comment;
import com.chh.pojos.Product;
import com.chh.pojos.Shipper;
import com.chh.pojos.User;
import com.chh.service.AccountService;
import com.chh.service.CommentService;
import com.chh.service.ShipperService;
import com.chh.service.UserService;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author huynh
 */
@RestController
@RequestMapping("/api")
public class ApiUserController {
    @Autowired
    private UserService userService;
    
    @Autowired
    private AccountService accountService;
    
    @Autowired
    private ShipperService shipperService;
    
    @Autowired
    private CommentService commentService;
    
    /*GET ALL USER*/
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUser() {
        return new ResponseEntity<>(
                (List<User>)userService.findAll(),
            HttpStatus.OK);
    }
    
//    /*CREATE NEW USER*/
//    @RequestMapping(value = "/users", method = RequestMethod.POST)
//    public ResponseEntity<String> createUser(@RequestBody User user, Principal principal) {
//        if (userService.add(user, principal.getName())) {
//          return new ResponseEntity<>("Created!", HttpStatus.CREATED);
//        } else {
//          return new ResponseEntity<>("User Existed!", HttpStatus.BAD_REQUEST);
//        }
//    }
    
    /*INFO CURRENT USER*/
    @RequestMapping(value = "/users/my", method = RequestMethod.GET)
    public ResponseEntity<Object> currentUserName(Principal principal) {
        Account acc = (Account) accountService.findByUsername(principal.getName()).get(0);
        if (acc.getUser() != null) {
            return new ResponseEntity<>(acc.getUser(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("not found user!", HttpStatus.BAD_REQUEST);
        }
            
    }
        
    /*USER COMMENT ID SHIPPER*/
    @RequestMapping(value = "/users/comments/{idShipper}", method = RequestMethod.POST)
    public ResponseEntity<String> addCommentShipper(@RequestBody Comment comm, @PathVariable int idShipper, Principal prin) {
        
        List<Account> accs = accountService.findByUsername(prin.getName());
        List<Shipper> ships = shipperService.findById(idShipper);
        if (!ships.isEmpty()) {
            commentService.add(comm, accs.get(0).getUser().getUserId(), idShipper);
            return new ResponseEntity<>("Comment suscess!", HttpStatus.CREATED);
        }else 
            return new ResponseEntity<>("Don't comment!", HttpStatus.BAD_REQUEST);
        
    }
  
}
