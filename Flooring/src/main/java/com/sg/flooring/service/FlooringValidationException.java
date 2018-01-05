/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.service;

/**
 *
 * @author apprentice
 */
public class FlooringValidationException extends Exception {

    public FlooringValidationException(String message) {
        super(message);
    }

    public FlooringValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
