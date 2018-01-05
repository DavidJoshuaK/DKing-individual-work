/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.dao;

import com.sg.flooring.dto.Order;
import com.sg.flooring.dto.Product;
import com.sg.flooring.dto.Tax;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
public class FlooringTaxDaoImpl implements FlooringTaxDao {

    private Map<String, Tax> stateTaxes = new HashMap<>();

    public static final String STATETAX_FILE = "StateTax.txt";
    public static final String DELIMITER = ", ";

    private void loadTaxes() throws FlooringDaoPersistenceException {
        Scanner scanner;

        try {
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(STATETAX_FILE)));
        } catch (FileNotFoundException e) {
            throw new FlooringDaoPersistenceException(
                    "-_- Could not load inventory into memory.", e);
        }

        String currentLine;

        String[] currentTokens;

        while (scanner.hasNextLine()) {

            currentLine = scanner.nextLine();

            currentTokens = currentLine.split(DELIMITER, 2);

            Tax currentStateTax = new Tax();
            currentStateTax.setState(currentTokens[0]);

            currentStateTax.setStateTax(new BigDecimal(currentTokens[1]));

            stateTaxes.put(currentStateTax.getState(), currentStateTax);
            //inventoryItems.put(currentItem.getIdOfInventory(), currentItem);

        }
        // close scanner
        scanner.close();
    }

    @Override
    public Tax addTax(String state, Tax tax) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Tax> getAllTax() {
        return new ArrayList<Tax>(stateTaxes.values());
    }

    @Override
    public Tax getTax(String state) {
        return stateTaxes.get(state);
    }

    @Override
    public Tax removeTax(String state) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void openTaxes() throws FlooringDaoPersistenceException {
        loadTaxes();
    }

}
