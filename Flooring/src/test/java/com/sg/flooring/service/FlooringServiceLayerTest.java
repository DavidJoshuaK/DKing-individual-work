/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.service;

import com.sg.flooring.dao.FlooringAuditDao;
import com.sg.flooring.dao.FlooringAuditDaoFileImpl;
import com.sg.flooring.dao.FlooringDaoPersistenceException;
import com.sg.flooring.dao.FlooringOrderDao;
import com.sg.flooring.dao.FlooringOrderDaoFileImpl;
import com.sg.flooring.dao.FlooringProductDao;
import com.sg.flooring.dao.FlooringProductDaoImpl;
import com.sg.flooring.dao.FlooringTaxDao;
import com.sg.flooring.dao.FlooringTaxDaoImpl;
import com.sg.flooring.dto.Order;
import com.sg.flooring.dto.Product;
import com.sg.flooring.dto.Tax;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apprentice
 */
public class FlooringServiceLayerTest {

    FlooringServiceLayer service;


    public FlooringServiceLayerTest() {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContextTests.xml");

        service = ctx.getBean("serviceLayer", FlooringServiceLayer.class);

    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of createOrder method, of class FlooringServiceLayer.
     */
    @Test
    public void testCreateOrderCalculations() throws Exception {

        service.loadFiles();
     

        Order newOrder = new Order();
        newOrder.setCustomerName("Jake");
        newOrder.setState("OH");
        newOrder.setProductType("Wood");
        newOrder.setArea(new BigDecimal("233"));

        service.calculateNewOrderDataInput(newOrder);

        Assert.assertEquals(newOrder.getMaterialCostTotal(), (new BigDecimal("1199.95")));
        Assert.assertEquals(newOrder.getLaborCost(), (new BigDecimal("1106.75")));
        Assert.assertEquals(newOrder.getTax(), (new BigDecimal("144.17")));
        Assert.assertEquals(newOrder.getTotal(), (new BigDecimal("2450.87")));

    }

    /**
     * Test of createOrder method, of class FlooringServiceLayer.
     */
    @Test
    public void testReadProductAndTaxDaoCorrectly() throws Exception {
        service.loadFiles();  

        Order newOrder = new Order();
        newOrder.setCustomerName("Jake");
        newOrder.setState("OH");
        newOrder.setProductType("Wood");
        newOrder.setArea(new BigDecimal("233"));

        service.calculateNewOrderDataInput(newOrder);

        Assert.assertEquals(newOrder.getTaxRate(), (new BigDecimal("6.25")));
        Assert.assertEquals(newOrder.getCostPerSquareFoot(), (new BigDecimal("5.15")));
        Assert.assertEquals(newOrder.getLaborCostPerSquareFoot(), (new BigDecimal("4.75")));

    }

    /**
     * Test of createOrder method, of class FlooringServiceLayer.
     */
    @Test
    public void testProductMatchingExceptionThrown() throws Exception {
       service.loadFiles();      

        Order newOrder = new Order();
        newOrder.setCustomerName("Jake");
        newOrder.setState("OH");
        newOrder.setProductType("Reptile");
        newOrder.setArea(new BigDecimal("233"));

        boolean correctExceptionThrown = false;
        try {
            service.calculateNewOrderDataInput(newOrder);
        } catch (FlooringValidationException e) {
            correctExceptionThrown = true;
        }

        Assert.assertTrue("Must Match Products", correctExceptionThrown);

    }

    /**
     * Test of createOrder method, of class FlooringServiceLayer.
     */
    @Test
    public void testStateMatchingExceptionThrown() throws Exception {
       service.loadFiles();
        
        Order newOrder = new Order();
        newOrder.setCustomerName("Jake");
        newOrder.setState("MN");
        newOrder.setProductType("Wood");
        newOrder.setArea(new BigDecimal("233"));

        boolean correctExceptionThrown = false;
        try {
            service.calculateNewOrderDataInput(newOrder);
        } catch (FlooringValidationException e) {
            correctExceptionThrown = true;
        }

        Assert.assertTrue("Must Match State", correctExceptionThrown);

    }

    /**
     * Test of setOrderId method, of class FlooringServiceLayer.
     */
    @Test
    public void testSetOrderId() throws Exception {
        Order newOrder = new Order();
        newOrder.setOrderNumber(609);
        newOrder.setCustomerName("Jake");
        newOrder.setState("OH");
        newOrder.setProductType("Wood");
        newOrder.setArea(new BigDecimal("233"));

        service.addOrder(newOrder);
        //   addOrder(newOrder.getOrderNumber(), newOrder);

        Order otherOrder = new Order();
        otherOrder.setCustomerName("Mickey");
        otherOrder.setState("PA");
        otherOrder.setProductType("Wood");
        otherOrder.setArea(new BigDecimal("560"));

        service.setOrderId(otherOrder);

        Integer number = 610;

        Assert.assertEquals(otherOrder.getOrderNumber(), number);

    }

    /**
     * Test of getOrder method, of class FlooringServiceLayer.
     */
    //@Test
    public void testGetOrder() {
    }

    /**
     * Test of getOrdersByDate method, of class FlooringServiceLayer.
     */
    @Test
    public void testGetOrdersByDate() throws Exception {
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
        String fileDate = "12-12-1987";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        LocalDate orderDate = LocalDate.parse(fileDate, formatter);
        order.setOrderDate(orderDate);
        service.addOrder(order);
        service.getOrdersByDate(orderDate);

        assertEquals(1, service.getOrdersByDate(orderDate).size()); 
    }

    
    
       @Test
    public void testNoDateException() throws Exception {
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
        String fileDate = "12-12-1987";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        LocalDate orderDate = LocalDate.parse(fileDate, formatter);
        order.setOrderDate(orderDate);
        
        
        String fileDate2 = "12-12-1990";
     
        LocalDate orderDate2 = LocalDate.parse(fileDate2, formatter);
    
        service.addOrder(order);
        service.getOrdersByDate(orderDate);
  
        boolean correctExceptionThrown = false;
        try {
            service.getOrdersByDate(orderDate2);
        } catch (FlooringValidationException e) {
            correctExceptionThrown = true;
        }

        Assert.assertTrue("No Date Matching Order!", correctExceptionThrown);
    }
    /**
     * Test of removeOrder method, of class FlooringServiceLayer.
     */
    // @Test
    public void testRemoveOrder() {
    }

    /**
     * Test of loadOrder method, of class FlooringServiceLayer.
     */
    //@Test
    public void testLoadOrder() throws Exception {
    }

    /**
     * Test of writeOrders method, of class FlooringServiceLayer.
     */
    //@Test
    public void testWriteOrders() throws Exception {
    }

    /**
     * Test of loadProducts method, of class FlooringServiceLayer.
     */
}
