/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.controller;

import com.sg.flooring.dao.FlooringDaoPersistenceException;
import com.sg.flooring.dto.Order;
import com.sg.flooring.dto.Product;
import com.sg.flooring.dto.Tax;
import com.sg.flooring.service.FlooringValidationException;
import com.sg.flooring.service.FlooringServiceLayer;
import com.sg.flooring.ui.FlooringView;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class FlooringController {

    private FlooringView view;
    private FlooringServiceLayer service;

    public FlooringController(FlooringServiceLayer service, FlooringView view) {
        this.service = service;
        this.view = view;
    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {

            loadAllFiles();
            service.checkMode();

            boolean trainingMode = service.checkMode();
            if (trainingMode == true) {
                view.displayModeTraining();
            } else {
                view.displayModeProduction();
            }

            while (keepGoing) {
                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        displayOrdersByDate();
                        break;
                    case 2:
                        createNewOrder();
                        break;
                    case 3:
                        editOrder();
                        break;
                    case 4:
                        removeOrder();
                        break;
                    case 5:
                        if (trainingMode) {
                            view.doesNotSave();
                        } else {
                            saveTheOrders();
                        }
                        break;
                    case 6:
                        if (trainingMode) {
                            keepGoing = false;
                            break;
                        } else {
                            saveTheOrders();
                            keepGoing = false;
                            break;
                        }
                    default:
                        return;
                }

            }
        } catch (FlooringDaoPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private void loadAllFiles() throws FlooringDaoPersistenceException {
        service.loadFiles();
    }

    private void saveTheOrders() throws FlooringDaoPersistenceException {
        service.writeOrders();
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void displayOrders() {
        List<Order> orders = service.getAllOrders();
        view.getAllOrders(orders);
    }

    private void displayOrdersByDate() {
        LocalDate date = view.searchOrderDate();
        try {
            List<Order> orders = service.getOrdersByDate(date);
            view.getAllOrders(orders);
        } catch (FlooringValidationException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private void createNewOrder() {
        view.displayCreateNewOrder();

        try {
            Order currentOrder = view.getNewOrderInfo();
            Order newOrder = service.calculateNewOrderDataInput(currentOrder);
            Order orderIdSet = service.setOrderId(newOrder);
            view.getNewOrder(orderIdSet);
            if (view.askUserIfWantToSaveTheOrder() == true) {
                service.addOrder(orderIdSet);
            }
        } catch (FlooringValidationException e) {
            view.displayErrorMessage(e.getMessage());
            createNewOrder();
        }
    }

    private void editOrder() throws FlooringDaoPersistenceException {
        LocalDate date = view.searchOrderDate();
        try {
            List<Order> orders = service.getOrdersByDate(date);
            view.getAllOrders(orders);
            String orderNumber = view.askOrderChoice();
            Order editOrder = service.getOrder(Integer.parseInt(orderNumber));
            editOrder = view.getEditOrderInfo(editOrder);
            service.addOrder(editOrder);
        } catch (FlooringValidationException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private void removeOrder() throws FlooringDaoPersistenceException {
        LocalDate date = view.searchOrderDate();
        try {
            List<Order> orders = service.getOrdersByDate(date);
            view.getAllOrders(orders);
            Integer orderNumber = view.getOrderChoice();
            if (view.askUserIfWantToRemoveTheOrder() == true) {
                service.removeOrder(orderNumber);
            }
        } catch (FlooringValidationException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }
}
