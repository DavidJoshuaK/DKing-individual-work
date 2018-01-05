/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.dao;

import com.sg.flooring.dto.Order;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface FlooringOrderDao {

    Order addOrder(Integer orderNumber, Order order);

    List<Order> getAllOrders();

    Order getOrder(Integer orderNumber);

    Order removeOrder(Integer orderNumber);

    void openOrders() throws FlooringDaoPersistenceException;

    void closeOrder() throws FlooringDaoPersistenceException;
    
}
