/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.dao;

import com.sg.flooring.dao.*;
import com.sg.flooring.dto.Product;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class FlooringProductStubImpl implements FlooringProductDao {

    private Product oneProduct;
    private List<Product> productList = new ArrayList<>();

    public FlooringProductStubImpl() {
        Product oneProduct = new Product();
        oneProduct.setLaborCost(new BigDecimal("5.15"));
        oneProduct.setMaterialCost(new BigDecimal("4.75"));
        oneProduct.setProductName("Wood");

        productList.add(oneProduct);
    }

    @Override
    public Product addProduct(String productName, Product product) {
        if (productName.equals(oneProduct.getProductName())) {
            return oneProduct;
        } else {
            return null;
        }
    }

    @Override
    public List<Product> getAllProducts() {
        return productList;
    }

    @Override
    public Product getProduct(String productName) {
        if (productName.equals(oneProduct.getProductName())) {
            return oneProduct;
        } else {
            return null;
        }
    }

    @Override
    public Product removeProducts(String productName) {
        if (productName.equals(oneProduct.getProductName())) {
            return oneProduct;
        } else {
            return null;
        }
    }

    @Override
    public void openProducts() throws FlooringDaoPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
