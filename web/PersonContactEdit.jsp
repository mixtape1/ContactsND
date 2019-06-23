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
        <%
            String idxs = request.getParameter("idx");
            String ContactId = request.getParameter("ContactId");
            int idx = -1;
            int contact = -1;
            try {
                contact = Integer.parseInt(ContactId);

            } catch (Exception e) {
            }
            try {
                idx = Integer.parseInt(idxs);
            } catch (Exception e) {
            }

        %>
        <h1>Edit <%= DB.getById(idx).getFirstName()%> Contacts</h1>
        <form action="SaveContactServlet" method="POST">


            <label> Name
                <input name="name" type="text" value="<%= (ContactId != null) ? DB.getContactById(contact).getContact() : ""%>">
                <input name="ContactId" type="hidden" value="<%= contact%>">
            </label>
            <br>
            <label>
                Type: 
                <input name="type" type="text" value="<%= (ContactId != null) ? DB.getContactById(contact).getType() : ""%>">
            </label>

            <input name="idxs" value="<%=idx%>" type="hidden" >
            <input name="contact" value="<%= contact%>" type="hidden" >

            <br>
            <input class="btn" style="margin-bottom: 10px;" type="submit" value="save">
        </form>

        <a href="PersonContactsList.jsp?personId=<%=idx%>">Cancel</a>





    </body>
</html>
