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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author huynh
 */
@Controller
public class AccountController {
    @Autowired
    private AccountService UserDetailsService;
    
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    
    @GetMapping("/register")
    public String registerView(Model model){
        model.addAttribute("account",new Account());
        return "register";
    }
    
    @PostMapping("/register")
    public String register(Model model, @ModelAttribute(value = "account") Account account){
        String errMsg = "";
        //check loi tai koan da ton tai va nhap lai mat khau chinh xac
        if(this.UserDetailsService.findByUsername(account.getUsername()) == null){
            if(account.getPassword().equals(account.getConfirmPassword())
            && this.UserDetailsService.add(account) == true)
                return "redirect:/login";
            else
                errMsg = "mat khau nhap lai khong dung !";
        }else{
            errMsg = "ten tai khoan da co nguoi dang ky!";
        }
        model.addAttribute("errMsg",errMsg);
        return "register";
    }
}
