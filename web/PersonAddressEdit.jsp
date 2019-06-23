<%-- 
    Document   : PersonAddressEdit
    Created on : 16-Jun-2019, 21:46:52
    Author     : adoma
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="lt.bit.db.DB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="styles.css">

    </head>
    <body>
        <h1>Edit Person Address</h1>
        <form action="SaveAddressServlet" method="POST">

            <%
                String idxs = request.getParameter("idx");
                String addressId = request.getParameter("addressId");
                int idx = -1;
                int address = -1;
                try {
                    address = Integer.parseInt(addressId);

                } catch (Exception e) {
                }
                try {
                    idx = Integer.parseInt(idxs);
                } catch (Exception e) {
                }

            %>
            <label> Address:
                <input name="address" type="text" value="<%= (addressId != null) ? DB.getAddressById(address).getAddress() : ""%>">
                <input name="addressId" type="hidden" value="<%= address %>">
            </label>
            <br>
            <label>
                City:
                <input name="city" type="text" value="<%= (addressId != null) ? DB.getAddressById(address).getCity() : ""%>">
            </label>
            <br>
            <label>
                Post code: 
                <input name="postCode" type="text" value="<%= (addressId != null) ? DB.getAddressById(address).getPostalCode() : ""%>">
            </label>
            <input name="idxs" value="<%=idx%>" type="hidden" >
            <input name="address" value="<%= address%>" type="hidden" >

            <br>
            <input class="btn" style="margin-bottom: 10px;" type="submit" value="save">
        </form>

        <a href="PersonAdressList.jsp?personId=<%=idx%>">Cancel</a>





    </body>
</html>
