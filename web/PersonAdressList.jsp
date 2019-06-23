<%-- 
    Document   : PersonAdressList
    Created on : 15-Jun-2019, 16:06:04
    Author     : adoma
--%>

<%@page import="lt.bit.db.DB"%>
<%@page import="lt.bit.data.Address"%>
<%@page import="lt.bit.data.Person"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="styles.css">
    </head>
    <body>
        <h1>Person address list!</h1>
        <%  String personIdStr = request.getParameter("personId");
            int personId = -1;

            try {
                personId = Integer.parseInt(personIdStr);
            } catch (Exception e) {
                }%>
        <table>
            <tr>
                <th>Address</th>
                <th>City</th>
                <th>Post code</th>

            </tr>
            <% if (personId > 0) {
                       for (Address elem : DB.getPersonAddresses(personId)) {%>
            <tr>
                <td><%=DB.getAddressById(elem.getId()).getAddress()%> </td>
                <td><%=DB.getAddressById(elem.getId()).getCity()%></td>
                <td><%=DB.getAddressById(elem.getId()).getPostalCode()%></td>
                <td><a href="PersonAddressEdit.jsp?addressId=<%=elem.getId()%>&idx=<%=personId%>">Edit</a></td>
                <td><a href="RemovePersonAddressServlet?idx=<%=elem.getId()%>&personId=<%=DB.getByAddress(elem).getId()%>">Delete</a></td>
            </tr>  
            <%}
                }%>

        </table>
        <a href="PersonAddressEdit.jsp?idx=<%=personId%>">Add</a>
        <a href='index.jsp'>Back</a>

    </body>
</html>
