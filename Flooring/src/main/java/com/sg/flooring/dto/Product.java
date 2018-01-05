/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.dto;

import java.math.BigDecimal;

/**
 *
 * @author apprentice
 */
public class Product {

    private String productName;
    private BigDecimal materialCost;
    private BigDecimal laborCost;


 
    
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName){
        this.productName = productName;
    }

    public BigDecimal getMaterialCost() {
        return materialCost;
    }

    public void setMaterialCost(BigDecimal materialCost) {
        this.materialCost = materialCost;
    }

    public BigDecimal getLaborCost() {
        return laborCost;
    }

    public void setLaborCost(BigDecimal laborCost) {
        this.laborCost = laborCost;
    }

}
