/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import domain.User;
import domain.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Mason
 */
public class LoginServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        //Logs out if there is a logout parameter in the request
        if(request.getParameter("logout") != null) {
            session.invalidate();
            request.setAttribute("feedback", "User has been logged out");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }
        
        if(session.getAttribute("userName") != null) {
            response.sendRedirect("home");
            return;
        }
        
        Cookie[] cookies = request.getCookies();
        String cookieName = "userName";
        
        for(Cookie c: cookies) {
            if(cookieName.equals(c.getName())) {
                request.setAttribute("username", c.getValue());
                request.setAttribute("checked", "checked");
            }
        }
            
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        
        if((((userName == null) || (userName.equals(""))) ||((password == null) || (password.equals(""))))) {
            request.setAttribute("error", "Please fill all fields");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);  
            return;
        }
        
        UserService us = new UserService();
        User user = us.login(userName, password);
        
        if(user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("username", userName);
            
            if(request.getParameter("cache") != null) {
                Cookie c = new Cookie("userLoginCookie", userName);
                c.setMaxAge(60*60*24);
                c.setPath("/");
                response.addCookie(c);
                
            }
            response.sendRedirect("home");
     
        }
        else {
            request.setAttribute("username", userName);
            request.setAttribute("error", "Invalid Information");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        
        }
    }
}
