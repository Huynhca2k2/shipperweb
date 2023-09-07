/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chh.controllers;

import com.chh.pojos.Account;
import com.chh.pojos.User;
import com.chh.service.AccountService;
import com.chh.service.JwtService;
import com.chh.service.UserService;
import java.security.Principal;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
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
public class ApiAccountController {
    @Autowired
    private AccountService accountService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private JwtService jwtService;
    
    /*GET ALL ACCOUNT*/
    @RequestMapping(value = "/accounts", method = RequestMethod.GET)
    public ResponseEntity<List<Account>> getAllAccount() {
        return new ResponseEntity<>(
              (List<Account>)accountService.findAll(),
              HttpStatus.OK);
    }

    /*INFO CURRENT ACCOUNT*/
    @RequestMapping(value = "/accounts/my", method = RequestMethod.GET)
        public ResponseEntity<Object> currentAccountName(Principal principal) {
            List<Account> accs = accountService.findByUsername(principal.getName());
            return new ResponseEntity<>(accs.get(0), HttpStatus.OK);
      }

    /*GET ACCOUNT BY ID*/
    @RequestMapping(value = "/accounts/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getAccountById(@PathVariable int id) {
        List<Account> accs =accountService.findById(id);
        if (!accs.isEmpty()) {
          return new ResponseEntity<>(accs.get(0), HttpStatus.OK);
        }
        return new ResponseEntity<>("Not Found User", HttpStatus.NO_CONTENT);
    }

    /*REGISTER NEW ACCOUNT*/
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<String> createAccount(@RequestBody Account acc) {
        User user = new User();
        List<Account> accs = accountService.findByUsername(acc.getUsername());
        if(!accs.isEmpty()){
            return new ResponseEntity<>("Username already exists !", HttpStatus.BAD_REQUEST);
        }else{
            if (this.accountService.add(acc) && this.userService.add(user, acc.getUsername()))
              return new ResponseEntity<>("Created!", HttpStatus.CREATED);
            else 
              return new ResponseEntity<>("User Existed!", HttpStatus.BAD_REQUEST);
            
        }
    }

    /*DELETE ACCOUNT*/
    @RequestMapping(value = "/accounts/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteAccountById(@PathVariable int id) {
        accountService.delete(id);
        return new ResponseEntity<>("Deleted!", HttpStatus.OK);
    }
    
    /*PUT ACCOUNT*/
    @RequestMapping(value = "/accounts/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> putAccountById(@PathVariable int id, @RequestBody Account acc) {
        accountService.updateAccount(acc.getUsername(),
                                     acc.getPassword(),
                                     acc.getFullName(),
                                     acc.getEmail(),
                                     acc.getPhone(),
                                     acc.getAddress(), id);
        return new ResponseEntity<>("Updated!", HttpStatus.OK);
    }
    
    /*LOGIN*/
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<String> login(HttpServletRequest request, @RequestBody Account acc) {
        String result = "";
        HttpStatus httpStatus = null;
        try{
            if(accountService.checkLogin(acc)) {
                result = jwtService.generateTokenLogin(acc.getUsername());
                httpStatus = HttpStatus.OK;
            }else {
                result = "Wrong username and password";
                httpStatus = HttpStatus.BAD_REQUEST;
            }
        }catch (Exception ex) {
            result = "Server Error";
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(result, httpStatus);
    }
  
}
