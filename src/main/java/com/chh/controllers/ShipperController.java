/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chh.controllers;

import com.chh.pojos.Shipper;
import com.chh.service.ShipperService;
import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
public class ShipperController {
    @Autowired
    private Cloudinary Cloudinary;
    
    @Autowired
    private ShipperService UserDetailsService;
    
//    @GetMapping("/admin/shippers")
//    public String list(Model model){
//        model.addAttribute("shipper", new Shipper());
//        return "shipper";
//    }
//    
//    @PostMapping("/admin/shippers")
//    public String add(Model model,@ModelAttribute(value = "shipper") Shipper shipper){
//        try {
//            Map r = this.Cloudinary.uploader().upload(shipper.getFile().getBytes(),
//                    ObjectUtils.asMap("resource_type", "auto"));
//            String imgUrl = (String) r.get("secure_url");
//            model.addAttribute("imgUrl", imgUrl);
//            //upload xong cho ve trang chu  
//            //return "redirect:/";
//            
//        } catch (IOException ex) {
//            System.err.println("==== add shipper ====" + ex.getMessage());
//        }
//        //upload that bai cho dung tai trang co giao dien shipper
//        return "shipper";
//    }
    
    @GetMapping("/shippers")
    public String registerShipperView(Model model){
        model.addAttribute("shipper",new Shipper());
        return "shipper";
    }
    
//    @PostMapping("/registers")
//    public String registerShipper( @ModelAttribute(value = "shipper") Shipper shipper){
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        
//        if(this.UserDetailsService.add(shipper, auth.getName()) == true)
//            return "redirect:/";
//        return "shipper";
//    }
}
