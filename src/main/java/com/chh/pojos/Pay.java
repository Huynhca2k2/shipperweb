/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chh.pojos;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author huynh
 */
@Entity
@Table(name = "pay")
public class Pay implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pay_id")
    private int payId;
    @Column(name = "date_pay")
    private String datePay;
    private boolean status;
    
    @OneToOne
    @JoinColumn(name = "auction_id",  referencedColumnName = "auction_id")
    private Auction auction;

    /**
     * @return the payId
     */
    public int getPayId() {
        return payId;
    }

    /**
     * @param payId the payId to set
     */
    public void setPayId(int payId) {
        this.payId = payId;
    }

    /**
     * @return the datePay
     */
    public String getDatePay() {
        return datePay;
    }

    /**
     * @param datePay the datePay to set
     */
    public void setDatePay(String datePay) {
        this.datePay = datePay;
    }

    /**
     * @return the status
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * @return the auction
     */
    public Auction getAuction() {
        return auction;
    }

    /**
     * @param auction the auction to set
     */
    public void setAuction(Auction auction) {
        this.auction = auction;
    }
}
