/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chh.controllers;

import com.chh.pojos.Account;
import com.chh.service.AccountService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author huynh
 */
@Controller
public class HomeController {
    @Autowired
    private AccountService AccountService;
    
    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("accounts", this.AccountService.findAll());
        
        return "index";
    }
    
    //nhan data tu path
    @RequestMapping("/account/{name}")
    public String hello(Model model, @PathVariable(value = "name") String name){
        model.addAttribute("message", name);
        return "account";
    }
    
    //gui data len sever
    @RequestMapping(path = "/account-post", method = RequestMethod.POST)
    public String show(Model model, @ModelAttribute(value = "account") Account account){
        model.addAttribute("acc", account.getUsername() + account.getPassword());
        return "index";
    }
}
