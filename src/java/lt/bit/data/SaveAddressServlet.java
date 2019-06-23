/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.bit.data;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
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
@WebServlet(name = "SaveAddressServlet", urlPatterns = {"/SaveAddressServlet"})
public class SaveAddressServlet extends HttpServlet {

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

        String address = request.getParameter("address");
        String addressIds = request.getParameter("addressId");

        String city = request.getParameter("city");
        String postCode = request.getParameter("postCode");
        String idxs = request.getParameter("idxs");

        int idx = -1;
        try {
            idx = Integer.parseInt(idxs);
        } catch (Exception e) {
        }
        int addressId = -1;
        try {
            addressId = Integer.parseInt(addressIds);
        } catch (Exception e) {
        }
        System.out.println("address id " + addressId);

        if (addressId < 0 && addressId < DB.getPersonAddresses(idx).size()) {
            if (address == "" || city == "" || postCode == "") {
                //do nothing
            } else {
                DB.addAddress(idx, new Address(address, city, postCode));

            }
        } else {
//            DB.getAll().set(DB.getAll().indexOf(DB.getById(idx)), new Person(firstName, lastName, nDate, salaryBig));

            DB.getPersonAddresses(idx).set(DB.getPersonAddresses(idx).indexOf(DB.getAddressById(addressId)), new Address(address, city, postCode));

        }

        response.sendRedirect("PersonAdressList.jsp?personId=" + idx);
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
