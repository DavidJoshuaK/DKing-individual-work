/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.ui;

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
public class FlooringView {

    private UserIO io;

    public FlooringView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("*******************************************************");
        io.print("*  <<Flooring Menu>>");
        io.print("*  1. Display Orders");
        io.print("*  2. Add an Order");
        io.print("*  3. Edit an Order");
        io.print("*  4. Remove an Order");
        io.print("*  5. Save Current Work");
        io.print("*  6. Quit");
        io.print("*******************************************************");
        return io.readInt("Please select from the above choices.", 1, 6);

    }

    public void getAllOrders(List<Order> orderList) {
        for (Order currentOrder : orderList) {
            io.print(" OrderNumber: " + currentOrder.getOrderNumber() + ", "
                + " Customer: " + currentOrder.getCustomerName() + ", "
                + " State: " + currentOrder.getState() + ", "
                + " Tax Rate: " + currentOrder.getTaxRate() + ", "
                + " Product: " + currentOrder.getProductType() + ", "
                + " Area: " + currentOrder.getArea() + ", "+ "\n"
                + " Cost per square foot: " + currentOrder.getCostPerSquareFoot() + ", "
                + " Labor cost per square foot: " + currentOrder.getLaborCostPerSquareFoot() + ", "
                + " Materials cost: " + currentOrder.getMaterialCostTotal() + ", "
                + " Labor cost: " + currentOrder.getLaborCost() + ", " + "\n"
                + " Tax total: " + currentOrder.getTax() + ", "
                + " Total price: " + currentOrder.getTotal());
        }
    }

    public void displayErrorMessage(String errorMessage) {
        io.print("---ERROR---");
        io.print(errorMessage);
    }

    public LocalDate searchOrderDate() {
        return io.readLocalDate("Please enter date you want to search (YYYY-MM-DD): ");
    }

    public void displayCreateNewOrder() {
        io.print("----Create New Order----");
    }

    public Product getProductType() {
        String product = io.readString("Enter Product Choice: ");

        Product newProduct = new Product();
        newProduct.setProductName(product);
        return newProduct;
    }

    public Tax getStateTaxType() {
        String stateTax = io.readString("Enter State: ");

        Tax newState = new Tax();
        newState.setState(stateTax);

        return newState;

    }

    public Order getNewOrderInfo() {

        String customerName = io.readString("Enter customer name: ");
        String state = io.readString("Enter state: ");
        String productType = io.readString("Enter product type: ");
        BigDecimal area = io.readBigDecimal("Enter area: ");
        LocalDate date = io.readLocalDate("Please enter date (YYYY-MM-DD): ");

        Order currentOrder = new Order();
        currentOrder.setCustomerName(customerName);
        currentOrder.setState(state);
        currentOrder.setProductType(productType);
        currentOrder.setArea(area);
        currentOrder.setOrderDate(date);

        io.print("Customer name: " + currentOrder.getCustomerName()
                + "State: " + currentOrder.getState()
                + "Product: " + currentOrder.getProductType()
                + "Area: " + currentOrder.getArea()
                + "Date: " + currentOrder.getOrderDate());

        return currentOrder;

    }

    public void displayNewCreatedOrder(List<Order> orderList) {
        for (Order currentOrder : orderList) {
            io.print(" OrderNumber: " + currentOrder.getOrderNumber() + ", "
                + " Customer: " + currentOrder.getCustomerName() + ", "
                + " State: " + currentOrder.getState() + ", "
                + " Tax Rate: " + currentOrder.getTaxRate() + ", "
                + " Product: " + currentOrder.getProductType() + ", "
                + " Area: " + currentOrder.getArea() + ", "+ "\n"
                + " Cost per square foot: " + currentOrder.getCostPerSquareFoot() + ", "
                + " Labor cost per square foot: " + currentOrder.getLaborCostPerSquareFoot() + ", "
                + " Materials cost: " + currentOrder.getMaterialCostTotal() + ", "
                + " Labor cost: " + currentOrder.getLaborCost() + ", " + "\n"
                + " Tax total: " + currentOrder.getTax() + ", "
                + " Total price: " + currentOrder.getTotal());
        }
    }

    public String askOrderChoice() {
        return io.readString("Please enter the order Number: .");
    }

    public int getOrderChoice() {
        return io.readInt("Please provide order Number: ");
    }

    public Order getEditOrderInfo(Order orderToEdit) {

        Order newOrder = new Order();

        String newCustomerName = io.readString("Please update title" + "(" + orderToEdit.getCustomerName() + ")");
        String newState = io.readString("Update State" + "(" + orderToEdit.getState() + ")");
        String newTaxRate = io.readString("Update tax rate" + "(" + orderToEdit.getTaxRate() + ")");
        String newProductType = io.readString("Update product type: " + "(" + orderToEdit.getProductType() + ")");
        String newArea = io.readString("Update area: " + "(" + orderToEdit.getArea() + ")");
        String newCostPerSquare = io.readString("Update cost per square foot: " + "(" + orderToEdit.getCostPerSquareFoot() + ")");
        String newLaborCostSquareFoot = io.readString("Update labor cost per square foot: " + "(" + orderToEdit.getLaborCostPerSquareFoot() + ")");
        String newMaterialCost = io.readString("Update labor cost per square foot: " + "(" + orderToEdit.getMaterialCostTotal() + ")");
        String newLaborCost = io.readString("Update labor cost per square foot: " + "(" + orderToEdit.getLaborCost() + ")");
        String newTax = io.readString("Update total tax: " + "(" + orderToEdit.getTax() + ")");
        String newTotal = io.readString("Update new Total: " + "(" + orderToEdit.getTotal() + ")");
        LocalDate newDate = io.readLocalDate("Please enter new date (YYYY-MM-DD): " + "(" + orderToEdit.getOrderDate() + ")");

        newOrder.setOrderNumber(orderToEdit.getOrderNumber());

        if (newCustomerName != null && !newCustomerName.isEmpty()) {
            newOrder.setCustomerName(newCustomerName);
        } else {
            String oldCustomerName = orderToEdit.getCustomerName();
            newOrder.setCustomerName(oldCustomerName);
        }

        if (newState != null && !newState.isEmpty()) {
            newOrder.setState(newState);
        } else {
            String oldState = orderToEdit.getState();
            newOrder.setState(oldState);
        }

        if (newTaxRate != null && !newTaxRate.isEmpty()) {
            newOrder.setTaxRate(new BigDecimal(newTaxRate));
        } else {
            BigDecimal oldTaxRate = orderToEdit.getTaxRate();
            newOrder.setTaxRate(oldTaxRate);
        }

        if (newProductType != null && !newProductType.isEmpty()) {
            newOrder.setProductType(newProductType);
        } else {
            String oldProductType = orderToEdit.getProductType();
            newOrder.setProductType(oldProductType);
        }

        if (newArea != null && !newArea.isEmpty()) {
            newOrder.setArea(new BigDecimal(newArea));
        } else {
            BigDecimal oldArea = orderToEdit.getArea();
            newOrder.setArea(oldArea);
        }

        if (newCostPerSquare != null && !newCostPerSquare.isEmpty()) {
            newOrder.setCostPerSquareFoot(new BigDecimal(newCostPerSquare));
        } else {
            BigDecimal oldCPSF = orderToEdit.getCostPerSquareFoot();
            newOrder.setCostPerSquareFoot(oldCPSF);
        }

        if (newLaborCostSquareFoot != null && !newLaborCostSquareFoot.isEmpty()) {
            newOrder.setLaborCost(new BigDecimal(newLaborCostSquareFoot));
        } else {
            BigDecimal oldLaborCostPerSquare = orderToEdit.getLaborCostPerSquareFoot();
            newOrder.setLaborCostPerSquareFoot(oldLaborCostPerSquare);
        }

        if (newMaterialCost != null && !newMaterialCost.isEmpty()) {
            newOrder.setMaterialCostTotal(new BigDecimal(newMaterialCost));
        } else {
            BigDecimal oldMaterialCost = orderToEdit.getMaterialCostTotal();
            newOrder.setMaterialCostTotal(oldMaterialCost);
        }

        if (newLaborCost != null && !newLaborCost.isEmpty()) {
            newOrder.setLaborCost(new BigDecimal(newLaborCost));
        } else {
            BigDecimal oldLaborCost = orderToEdit.getLaborCost();
            newOrder.setLaborCost(oldLaborCost);
        }

        if (newTax != null && !newTax.isEmpty()) {
            newOrder.setTax(new BigDecimal(newTax));
        } else {
            BigDecimal oldTax = orderToEdit.getTax();
            newOrder.setTax(oldTax);
        }

        if (newTotal != null && !newTotal.isEmpty()) {
            newOrder.setTotal(new BigDecimal(newTotal));
        } else {
            BigDecimal oldTotal = orderToEdit.getTotal();
            newOrder.setTotal(oldTotal);
        }

        if (newDate != null) {
            newOrder.setOrderDate(newDate);
        } else {
            LocalDate oldDate = orderToEdit.getOrderDate();
            newOrder.setOrderDate(oldDate);
        }

        String answer = io.readString("Would you like to save this edited order? Y/N");
        String yes = "y";

        if (answer.toLowerCase().equals(yes)) {
            return newOrder;
        } else {
            return orderToEdit;
        }
    }

    public void getNewOrder(Order currentOrder) {
        io.print(" OrderNumber: " + currentOrder.getOrderNumber() + ", "
                + " Customer: " + currentOrder.getCustomerName() + ", "
                + " State: " + currentOrder.getState() + ", "
                + " Tax Rate: " + currentOrder.getTaxRate() + ", "
                + " Product: " + currentOrder.getProductType() + ", "
                + " Area: " + currentOrder.getArea() + ", "+ "\n"
                + " Cost per square foot: " + currentOrder.getCostPerSquareFoot() + ", "
                + " Labor cost per square foot: " + currentOrder.getLaborCostPerSquareFoot() + ", "
                + " Materials cost: " + currentOrder.getMaterialCostTotal() + ", "
                + " Labor cost: " + currentOrder.getLaborCost() + ", " + "\n"
                + " Tax total: " + currentOrder.getTax() + ", "
                + " Total price: " + currentOrder.getTotal());
    }

    public boolean askUserIfWantToSaveTheOrder() {
        String answer = io.readString("Would you like to save? Y/N");
        String yes = "y";
        if (answer.toLowerCase().equals(yes)) {
            return true;
        } else {
           return false;
        } 
    }
    
      public boolean askUserIfWantToRemoveTheOrder() {
        String answer = io.readString("Are you sure you want to remove this order? Y/N");
        String yes = "y";
        if (answer.toLowerCase().equals(yes)) {
            return true;
        } else {
           return false;
        } 
    }


    public void presentRemoveSuccessBanner() {
        io.print("****The order has been successfully removed!****");
    }

    public void displayModeTraining() {
        io.print("TRAINING MODE");
    }

    public void displayModeProduction() {
        io.print("PRODUCTION MODE");
    }

    public void doesNotSave() {
        io.print("Unable to write to files in training mode.");
    }

}
