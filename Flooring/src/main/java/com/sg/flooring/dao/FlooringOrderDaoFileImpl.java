/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.dao;

import com.sg.flooring.dto.Order;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author apprentice
 */
public class FlooringOrderDaoFileImpl implements FlooringOrderDao {

    private Map<Integer, Order> ordersMap = new HashMap<>();

    // private Map<LocalDate, List<Order>> orderbydate = new HashMap<>();
    private Set<LocalDate> dateMap = new HashSet<>();

    public static final String ORDER_FILE = "ORDERS/Orders_12121987.txt";
    public static final String ORDER_PATH = "ORDERS/";
    public final String DELIMITER = ", ";

    private void loadOrders() throws FlooringDaoPersistenceException {
        Scanner scanner;

        File directory = new File(ORDER_PATH);
        File[] files = directory.listFiles();
        for (File file : files) {
            try {
                scanner = new Scanner(new BufferedReader(new FileReader(file)));
            } catch (FileNotFoundException e) {
                throw new FlooringDaoPersistenceException(
                        "-_- Could not load inventory into memory.", e);
            }

            String currentLine;
            String[] currentTokens;

            // 7-15
            String fileDate = file.getName().substring(7, 15); //Orders_12121987 = 12121987
            String date = fileDate.substring(0, 2) + "-" + fileDate.substring(2, 4) + "-" + fileDate.substring(4); //12121987 = 12-12-1987

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");

            LocalDate orderDate = LocalDate.parse(date, formatter);
            
            while (scanner.hasNextLine()) {
                currentLine = scanner.nextLine();
                currentTokens = currentLine.split(DELIMITER, 12);

                Order currentOrder = new Order();

                currentOrder.setOrderNumber(Integer.parseInt(currentTokens[0]));
                currentOrder.setCustomerName(currentTokens[1]);
                currentOrder.setState(currentTokens[2]);
                currentOrder.setTaxRate(new BigDecimal(currentTokens[3]));
                currentOrder.setProductType(currentTokens[4]);
                currentOrder.setArea(new BigDecimal(currentTokens[5]));
                currentOrder.setCostPerSquareFoot(new BigDecimal(currentTokens[6]));
                currentOrder.setLaborCostPerSquareFoot(new BigDecimal(currentTokens[7]));
                currentOrder.setMaterialCostTotal(new BigDecimal(currentTokens[8]));
                currentOrder.setLaborCost(new BigDecimal(currentTokens[9]));
                currentOrder.setTax(new BigDecimal(currentTokens[10]));
                currentOrder.setTotal(new BigDecimal(currentTokens[11]));

                currentOrder.setOrderDate(orderDate);

                ordersMap.put(currentOrder.getOrderNumber(), currentOrder);
                dateMap.add(orderDate);
                
            }
            scanner.close();
        }

    }

    private void writeOrder() throws FlooringDaoPersistenceException {

        List<Order> orderForDates = this.getAllOrders();

        for (Order orderDates : orderForDates) {
            dateMap.add(orderDates.getOrderDate());
        }
        for (LocalDate date : dateMap) {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
            String dateFormat = date.format(formatter);
            dateFormat = dateFormat.replace("-", "");//MMddyyyy

            File fileName = new File(ORDER_PATH + "Orders_" + dateFormat + ".txt");

            PrintWriter out;

            try {
                out = new PrintWriter(new FileWriter(fileName));
            } catch (IOException e) {
                throw new FlooringDaoPersistenceException(
                        "Could not save order data.", e);
            }

            List<Order> orderList = this.getAllOrders();

            for (Order currentOrder : orderList) {
                if (currentOrder.getOrderDate().equals(date)) {

                    out.println(currentOrder.getOrderNumber() + DELIMITER
                            + currentOrder.getCustomerName() + DELIMITER
                            + currentOrder.getState() + DELIMITER
                            + currentOrder.getTaxRate() + DELIMITER
                            + currentOrder.getProductType() + DELIMITER
                            + currentOrder.getArea() + DELIMITER
                            + currentOrder.getCostPerSquareFoot() + DELIMITER
                            + currentOrder.getLaborCostPerSquareFoot() + DELIMITER
                            + currentOrder.getMaterialCostTotal() + DELIMITER
                            + currentOrder.getLaborCost() + DELIMITER
                            + currentOrder.getTax() + DELIMITER
                            + currentOrder.getTotal());

                }
                out.flush();

            }
            out.close();
        }

    }

    @Override
    public Order addOrder(Integer orderNumber, Order order) {
        Order newOrder = ordersMap.put(orderNumber, order);
        return newOrder;
    }

    @Override
    public List<Order> getAllOrders() {
        return new ArrayList<Order>(ordersMap.values());
    }

    @Override
    public Order getOrder(Integer orderNumber) {
        return ordersMap.get(orderNumber);

    }

    @Override
    public Order removeOrder(Integer orderNumber) {
        Order removedOrder = ordersMap.remove(orderNumber);
        return removedOrder;
    }

    @Override
    public void openOrders() throws FlooringDaoPersistenceException {
        loadOrders();
    }

    @Override
    public void closeOrder() throws FlooringDaoPersistenceException {
        writeOrder();
    }

}
