<%-- 
    Document   : index.jsp
    Created on : 14-Jun-2019, 21:40:53
    Author     : adoma
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="lt.bit.data.Person"%>
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
        <h1>Person list!</h1>

        <table>
            <tr>
                <th>First name</th>
                <th>Last name</th>
                <th>Salary</th>
                <th>Birth date</th>

            </tr>
            <%for (Person elem : DB.getAll()) {%>
            <tr>
                <td><%=elem.getFirstName()%> </td>
                <td><%=elem.getLastName()%></td>
                <td><%=elem.getSalary()%></td>
                <td>                <%
                    String data = "";
                    try {
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        data = format.format(elem.getBirthDate());
                    } catch (Exception e) {
                    }
                %>
                <%= data %>
                </td>
                <td><a href="PersonEdit.jsp?idx=<%= elem.getId() %>">Edit</a></td>
                <td><a href="deletePerson?idx=<%= elem.getId() %>">Delete</a></td>
                <td><a href="PersonAdressList.jsp?personId=<%=  elem.getId() %>">Address List</a></td>
                <td><a href="PersonContactsList.jsp?personId=<%= elem.getId() %>">Contacts List</a></td>
            </tr>  
            <%}%>
        </table>
        
        <a href='PersonEdit.jsp'>Add</a>
    </body>
</html>
