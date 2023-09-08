/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chh.controllers;

import com.chh.pojos.Account;
import com.chh.pojos.Pay;
import com.chh.pojos.Product;
import com.chh.pojos.Shipper;
import com.chh.service.AccountService;
import com.chh.service.PayService;
import com.chh.service.ProductService;
import com.chh.service.ShipperService;
import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    private ShipperService shipperService;
    
    @Autowired
    private AccountService accountService;
    
    @Autowired
    private PayService payService;
    
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
        prod.setDateProduct(new Date());
        if (this.productService.add(prod)) {
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
    
    /*SELETED SHIPPER*/
    @RequestMapping(value = "/products/shippers/{id}", method = RequestMethod.POST)
    public ResponseEntity<String> seletedShipper(@PathVariable int id) {
        
        Shipper ship = (Shipper) shipperService.findById(id).get(0);
        int idProd = ship.getProduct().getProductId();
        List<Product> prods = this.productService.findById(idProd);
        Product product = ship.getProduct();
        
        if(!prods.get(0).getShippers().isEmpty()){
            for(int i = 0; i < product.getShippers().size(); i++){
                if(product.getShippers().get(i).getShipperId() != id){
                    this.shipperService.setProductId(product.getShippers().get(i), 0);
                }else{
                    //khi shipper duoc chon thi gan status = true, gan dealAuction = dealShipper
                    //roi gan dealShipper = 0
                    this.shipperService.setStatus(product.getShippers().get(i), true);
                    this.productService.setDealProduct(product, ship.getDealShipper());
                    this.shipperService.setDealShipper(ship, BigDecimal.ZERO);
                    
                }
            }
            //tao pay sau khi chon duoc shipper
            Pay pay = new Pay();
            pay.setDatePay(new Date());
            this.payService.add(pay, product);
            return new ResponseEntity<>("Seleted Shipper Suscess!", HttpStatus.CREATED);
        }else
            return new ResponseEntity<>("Seleted Error!", HttpStatus.BAD_REQUEST);
        
    }
    
}
