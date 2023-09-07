/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.chh.service;

import com.chh.pojos.Auction;
import java.math.BigDecimal;
import java.util.List;
/**
 *
 * @author huynh
 */
public interface AuctionService{
    List<Auction> findAll();
    List<Auction> findById(int id);
    boolean add(Auction auction);
    void delete(int id);
    void setDealAuction(Auction auction, BigDecimal deal);
}
