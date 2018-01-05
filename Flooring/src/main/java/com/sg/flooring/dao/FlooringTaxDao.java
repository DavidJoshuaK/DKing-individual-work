/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.dao;

import com.sg.flooring.dto.Order;
import com.sg.flooring.dto.Tax;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface FlooringTaxDao {

    Tax addTax(String state, Tax tax);

    List<Tax> getAllTax();

    Tax getTax(String state);

    Tax removeTax(String state);
    
    public void openTaxes() throws FlooringDaoPersistenceException;
       
}
