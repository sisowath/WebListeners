/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.atoudeft.listeners;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 *
 * @author moumene
 */
public class EcouteurSession implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        ServletContext appli = se.getSession().getServletContext();
        int n = Integer.parseInt(appli.getAttribute("nbConnectes").toString());
        n++;
        System.out.println(n+"eme Session ouverte");
        appli.setAttribute("nbConnectes", n);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        ServletContext appli = se.getSession().getServletContext();
        int n = Integer.parseInt(appli.getAttribute("nbConnectes").toString());
        n--;
        System.out.println(n+"eme Session fermée");
        appli.setAttribute("nbConnectes", n);
    }    
}
