/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.atoudeft.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author moumene
 */
public class EcouteurApplication implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Application d�mar�e");
        ServletContext appli = sce.getServletContext();
        appli.setAttribute("nbConnectes", 0);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Application termin�e");
    }
    
}
