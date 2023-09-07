/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chh.controllers;

import com.chh.pojos.Account;
import com.chh.pojos.Auction;
import com.chh.pojos.Product;
import com.chh.service.AccountService;
import com.chh.service.AuctionService;
import com.chh.service.ProductService;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author huynh
 */
@RestController
@RequestMapping("/api")
public class ApiProductController {
    @Autowired
    private ProductService productService;
    
    @Autowired
    private AuctionService auctionService;
    
    @Autowired
    private AccountService accountService;
    
    /*GET ALL PRODUCT*/
    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> getAllProduct() {
        return new ResponseEntity<>(
              (List<Product>)productService.findAll(),
              HttpStatus.OK);
    }

    /*GET PRODUCT BY ID*/
    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getProductById(@PathVariable int id) {
        List<Product> prods = productService.findById(id);
        if (!prods.isEmpty()) {
          return new ResponseEntity<>(prods, HttpStatus.OK);
        }
        return new ResponseEntity<>("Not Found Product", HttpStatus.NO_CONTENT);
    }
    
    /*CREATE NEW PRODUCT*/
    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ResponseEntity<String> createProduct(@RequestBody Product prod, Principal prin) {
        Account acc = (Account) accountService.findByUsername(prin.getName()).get(0);
        //khi tao product thi tao them auction
        Auction auc = new Auction();
        this.auctionService.add(auc);
        
        if (this.productService.add(prod)) {
            this.productService.setAuction(prod, auc.getAuctionId());
            this.productService.setUserId(prod, acc.getUser().getUserId());
            return new ResponseEntity<>("Created!", HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>("Product Existed!", HttpStatus.BAD_REQUEST);
        }
    }

    /*DELETE PRODUCT*/
    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteProductById(@PathVariable int id) {
        productService.delete(id);
        return new ResponseEntity<>("Deleted!", HttpStatus.OK);
    }
    
    /*INFO CURRENT PRODUCT*/
    @RequestMapping(value = "/products/my", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> currentProduct(Principal principal) {
        Account acc = (Account) accountService.findByUsername(principal.getName()).get(0);
        return new ResponseEntity<>((List<Product>) acc.getUser().getProducts(), HttpStatus.OK);
    }
    
    /*PUT PRODUCT*/
    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> putProductById(@PathVariable int id, @RequestBody Product prod) {
        productService.updateProduct(prod.getNameproduct(), prod.getImage(), id);
        return new ResponseEntity<>("Updated!", HttpStatus.OK);
    }
    
}
