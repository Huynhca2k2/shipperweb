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
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author huynh
 */
@Entity
@Table(name = "product")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;
    private String nameproduct;
    @Column(name = "date_product")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateProduct;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;
    private String image;
    @Column(name = "start_price")
    private BigDecimal startPrice;
    private int weight;
    private int height;
    @Column(name = "address_start")
    private String addressStart;
    @Column(name = "address_end")
    private String addressEnd;
    private String description;
    private BigDecimal deal;
    private boolean status;
    
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "product")
    private List<Shipper> shippers;
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "product")
    private Pay pay;

    /**
     * @return the productId
     */
    public int getProductId() {
        return productId;
    }

    /**
     * @param productId the productId to set
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }

    /**
     * @return the nameproduct
     */
    public String getNameproduct() {
        return nameproduct;
    }

    /**
     * @param nameproduct the nameproduct to set
     */
    public void setNameproduct(String nameproduct) {
        this.nameproduct = nameproduct;
    }

    /**
     * @return the dateProduct
     */
    public Date getDateProduct() {
        return dateProduct;
    }

    /**
     * @param dateProduct the dateProduct to set
     */
    public void setDateProduct(Date dateProduct) {
        this.dateProduct = dateProduct;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
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
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
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
     * @return the startPrice
     */
    public BigDecimal getStartPrice() {
        return startPrice;
    }

    /**
     * @param startPrice the startPrice to set
     */
    public void setStartPrice(BigDecimal startPrice) {
        this.startPrice = startPrice;
    }

    /**
     * @return the weight
     */
    public int getWeight() {
        return weight;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * @return the addressStart
     */
    public String getAddressStart() {
        return addressStart;
    }

    /**
     * @param addressStart the addressStart to set
     */
    public void setAddressStart(String addressStart) {
        this.addressStart = addressStart;
    }

    /**
     * @return the addressEnd
     */
    public String getAddressEnd() {
        return addressEnd;
    }

    /**
     * @param addressEnd the addressEnd to set
     */
    public void setAddressEnd(String addressEnd) {
        this.addressEnd = addressEnd;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
