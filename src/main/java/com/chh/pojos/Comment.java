/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chh.pojos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
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

/**
 *
 * @author huynh
 */
@Entity
@Table(name = "comment")
public class Comment implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private int commentId;
    private String content;
    @Column(name = "date_comment")
    private String dateComment;
    
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private com.chh.pojos.User user;
    
    @ManyToOne
    @JoinColumn(name = "shipper_id", referencedColumnName = "shipper_id")
    private Shipper shipper;

    /**
     * @return the commentId
     */
    public int getCommentId() {
        return commentId;
    }

    /**
     * @param commentId the commentId to set
     */
    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the dateComment
     */
    public String getDateComment() {
        return dateComment;
    }

    /**
     * @param dateComment the dateComment to set
     */
    public void setDateComment(String dateComment) {
        this.dateComment = dateComment;
    }

    /**
     * @return the shipper
     */
    public Shipper getShipper() {
        return shipper;
    }

    /**
     * @param shipper the shipper to set
     */
    public void setShipper(Shipper shipper) {
        this.shipper = shipper;
    }

    /**
     * @return the user
     */
    public com.chh.pojos.User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(com.chh.pojos.User user) {
        this.user = user;
    }

    
}
