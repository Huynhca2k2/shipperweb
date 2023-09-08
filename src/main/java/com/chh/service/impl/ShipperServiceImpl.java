/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chh.service.impl;

import com.chh.pojos.Account;
import com.chh.pojos.Shipper;
import com.chh.repository.AccountRepository;
import com.chh.repository.ShipperRepository;
import com.chh.service.ShipperService;
import com.cloudinary.Cloudinary;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author huynh
 */
@Service
public class ShipperServiceImpl implements ShipperService{
    
    @Autowired
    public ShipperRepository shipperRepository;
    
    @Autowired
    public AccountRepository accountRepository;
    
    @Autowired
    private Cloudinary Cloudinary;
    
    @Override
    public boolean add(Shipper shipper) {
        return this.shipperRepository.add(shipper);
    }
    
    @Override
    public List<Shipper> findAll() {
        return this.shipperRepository.findAll();
    }
    
    @Override
    public List<Shipper> findById(int id) {
        return this.shipperRepository.findById(id);
    }
    
    @Override
    public void setProductId(Shipper ship, int idProd) {
        this.shipperRepository.setProductId(ship, idProd);
    }
    
    @Override
    public void setStatus(Shipper ship, boolean isSeleted) {
        this.shipperRepository.setStatus(ship, isSeleted);
    }
    
    @Override
    public void setIsShipper(Shipper ship, boolean isShip) {
        this.shipperRepository.setIsShipper(ship, isShip);
    }
    
    @Override
    public void setAccount(Shipper ship, Account acc){
        this.shipperRepository.setAccount(ship, acc);
    }

    @Override
    public void setDealShipper(Shipper ship, BigDecimal deal) {
        this.shipperRepository.setDealShipper(ship, deal);
    }

    @Override
    public void setIdAccountTmp(Shipper ship, int accountIdTmp) {
        this.shipperRepository.setIdAccountTmp(ship, accountIdTmp);
    }
    
    @Override
    public void updateShipper(String avatar,String cmnd, String currentPos,int idShip){
        this.shipperRepository.updateShipper(avatar, cmnd, currentPos, idShip);
    }
}
