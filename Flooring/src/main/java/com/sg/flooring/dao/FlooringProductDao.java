/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.dao;

import com.sg.flooring.dto.Order;
import com.sg.flooring.dto.Product;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface FlooringProductDao {

    Product addProduct(String productName, Product product);

    List<Product> getAllProducts();

    Product getProduct(String productName);

    Product removeProducts(String productName);
    
    public void openProducts() throws FlooringDaoPersistenceException;
}
