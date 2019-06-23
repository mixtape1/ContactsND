/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.bit.data;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lt.bit.db.DB;

/**
 *
 * @author adoma
 */
@WebServlet(name = "SaveContactServlet", urlPatterns = {"/SaveContactServlet"})
public class SaveContactServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String contactIds = request.getParameter("ContactId");
                
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        String idxs = request.getParameter("idxs");

        int idx = -1;
        try {
            idx = Integer.parseInt(idxs);
        } catch (Exception e) {
        }
        int contactId = -1;
        try {
            contactId = Integer.parseInt(contactIds);
        } catch (Exception e) {
        }

        if (contactId <0 && contactId<DB.getPersonContacts(idx).size()) {
            DB.addContact(idx, new Contact(name, type));
        } else {
//            DB.getAll().set(DB.getAll().indexOf(DB.getById(idx)), new Person(firstName, lastName, nDate, salaryBig));
            
            DB.getPersonContacts(idx).set(DB.getPersonContacts(idx).indexOf(DB.getContactById(contactId)), new Contact(name, type));
               
        }

        response.sendRedirect("PersonContactsList.jsp?personId="+idx);
    }

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

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
