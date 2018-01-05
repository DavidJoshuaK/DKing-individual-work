/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.service;

import com.sg.flooring.dao.FlooringDaoPersistenceException;
import com.sg.flooring.dto.Order;
import com.sg.flooring.dto.Product;
import com.sg.flooring.dto.Tax;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface FlooringServiceLayer {

   Order calculateNewOrderDataInput(Order order) throws FlooringValidationException;
   
   void addOrder(Order order);

    List<Order> getAllOrders();

    Order getOrder(Integer orderNumber);

    List<Order> getOrdersByDate(LocalDate date) throws FlooringValidationException;

    Order removeOrder(Integer orderNumber);

    void loadFiles() throws FlooringDaoPersistenceException;

    void writeOrders() throws FlooringDaoPersistenceException;
    
    Order setOrderId(Order order);
    
    boolean checkMode() throws FlooringDaoPersistenceException;

}
