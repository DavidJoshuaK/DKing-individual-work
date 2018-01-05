/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.advice;

import com.sg.flooring.dao.FlooringAuditDao;
import com.sg.flooring.dao.FlooringDaoPersistenceException;
import org.aspectj.lang.JoinPoint;


/**
 *
 * @author apprentice
 */
public class LoggingAdvice {

    private FlooringAuditDao auditDao;

    private LoggingAdvice(FlooringAuditDao auditDao) {
        this.auditDao = auditDao;
    }

    public void createAuditEntry(JoinPoint jp, Exception exceptionMessage) {
        Object[] args = jp.getArgs();
        String auditEntry = jp.getSignature().getName() + ": " + exceptionMessage.getMessage() + ": ";
        for (Object currentArg : args) {
            auditEntry += currentArg;
        }
        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (FlooringDaoPersistenceException e) {
            System.err.println("ERROR: Could not create audit entry in LoggingAdvice");
        }
    }

}
