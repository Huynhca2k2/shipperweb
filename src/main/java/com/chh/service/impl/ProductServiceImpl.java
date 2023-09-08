/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chh.service.impl;

import com.chh.pojos.Product;
import com.chh.repository.ProductRepository;

import com.chh.service.ProductService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 *
 * @author huynh
 */
@Service
public class ProductServiceImpl implements ProductService{
    
    @Autowired
    public ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public List<Product> findById(int id) {
        return this.productRepository.findById(id);
    }
    
    @Override
    public void setAuction(Product prod, int idAuc){
        this.productRepository.setAuction(prod, idAuc);
    }

    @Override
    public boolean add(Product product) {
        return this.productRepository.add(product);
    }

    @Override
    public void delete(int id) {
        this.productRepository.delete(id);
    }
    
    @Override
    public void setUserId(Product prod, int idUser){
        this.productRepository.setUserId(prod, idUser);
    }
    
    @Override
    public void updateProduct(String nameproduct, String image, int idProd){
        this.productRepository.updateProduct(nameproduct, image, idProd);
    }
    
    @Override
    public void setDealProduct(Product product, BigDecimal deal){
        this.productRepository.setDealProduct(product, deal);
    }
}
