/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.chh.service;

import com.chh.pojos.Product;
import java.util.List;
/**
 *
 * @author huynh
 */
public interface ProductService{
    List<Product> findAll();
    List<Product> findById(int id);
    boolean add(Product product);
    void delete(int id);
    void setAuction(Product prod, int idAuc);
    void setUserId(Product prod, int idUser);
    void updateProduct(String nameproduct, String image, int idProd);
    //Product findByIdUser(int idUser);
}
