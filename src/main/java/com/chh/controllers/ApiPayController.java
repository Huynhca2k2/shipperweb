/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chh.controllers;

import com.chh.pojos.Account;
import com.chh.pojos.Pay;
import com.chh.service.AccountService;
import com.chh.service.PayService;
import com.chh.service.ShipperService;
import java.math.BigDecimal;
import java.security.Principal;
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
public class ApiPayController {
    @Autowired
    private PayService payService;
    
    @Autowired
    private AccountService accountService;
    
    @Autowired
    private ShipperService shipperService;
    
    /*GET ALL PAY*/
    @RequestMapping(value = "/pays", method = RequestMethod.GET)
    public ResponseEntity<List<Pay>> getAllPay() {
        return new ResponseEntity<>(
              (List<Pay>)payService.findAll(),
              HttpStatus.OK);
    }

    /*GET PAY BY ID*/
    @RequestMapping(value = "/pays/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getPayById(@PathVariable int id) {
        List<Pay> pays = payService.findById(id);
        if (!pays.isEmpty()) {
            return new ResponseEntity<>(pays.get(0), HttpStatus.OK);
        }else
            return new ResponseEntity<>("Not Found Auction", HttpStatus.NO_CONTENT);
    }

//    /*CREATE NEW PAY*/
//    @RequestMapping(value = "/pays", method = RequestMethod.POST)
//    public ResponseEntity<String> createPay(@RequestBody Pay pay) {
//        if (payService.add(pay)) {
//          return new ResponseEntity<>("Created!", HttpStatus.CREATED);
//        } else {
//          return new ResponseEntity<>("Auction Existed!", HttpStatus.BAD_REQUEST);
//        }
//    }

    /*DELETE PAY*/
    @RequestMapping(value = "/pays/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deletePayById(@PathVariable int id) {
        payService.delete(id);
        return new ResponseEntity<>("Deleted!", HttpStatus.OK);
    }
    
    /*PAY MONEY*/
    @RequestMapping(value = "/pays/{id}", method = RequestMethod.POST)
    public ResponseEntity<String> payMoney(@PathVariable int id, Principal prin) {
        List<Account> accs = this.accountService.findByUsername(prin.getName());
        List<Pay> pays = this.payService.findById(id);
        
        if (!pays.isEmpty() && pays.get(0).isStatus() == false) {
            Account accOfShip = pays.get(0).getAuction().getShippers().get(0).getAccount();
            Account accOfUser = accs.get(0);
            BigDecimal payMentValue = pays.get(0).getAuction().getDeal();
            
            //tru money cua user roi cong cho shipper
            this.accountService.setMoney(accOfShip, accOfShip.getMoney().add(payMentValue));
            this.accountService.setMoney(accOfUser, accOfUser.getMoney().subtract(payMentValue));
            
            //set lai status pay da thanh toan
            this.payService.setPayStatus(pays.get(0), true);
            
            //reset shipper
            this.shipperService.setAuctionId(accOfShip.getShipper(), 0);
            this.shipperService.setStatus(accOfShip.getShipper(), false);
            
            return new ResponseEntity<>("Pay suscess!", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Payment Completed!", HttpStatus.BAD_REQUEST);
        }
    }
    
}
