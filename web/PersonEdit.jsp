<%-- 
    Document   : PersonEdit
    Created on : 14-Jun-2019, 21:51:14
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
        <h1>Edit Person Details</h1>
        <form action="SavePersonServlet" method="POST">

            <%  String idxs = request.getParameter("idx");
                int idx = -1;
                try {
                    idx = Integer.parseInt(idxs);
                } catch (Exception e) {
                }
            %>
            <label> First Name:
                <input name="firstName" type="text" value="<%= (idxs != null) ? DB.getById(idx).getFirstName() : ""%>">
            </label>
            <br>
            <label>
                Last Name:
                <input name="lastName" type="text" value="<%= (idxs != null) ? DB.getById(idx).getLastName() : ""%>">
            </label>
            <br>
            <label>
                <%
                    String data = "";
                    try {
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        data = format.format(DB.getById(idx).getBirthDate());
                    } catch (Exception e) {
                    }
                %>
                Birth date:
                <input type="date" name="birthday" value="<%=data%>">
            </label>
            <br>
            <label>
                Salary: 
                <input name="salary" type="text" value="<%= (idxs != null) ? DB.getById(idx).getSalary() : ""%>">
            </label>
            <br>
            <input class="btn" type="submit" value="save">


            <% if (idxs != null) {%>
            <br>
            <input name="idxt" type="hidden" value="<%=idx%>">
            <%}%>

        </form>
        <br>

        <a href="index.jsp">Cancel</a>

    </body>
</html>
