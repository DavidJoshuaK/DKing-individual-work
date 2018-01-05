/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.dao;

import com.sg.flooring.dto.Order;
import com.sg.flooring.dto.Product;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class FlooringProductDaoImpl implements FlooringProductDao {

    private Map<String, Product> products = new HashMap<>();
    //   private Map<BigDecimal, Change> changeBack = new HashMap<>();

    public static final String PRODUCT_FILE = "Product.txt";
    public static final String DELIMITER = ", ";

    private void loadProducts() throws FlooringDaoPersistenceException {
        Scanner scanner;

        try {

            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(PRODUCT_FILE)));
        } catch (FileNotFoundException e) {
            throw new FlooringDaoPersistenceException(
                    "-_- Could not load inventory into memory.", e);
        }

        String currentLine;

        String[] currentTokens;

        while (scanner.hasNextLine()) {

            currentLine = scanner.nextLine();

            currentTokens = currentLine.split(DELIMITER, 3);

            Product currentProduct = new Product();
            currentProduct.setProductName(currentTokens[0]);

            currentProduct.setMaterialCost(new BigDecimal(currentTokens[1]));
            currentProduct.setLaborCost(new BigDecimal(currentTokens[2]));

            products.put(currentProduct.getProductName(), currentProduct);

        }

        scanner.close();
    }

    @Override
    public Product addProduct(String productName, Product product) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Product> getAllProducts() {
        return new ArrayList<Product>(products.values());
    }

    @Override
    public Product getProduct(String productName)  {
        return products.get(productName);
    }

    @Override
    public Product removeProducts(String productName)  {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void openProducts() throws FlooringDaoPersistenceException {
        loadProducts();
    }

}
