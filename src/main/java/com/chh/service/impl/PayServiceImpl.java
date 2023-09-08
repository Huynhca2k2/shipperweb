/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chh.service.impl;

import com.chh.pojos.Pay;
import com.chh.pojos.Product;
import com.chh.repository.PayRepository;

import com.chh.service.PayService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 *
 * @author huynh
 */
@Service
public class PayServiceImpl implements PayService{
    
    @Autowired
    public PayRepository payRepository;

    @Override
    public List<Pay> findAll() {
        return this.payRepository.findAll();
    }

    @Override
    public List<Pay> findById(int id) {
        return this.payRepository.findById(id);
    }


    @Override
    public boolean add(Pay pay, Product prod) {
        pay.setProduct(prod);
        return this.payRepository.add(pay);
    }

    @Override
    public void delete(int id) {
        this.payRepository.delete(id);
    }
    
    @Override
    public void setPayStatus(Pay pay, boolean isStatus){
        this.payRepository.setPayStatus(pay, isStatus);
    }
    
    
}
