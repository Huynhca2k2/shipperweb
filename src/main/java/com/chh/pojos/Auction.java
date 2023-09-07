/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chh.pojos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author huynh
 */
@Entity
@Table(name = "auction")
public class Auction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auction_id")
    private int auctionId;
    private BigDecimal deal;
    @Column(name ="date_auction")
    private String dateAuction;
    
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "auction")
    private List<Shipper> shippers;
    
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "auction")
    private Product product;
    
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "auction")
    private Pay pay;

    /**
     * @return the auctionId
     */
    public int getAuctionId() {
        return auctionId;
    }

    /**
     * @param auctionId the auctionId to set
     */
    public void setAuctionId(int auctionId) {
        this.auctionId = auctionId;
    }

    /**
     * @return the deal
     */
    public BigDecimal getDeal() {
        return deal;
    }

    /**
     * @param deal the deal to set
     */
    public void setDeal(BigDecimal deal) {
        this.deal = deal;
    }

    /**
     * @return the dateAuction
     */
    public String getDateAuction() {
        return dateAuction;
    }

    /**
     * @param dateAuction the dateAuction to set
     */
    public void setDateAuction(String dateAuction) {
        this.dateAuction = dateAuction;
    }


    /**
     * @return the product
     */
    public Product getProduct() {
        return product;
    }

    /**
     * @param product the product to set
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * @return the pay
     */
    public Pay getPay() {
        return pay;
    }

    /**
     * @param pay the pay to set
     */
    public void setPay(Pay pay) {
        this.pay = pay;
    }

    /**
     * @return the shippers
     */
    public List<Shipper> getShippers() {
        return shippers;
    }

    /**
     * @param shippers the shippers to set
     */
    public void setShippers(List<Shipper> shippers) {
        this.shippers = shippers;
    }

    

}
