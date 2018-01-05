/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.ui;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class UserIOConsoleImpl implements UserIO {

    private Scanner sc = new Scanner(System.in);
    private String input = "";

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public double readDouble(String prompt) {
        print(prompt);
        input = sc.nextLine();
        return Double.parseDouble(input);
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        do {
            print(prompt);
            input = sc.nextLine();
        } while (Double.parseDouble(input) < min || Double.parseDouble(input) > max);

        return Double.parseDouble(input);
    }

    @Override
    public float readFloat(String prompt) {
        print(prompt);
        input = sc.nextLine();
        return Float.parseFloat(input);
    }

    @Override
    public float readFloat(String prompt, float min, float max) {

        do {
            print(prompt);
            input = sc.nextLine();
        } while (Float.parseFloat(input) < min || Float.parseFloat(input) > max);

        return Float.parseFloat(input);
    }

    @Override
    public int readInt(String prompt) {
        print(prompt);
        input = sc.nextLine();
        return Integer.parseInt(input);
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        do {
            print(prompt);
            input = sc.nextLine();
        } while (Integer.parseInt(input) < min || Integer.parseInt(input) > max);
        return Integer.parseInt(input);
    }

    @Override
    public long readLong(String prompt) {
        print(prompt);
        input = sc.nextLine();
        return Long.parseLong(input);
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        do {
            print(prompt);
            input = sc.nextLine();
        } while (Long.parseLong(input) < min || Long.parseLong(input) > max);

        return Long.parseLong(input);
    }

    @Override
    public String readString(String prompt) {
        print(prompt);
        input = sc.nextLine();
        return input;
    }

    @Override
    public int readInt(String please_select_from_the_above_choices, double d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LocalDate readLocalDate(String prompt) {
        String input = "";
        LocalDate date = null;

        while (true) {
            try {
                print(prompt);
                input = sc.nextLine();
                date = LocalDate.parse(input);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date!");
            }
        }
        return date;
    }

    @Override
    public BigDecimal readBigDecimal(String prompt) {
        String input = "";
        BigDecimal bdec = new BigDecimal("0.00");

        while (true) {
            try {
                print(prompt);
                input = sc.nextLine();
                bdec = new BigDecimal(input);
                break;
            } catch (Exception e) {
                System.out.println("Invalid input, must be greater than 0.");
            }
        }
        return bdec;
    }

}
