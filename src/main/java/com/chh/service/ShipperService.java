/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.chh.service;

import com.chh.pojos.Account;
import com.chh.pojos.Shipper;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author huynh
 */
public interface ShipperService {
    boolean add(Shipper shipper);
    List<Shipper> findAll();
    List<Shipper> findById(int id);
    void setProductId(Shipper ship, int idProd);
    void setStatus(Shipper ship, boolean isSeleted);
    void setDealShipper(Shipper ship, BigDecimal deal);
    void setIsShipper(Shipper ship, boolean isShip);
    void setAccount(Shipper ship, Account acc);
    void setIdAccountTmp(Shipper ship, int AccountIdTmp);
    void updateShipper(String avatar,String cmnd, String currentPos,int idShip);
}
