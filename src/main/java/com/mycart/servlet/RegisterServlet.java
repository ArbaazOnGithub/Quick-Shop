package com.mycart.servlet;

import com.mycart.entities.User;
import com.mycart.helper.FactoryProvider;
import jakarta.persistence.Query;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class RegisterServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            try {
                String userName = request.getParameter("user_name");
                String userEmail = request.getParameter("user_email");
                String userPassword = request.getParameter("user_password");
                String userPhone = request.getParameter("user_phone");
                String userAddress = request.getParameter("user_address");

                //validation
                if (userName.isEmpty()) {
                    out.println("Name is blank");
                    return;
                }

//                if (isEmailRegisterd(userEmail)) {
//
//                    HttpSession httpSession = request.getSession();
//                    httpSession.setAttribute("message", ""+userEmail+" Email is already registered !! ");
//                    response.sendRedirect(request.getContextPath() + "/register.jsp");
//                    return;
//                }

                // creating user object to store data
                User user = new User(userName, userEmail, userPassword, userPhone, "default.jpg", userAddress, "normal");

                Session hibernateSession = FactoryProvider.getFactory().openSession();

                Transaction tx = hibernateSession.beginTransaction();
                int userId = (int) hibernateSession.save(user);

                tx.commit();
                hibernateSession.close();

                HttpSession httpSession = request.getSession();
                httpSession.setAttribute("message", "Registration Successful !! " + "user Id is " + userId);
                response.sendRedirect(request.getContextPath() + "/register.jsp");

                return;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

//    private boolean isEmailRegisterd(String email) {
//        Session session = FactoryProvider.getFactory().openSession();
//        String q1 = "FROM user WHERE userEmail=:email";
//        Query query = session.createQuery(q1);
//        query.setParameter("email", email);
//        User existingUser = null;
//        try {
//            existingUser = (User) query.getSingleResult(); // Use getSingleResult instead of uniqueResult
//        } catch (Exception e) {
//            // If no result is found, handle the exception (NoResultException or any other Hibernate exception)
//            existingUser = null; // This means email is not registered
//        }
//
//        session.close();
//
//        // Return true if a user with the given email exists, false otherwise
//        return existingUser != null;
//    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
