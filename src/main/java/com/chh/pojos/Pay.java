/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chh.pojos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

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
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date datePay;
    private boolean status;
    
    @OneToOne
    @JoinColumn(name = "product_id",  referencedColumnName = "product_id")
    private Product product;

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
    public Date getDatePay() {
        return datePay;
    }

    /**
     * @param datePay the datePay to set
     */
    public void setDatePay(Date datePay) {
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
}
