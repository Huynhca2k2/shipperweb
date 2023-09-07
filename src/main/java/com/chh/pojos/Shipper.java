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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author huynh
 */
@Entity
@Table(name = "shipper")
public class Shipper implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shipper_id")
    private int shipperId;
    private String avatar;
    private String cmnd;
    private boolean status;
    @Column(name = "current_pos")
    private String currentPos;
    @Column(name = "is_shipper")
    private boolean isShipper;
    @Column(name = "deal_shipper")
    private BigDecimal dealShipper;
    @Column(name = "account_id_tmp")
    private int accountIdTmp;
    
    @OneToOne
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    private Account account;
    
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "auction_id", referencedColumnName = "auction_id")
    private Auction auction;
    
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shipper")
    private List<Comment> comments;
    
    @JsonIgnore
    @Transient
    private MultipartFile fileAvatar;
    @JsonIgnore
    @Transient
    private MultipartFile fileCmnd;
    
    /**
     * @return the shipperId
     */
    public int getShipperId() {
        return shipperId;
    }

    /**
     * @param shipperId the shipperId to set
     */
    public void setShipperId(int shipperId) {
        this.shipperId = shipperId;
    }

    /**
     * @return the avatar
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * @param avatar the avatar to set
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * @return the cmnd
     */
    public String getCmnd() {
        return cmnd;
    }

    /**
     * @param cmnd the cmnd to set
     */
    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
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
     * @return the account
     */
    public Account getAccount() {
        return account;
    }

    /**
     * @param account the account to set
     */
    public void setAccount(Account account) {
        this.account = account;
    }


    /**
     * @return the fileAvatar
     */
    public MultipartFile getFileAvatar() {
        return fileAvatar;
    }

    /**
     * @param fileAvatar the fileAvatar to set
     */
    public void setFileAvatar(MultipartFile fileAvatar) {
        this.fileAvatar = fileAvatar;
    }

    /**
     * @return the fileCmnd
     */
    public MultipartFile getFileCmnd() {
        return fileCmnd;
    }

    /**
     * @param fileCmnd the fileCmnd to set
     */
    public void setFileCmnd(MultipartFile fileCmnd) {
        this.fileCmnd = fileCmnd;
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

    /**
     * @return the dealShipper
     */
    public BigDecimal getDealShipper() {
        return dealShipper;
    }

    /**
     * @param dealShipper the dealShipper to set
     */
    public void setDealShipper(BigDecimal dealShipper) {
        this.dealShipper = dealShipper;
    }

    /**
     * @return the comments
     */
    public List<Comment> getComments() {
        return comments;
    }

    /**
     * @param comments the comments to set
     */
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    /**
     * @return the isShipper
     */
    public boolean isIsShipper() {
        return isShipper;
    }

    /**
     * @param isShipper the isShipper to set
     */
    public void setIsShipper(boolean isShipper) {
        this.isShipper = isShipper;
    }

    /**
     * @return the currentPos
     */
    public String getCurrentPos() {
        return currentPos;
    }

    /**
     * @param currentPos the currentPos to set
     */
    public void setCurrentPos(String currentPos) {
        this.currentPos = currentPos;
    }

    /**
     * @return the accountIdTmp
     */
    public int getAccountIdTmp() {
        return accountIdTmp;
    }

    /**
     * @param accountIdTmp the shipperIdTmp to set
     */
    public void setAccountIdTmp(int accountIdTmp) {
        this.accountIdTmp = accountIdTmp;
    }
    
}
