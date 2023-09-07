/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chh.service.impl;

import com.chh.pojos.Auction;
import com.chh.repository.AuctionRepository;
import com.chh.service.AuctionService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author huynh
 */
@Service
public class AuctionServiceImpl implements AuctionService{
    
    @Autowired
    public AuctionRepository auctionRepository;

    @Override
    public List<Auction> findAll() {
        return this.auctionRepository.findAll();
    }

    @Override
    public List<Auction> findById(int id) {
        return this.auctionRepository.findById(id);
    }

    @Override
    public boolean add(Auction auction) {
        return this.auctionRepository.add(auction);
    }

    @Override
    public void delete(int id) {
        this.auctionRepository.delete(id);
    }
    
    @Override
    public void setDealAuction(Auction auction, BigDecimal deal){
        this.auctionRepository.setDealAuction(auction, deal);
    }
    
}
