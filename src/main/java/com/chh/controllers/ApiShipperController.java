/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chh.controllers;

import com.chh.pojos.Account;
import com.chh.pojos.Shipper;
import com.chh.service.AccountService;
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
public class ApiShipperController {
    @Autowired
    private ShipperService shipperService;
    
    @Autowired
    private AccountService accountService;
    
    @Autowired
    private UserService userService;
    
    /*GET ALL SHIPPER*/
    @RequestMapping(value = "/shippers", method = RequestMethod.GET)
    public ResponseEntity<List<Shipper>> getAllShipper() {
        return new ResponseEntity<>(
                (List<Shipper>)shipperService.findAll(),
            HttpStatus.OK);
    }
    
    /*GET ACCOUNT BY ID*/
    @RequestMapping(value = "/shippers/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getShipperById(@PathVariable int id) {
        List<Shipper> ships = shipperService.findById(id);
        if (!ships.isEmpty()) {
          return new ResponseEntity<>(ships, HttpStatus.OK);
        }
        return new ResponseEntity<>("Not Found User", HttpStatus.NO_CONTENT);
    }
    
    /*CREATE NEW SHIPPER*/
    @RequestMapping(value = "/shippers", method = RequestMethod.POST)
    public ResponseEntity<String> createShipper(@RequestBody Shipper shipper, Principal prin) {
        List<Account> accs = this.accountService.findByUsername(prin.getName());
        
        this.shipperService.setIdAccountTmp(shipper, accs.get(0).getAccountId());
        if (this.shipperService.add(shipper)){
          return new ResponseEntity<>("Created! Please let admin review.", HttpStatus.CREATED);
        } else {
          return new ResponseEntity<>("Shipper Existed!", HttpStatus.BAD_REQUEST);
        }
    }
    
    /*INFO CURRENT SHIPPER*/
    @RequestMapping(value = "/shippers/my", method = RequestMethod.GET)
        public ResponseEntity<Object> currentShipperName(Principal principal) {
            Account acc = (Account) this.accountService.findByUsername(principal.getName()).get(0);
            if (acc.getShipper() != null) {
                return new ResponseEntity<>(acc.getShipper(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("User don't register shipper!", HttpStatus.BAD_REQUEST);
            }
            
      }
        
    /*SHIPPER AUCTION*/
    @RequestMapping(value = "/shippers/products/{id}", method = RequestMethod.POST)
    public ResponseEntity<String> addListShipper(@PathVariable int id, Principal prin) {
        Account acc = this.accountService.findByUsername(prin.getName()).get(0);
        Shipper ship = acc.getShipper();
        //khi shipper chua dau gia thi
        if (ship.isStatus() == false && ship.getProduct().getProductId() == 0) {
            this.shipperService.setProductId(ship, id);
            return new ResponseEntity<>("add suscess!", HttpStatus.CREATED);
        }else 
            return new ResponseEntity<>("Please wait for the auction to finish!", HttpStatus.BAD_REQUEST);
        
    }
    
    /*SHIPPER XET DUYET*/
    @RequestMapping(value = "/shippers/isshipper/{id}", method = RequestMethod.POST)
    public ResponseEntity<String> isShipper(@PathVariable int id) {
        
        List<Shipper> ships = this.shipperService.findById(id);
        List<Account> accs = this.accountService.findById(ships.get(0).getAccountIdTmp());
        
        if (!accs.isEmpty() && !ships.isEmpty()) {
            
            this.shipperService.setIsShipper(ships.get(0), true);
            this.shipperService.setAccount(ships.get(0), accs.get(0));
            this.accountService.setRoleAccount(accs.get(0), Account.SHIPPER);
            
            return new ResponseEntity<>("add suscess!", HttpStatus.CREATED);
        }else 
            return new ResponseEntity<>("add error!", HttpStatus.BAD_REQUEST);
        
    }
    
    /*PUT SHIPPER*/
    @RequestMapping(value = "/shippers/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> putProductById(@PathVariable int id, @RequestBody Shipper ship) {
        shipperService.updateShipper(ship.getAvatar(),
                                     ship.getCmnd(),
                                     ship.getCurrentPos(), id);
        return new ResponseEntity<>("Updated!", HttpStatus.OK);
    }
    
    
}
