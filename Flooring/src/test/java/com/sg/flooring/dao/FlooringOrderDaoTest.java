/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.dao;

import com.sg.flooring.dto.Order;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author apprentice
 */
public class FlooringOrderDaoTest {

    private FlooringOrderDao daoOrder = new FlooringOrderDaoFileImpl();

    public FlooringOrderDaoTest() {

    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws Exception {
        List<Order> orderList = daoOrder.getAllOrders();
        for (Order order : orderList) {
            daoOrder.removeOrder(order.getOrderNumber());
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addOrder method, of class FlooringOrderDao.
     */
    @Test
    public void testAddOrder() throws Exception {
        Order order = new Order();
        order.setOrderNumber(1);
        order.setCustomerName("John");
        order.setState("OH");
        order.setTaxRate(new BigDecimal("6.75"));
        order.setProductType("Wood");
        order.setArea(new BigDecimal("250"));
        order.setCostPerSquareFoot(new BigDecimal("100"));
        order.setMaterialCostTotal(new BigDecimal("50.00"));
        order.setLaborCostPerSquareFoot(new BigDecimal("500"));
        order.setLaborCost(new BigDecimal("15.00"));
        order.setTax(new BigDecimal("66"));
        order.setTotal(new BigDecimal("500"));
        //order.setOrderDate(LocalDate("2018-10-10"));

        daoOrder.addOrder(order.getOrderNumber(), order);

        Order fromDao = daoOrder.getOrder(order.getOrderNumber());

        assertEquals(order, fromDao);

    }

    /**
     * Test of getAllOrders method, of class FlooringOrderDao.
     */
    @Test
    public void testGetAllOrders() throws Exception {
        Order order = new Order();
        order.setOrderNumber(1);
        order.setCustomerName("John");
        order.setState("OH");
        order.setTaxRate(new BigDecimal("6.75"));
        order.setProductType("Wood");
        order.setArea(new BigDecimal("250"));
        order.setCostPerSquareFoot(new BigDecimal("100"));
        order.setMaterialCostTotal(new BigDecimal("50.00"));
        order.setLaborCostPerSquareFoot(new BigDecimal("500"));
        order.setLaborCost(new BigDecimal("15.00"));
        order.setTax(new BigDecimal("66"));
        order.setTotal(new BigDecimal("500"));

        daoOrder.addOrder(order.getOrderNumber(), order);

        Order otherOrder = new Order();
        otherOrder.setOrderNumber(2);
        otherOrder.setCustomerName("Matt");
        otherOrder.setState("PA");
        otherOrder.setTaxRate(new BigDecimal("8"));
        otherOrder.setProductType("Laminate");
        otherOrder.setArea(new BigDecimal("250"));
        otherOrder.setCostPerSquareFoot(new BigDecimal("200"));
        otherOrder.setMaterialCostTotal(new BigDecimal("6.00"));
        otherOrder.setLaborCostPerSquareFoot(new BigDecimal("222.33"));
        otherOrder.setLaborCost(new BigDecimal("17.00"));
        otherOrder.setTax(new BigDecimal("22"));
        otherOrder.setTotal(new BigDecimal("243"));

        daoOrder.addOrder(otherOrder.getOrderNumber(), otherOrder);

        assertEquals(2, daoOrder.getAllOrders().size());

    }

    /**
     * Test of getOrder method, of class FlooringOrderDao.
     */
    @Test
    public void testGetOrder() throws Exception {
        Order order = new Order();
        order.setOrderNumber(1);
        order.setCustomerName("John");
        order.setState("OH");
        order.setTaxRate(new BigDecimal("6.75"));
        order.setProductType("Wood");
        order.setArea(new BigDecimal("250"));
        order.setCostPerSquareFoot(new BigDecimal("100"));
        order.setMaterialCostTotal(new BigDecimal("50.00"));
        order.setLaborCostPerSquareFoot(new BigDecimal("500"));
        order.setLaborCost(new BigDecimal("15.00"));
        order.setTax(new BigDecimal("66"));
        order.setTotal(new BigDecimal("500"));

        daoOrder.addOrder(order.getOrderNumber(), order);
        assertEquals(1, daoOrder.getAllOrders().size());

    }

    /**
     * Test of removeOrder method, of class FlooringOrderDao.
     */
    @Test
    public void testRemoveOrder() throws Exception {

        Order order = new Order();
        order.setOrderNumber(1);
        order.setCustomerName("John");
        order.setState("OH");
        order.setTaxRate(new BigDecimal("6.75"));
        order.setProductType("Wood");
        order.setArea(new BigDecimal("250"));
        order.setCostPerSquareFoot(new BigDecimal("100"));
        order.setMaterialCostTotal(new BigDecimal("50.00"));
        order.setLaborCostPerSquareFoot(new BigDecimal("500"));
        order.setLaborCost(new BigDecimal("15.00"));
        order.setTax(new BigDecimal("66"));
        order.setTotal(new BigDecimal("500"));

        daoOrder.addOrder(order.getOrderNumber(), order);

        Order otherOrder = new Order();
        otherOrder.setOrderNumber(2);
        otherOrder.setCustomerName("Matt");
        otherOrder.setState("PA");
        otherOrder.setTaxRate(new BigDecimal("8"));
        otherOrder.setProductType("Laminate");
        otherOrder.setArea(new BigDecimal("250"));
        otherOrder.setCostPerSquareFoot(new BigDecimal("200"));
        otherOrder.setMaterialCostTotal(new BigDecimal("6.00"));
        otherOrder.setLaborCostPerSquareFoot(new BigDecimal("222.33"));
        otherOrder.setLaborCost(new BigDecimal("17.00"));
        otherOrder.setTax(new BigDecimal("22"));
        otherOrder.setTotal(new BigDecimal("243"));

        daoOrder.addOrder(otherOrder.getOrderNumber(), otherOrder);

        daoOrder.removeOrder(order.getOrderNumber());
        assertEquals(1, daoOrder.getAllOrders().size());
        assertNull(daoOrder.getOrder(order.getOrderNumber()));

        daoOrder.removeOrder(otherOrder.getOrderNumber());
        assertEquals(0, daoOrder.getAllOrders().size());
        assertNull(daoOrder.getOrder(otherOrder.getOrderNumber()));
    }

    /**
     * Test of openOrders method, of class FlooringOrderDao.
     */
    @Test
    public void testOpenOrders() throws Exception {
    }

    /**
     * Test of closeOrder method, of class FlooringOrderDao.
     */
    @Test
    public void testCloseOrder() throws Exception {
    }

}
