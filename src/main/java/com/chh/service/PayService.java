/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.chh.service;
import com.chh.pojos.Auction;
import com.chh.pojos.Pay;
import java.util.List;
/**
 *
 * @author huynh
 */
public interface PayService{
    List<Pay> findAll();
    List<Pay> findById(int id);
    boolean add(Pay pay, Auction auc);
    void delete(int id);
    void setPayStatus(Pay pay, boolean isStatus);
    //Pay findByIdAuction(int idAuction);
}
