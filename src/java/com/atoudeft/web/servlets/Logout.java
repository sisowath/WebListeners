package com.atoudeft.web.servlets;

import com.atoudeft.entites.User;
import com.atoudeft.jdbc.connexion.Connexion;
import com.atoudeft.dao.implementation.UserDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Logout extends HttpServlet {
	
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        if( request.getSession() != null ) {
            try {
                Class.forName( request.getServletContext().getInitParameter("jdbcDriver") );
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Logout.class.getName()).log(Level.SEVERE, null, ex);
            }
            Connexion.setUrl( request.getServletContext().getInitParameter("databaseURL") );
        
            UserDao unUserDao = new UserDao( Connexion.getInstance() );
            unUserDao.updateEtatToActif( (User)request.getSession().getAttribute("user") );
            request.getSession().invalidate();
        }
        request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        //out.println("Je suis dans logout.java");
    }
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    public String getServletInfo() {
        return "Short description";
    }
}