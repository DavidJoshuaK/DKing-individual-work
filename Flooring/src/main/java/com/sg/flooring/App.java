/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring;

import com.sg.flooring.controller.FlooringController;
import com.sg.flooring.dao.FlooringDaoPersistenceException;
import com.sg.flooring.dao.FlooringOrderDao;
import com.sg.flooring.dao.FlooringOrderDaoFileImpl;
import com.sg.flooring.dao.FlooringProductDao;
import com.sg.flooring.dao.FlooringProductDaoImpl;
import com.sg.flooring.dao.FlooringTaxDao;
import com.sg.flooring.dao.FlooringTaxDaoImpl;
import com.sg.flooring.service.FlooringServiceLayer;
import com.sg.flooring.service.FlooringServiceLayerImpl;
import com.sg.flooring.ui.FlooringView;
import com.sg.flooring.ui.UserIO;
import com.sg.flooring.ui.UserIOConsoleImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apprentice
 */
public class App {

    public static void main(String[] args) throws FlooringDaoPersistenceException {

//        UserIO myIo = new UserIOConsoleImpl();
//      
//        FlooringView myView = new FlooringView(myIo);
//    
//        FlooringOrderDao myDao = new FlooringOrderDaoFileImpl();
//        
//        FlooringProductDao myProductDao = new FlooringProductDaoImpl();
//        
//        FlooringTaxDao myTaxDao = new FlooringTaxDaoImpl();
//
//        FlooringServiceLayer myService = new FlooringServiceLayerImpl(myDao, myProductDao, myTaxDao);
//        
//        FlooringController controller = new FlooringController(myService, myView);
//        
//        controller.run();

        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("applicationContext.xml");
        
        FlooringController controller
                = ctx.getBean("controller", FlooringController.class);
        
        controller.run();

    }
}
