/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chh.controllers;

import com.chh.pojos.Auction;
import com.chh.pojos.Pay;
import com.chh.pojos.Shipper;
import com.chh.service.AuctionService;
import com.chh.service.PayService;
import com.chh.service.ShipperService;
import static java.lang.constant.ConstantDescs.NULL;
import java.math.BigDecimal;
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
public class ApiAuctionController {
    @Autowired
    private AuctionService auctionService;
    
    @Autowired
    private ShipperService shipperService;
    
    @Autowired
    private PayService payService;
    
    /*GET ALL AUCTION*/
    @RequestMapping(value = "/auctions", method = RequestMethod.GET)
    public ResponseEntity<List<Auction>> getAllAuction() {
        return new ResponseEntity<>(
              (List<Auction>)auctionService.findAll(),
              HttpStatus.OK);
    }

    /*DELETE AUCTION*/
    @RequestMapping(value = "/auctions/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteAuctionById(@PathVariable int id) {
        auctionService.delete(id);
        return new ResponseEntity<>("Deleted!", HttpStatus.OK);
    }
    
    /*GET AUCTION BY ID*/
    @RequestMapping(value = "/auctions/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getAuctionById(@PathVariable int id) {
        List<Auction> aucs = auctionService.findById(id);
        if (!aucs.isEmpty()) {
          return new ResponseEntity<>(aucs.get(0), HttpStatus.OK);
        }
        return new ResponseEntity<>("Not Found Auction", HttpStatus.NO_CONTENT);
    }
    
    /*SELETED SHIPPER*/
    @RequestMapping(value = "/auctions/shippers/{id}", method = RequestMethod.POST)
    public ResponseEntity<String> seletedShipper(@PathVariable int id) {
        
        Shipper ship = (Shipper) shipperService.findById(id).get(0);
        int idAuc = ship.getAuction().getAuctionId();
        List<Auction> aucs = this.auctionService.findById(idAuc);
        Auction auction = ship.getAuction();
        
        if(!aucs.get(0).getShippers().isEmpty()){
            for(int i = 0; i < auction.getShippers().size(); i++){
                if(auction.getShippers().get(i).getShipperId() != id){
                    this.shipperService.setAuctionId(auction.getShippers().get(i), 0);
                }else{
                    //khi shipper duoc chon thi gan status = true, gan dealAuction = dealShipper
                    //roi gan dealShipper = 0
                    this.shipperService.setStatus(auction.getShippers().get(i), true);
                    this.auctionService.setDealAuction(auction, ship.getDealShipper());
                    this.shipperService.setDealShipper(ship, BigDecimal.ZERO);
                    
                }
            }
            //tao pay sau khi chon duoc shipper
            Pay pay = new Pay();
            this.payService.add(pay, auction);
            return new ResponseEntity<>("Seleted Shipper Suscess!", HttpStatus.CREATED);
        }else
            return new ResponseEntity<>("Seleted Error!", HttpStatus.BAD_REQUEST);
        
    }
    
  
}
