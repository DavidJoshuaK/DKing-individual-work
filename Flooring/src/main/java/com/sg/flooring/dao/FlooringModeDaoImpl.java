/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class FlooringModeDaoImpl implements FlooringModeDao {

    public static final String CONFIGURATION_FILE = "Configuration.txt";

    @Override
    public boolean checkMode() throws FlooringDaoPersistenceException {

        Boolean trainingMode = true;
        Scanner scanner;
        try {
            scanner = new Scanner(new BufferedReader(
                            new FileReader(CONFIGURATION_FILE)));
        } catch (FileNotFoundException e) {
            throw new FlooringDaoPersistenceException(
                    "-_- Could not load inventory data into memory.", e);
        }

        String currentLine;

        currentLine = scanner.nextLine();
        trainingMode = !currentLine.equals("mode=production");
        scanner.close();

        return trainingMode;
    }

}
