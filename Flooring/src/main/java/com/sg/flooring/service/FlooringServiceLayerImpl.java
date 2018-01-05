/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.service;

import com.sg.flooring.dao.FlooringAuditDao;
import com.sg.flooring.dao.FlooringDaoPersistenceException;
import com.sg.flooring.dao.FlooringModeDao;
import com.sg.flooring.dao.FlooringOrderDao;
import com.sg.flooring.dao.FlooringProductDao;
import com.sg.flooring.dao.FlooringTaxDao;
import com.sg.flooring.dto.Order;
import com.sg.flooring.dto.Product;
import com.sg.flooring.dto.Tax;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author apprentice/
 */
public class FlooringServiceLayerImpl implements FlooringServiceLayer {

    private FlooringOrderDao daoOrder;
    private FlooringProductDao daoProduct;
    private FlooringTaxDao daoTax;
    private FlooringAuditDao auditDao;
    private FlooringModeDao modeDao;

    public FlooringServiceLayerImpl(FlooringOrderDao daoOrder, FlooringProductDao daoProduct,
            FlooringTaxDao daoTax, FlooringAuditDao auditDao, FlooringModeDao modeDao) {
        this.daoOrder = daoOrder;
        this.daoProduct = daoProduct;
        this.daoTax = daoTax;
        this.auditDao = auditDao;
        this.modeDao = modeDao;
    }

    @Override
    public Order calculateNewOrderDataInput(Order order) throws FlooringValidationException {

        for (Product product : daoProduct.getAllProducts()) {
            if (product.getProductName().equals(order.getProductType())) {
                order.setLaborCostPerSquareFoot(product.getLaborCost().setScale(2, RoundingMode.HALF_UP));

                order.setCostPerSquareFoot(product.getMaterialCost().setScale(2, RoundingMode.HALF_UP));
            }
        }

        if (order.getLaborCostPerSquareFoot() == null) {
            throw new FlooringValidationException("Must Match Products");
        }

        for (Tax stateTax : daoTax.getAllTax()) {
            if (stateTax.getState().equals(order.getState())) {
                order.setTaxRate(stateTax.getStateTax().setScale(2, RoundingMode.HALF_UP));
            }
        }

        if (order.getTaxRate() == null) {
            throw new FlooringValidationException("Must Match State");
        }

        order.setMaterialCostTotal(order.getArea().multiply(order.getCostPerSquareFoot()).setScale(2, RoundingMode.HALF_UP));
        order.setLaborCost(order.getLaborCostPerSquareFoot().multiply(order.getArea()).setScale(2, RoundingMode.HALF_UP));
        BigDecimal totalNoTax = (order.getMaterialCostTotal().add(order.getLaborCost()).setScale(2, RoundingMode.HALF_UP));
        BigDecimal hundred = new BigDecimal("100.00");
        BigDecimal taxResult = order.getTaxRate().divide(hundred).multiply(totalNoTax);
        order.setTax(taxResult.setScale(2, RoundingMode.HALF_UP));
        order.setTotal(order.getTax().add(totalNoTax).setScale(2, RoundingMode.HALF_UP));

        return order;

    }

    @Override
    public Order setOrderId(Order order) {

        List<Order> orderList = daoOrder.getAllOrders();

        Integer num = 0;

        for (Order orderGetNum : orderList) {
            if (orderGetNum.getOrderNumber() >= num) {
                num = orderGetNum.getOrderNumber();
                num++;
                order.setOrderNumber(num);
            }

        }
        return order;
    }

    @Override
    public List<Order> getAllOrders() {
        return daoOrder.getAllOrders();
    }

    @Override
    public Order getOrder(Integer orderNumber) {
        return daoOrder.getOrder(orderNumber);

    }

    @Override
    public List<Order> getOrdersByDate(LocalDate date) throws FlooringValidationException {
        List<Order> ordersToDisplay = new ArrayList<>();
        List<Order> orderList = daoOrder.getAllOrders();

        for (Order ordersSearch : orderList) {
            if (ordersSearch.getOrderDate().equals(date)) {
                ordersToDisplay.add(ordersSearch);
            }
        }
        if (ordersToDisplay.isEmpty()) {
            throw new FlooringValidationException("No Date Matching Order!");
        }
        return ordersToDisplay;
    }

    @Override
    public Order removeOrder(Integer orderNumber) {
        Order removedOrder = daoOrder.removeOrder(orderNumber);
        return removedOrder;
    }

    @Override
    public void writeOrders() throws FlooringDaoPersistenceException {
        daoOrder.closeOrder();
    }

    @Override
    public void loadFiles() throws FlooringDaoPersistenceException {
        daoOrder.openOrders();
        daoProduct.openProducts();
        daoTax.openTaxes();
    }

    @Override
    public void addOrder(Order order) {
        daoOrder.addOrder(order.getOrderNumber(), order);
    }

    @Override
    public boolean checkMode() throws FlooringDaoPersistenceException {
        return modeDao.checkMode();
    }

}
