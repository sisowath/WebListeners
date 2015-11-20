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

public class Login extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println("Inside login.java");
        String  u = request.getParameter("username");
        String p = request.getParameter("password");          
        try {
            Class.forName(this.getServletContext().getInitParameter("jdbcDriver"));
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }       
        Connexion.setUrl(this.getServletContext().getInitParameter("databaseURL"));
        UserDao unUserDao = new UserDao(Connexion.getInstance());
        User unUser = unUserDao.read( u );        
        if (unUser==null) {
            //Utilisateur inexistant
            request.setAttribute("message-warning", "Utilisateur { " + u + " } inexistant.");
            RequestDispatcher r = this.getServletContext().getRequestDispatcher("/index.jsp");
            r.forward(request, response);
        } else if (!unUser.getPassword().equals(p)) {
            //Mot de passe incorrect
            request.setAttribute("message-warning", "Mot de passe incorrect.");
            RequestDispatcher r = this.getServletContext().getRequestDispatcher("/index.jsp");
            r.forward(request, response);
        } else {
            //connexion OK
            if( unUserDao.updateEtatToActif( unUser ) ) {
                HttpSession session = request.getSession(true);
                session.setAttribute("connected", unUser.getUsername());     
                session.setAttribute("user", unUser);
                out.println("Connexion réussie !!");
                request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);             
            } else {
                out.println("Connexion presque réussie !!");
            }
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
    public String getServletInfo() {
        return "Short description";
    }
}