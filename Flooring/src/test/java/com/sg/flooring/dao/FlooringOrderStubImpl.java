/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.dao;

import com.sg.flooring.dao.*;
import com.sg.flooring.dto.Order;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class FlooringOrderStubImpl implements FlooringOrderDao {

    Order oneOrder;
    List<Order> orderList = new ArrayList<>();

    public void FlooringAuditDaoStubImpl() {
        oneOrder = new Order();
        oneOrder.setOrderNumber(1);
        oneOrder.setCustomerName("John");
        oneOrder.setState("OH");
        oneOrder.setTaxRate(new BigDecimal("6.75"));
        oneOrder.setProductType("Wood");
        oneOrder.setArea(new BigDecimal("250"));
        oneOrder.setCostPerSquareFoot(new BigDecimal("100"));
        oneOrder.setMaterialCostTotal(new BigDecimal("50.00"));
        oneOrder.setLaborCostPerSquareFoot(new BigDecimal("500"));
        oneOrder.setLaborCost(new BigDecimal("15.00"));
        oneOrder.setTax(new BigDecimal("66"));
        oneOrder.setTotal(new BigDecimal("500"));
        //order.setOrderDate(LocalDate("2018-10-10"));
    }

    @Override
    public Order addOrder(Integer orderNumber, Order order) {
        if (orderNumber.equals(oneOrder.getOrderNumber())) {
            return oneOrder;
        } else {
            return null;
        }
    }

    @Override
    public List<Order> getAllOrders() {
        return orderList;
    }

    @Override
    public Order getOrder(Integer orderNumber) {
        if (orderNumber.equals(oneOrder.getOrderNumber())) {
            return oneOrder;
        } else {
            return null;
        }
    }

    @Override
    public Order removeOrder(Integer orderNumber) {
        if (orderNumber.equals(oneOrder.getOrderNumber())) {
            return oneOrder;
        } else {
            return null;
        }
    }

    @Override
    public void openOrders() throws FlooringDaoPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void closeOrder() throws FlooringDaoPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
