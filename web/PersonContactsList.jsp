<%-- 
    Document   : PersonContactsList
    Created on : 16-Jun-2019, 12:33:23
    Author     : adoma
--%>

<%@page import="lt.bit.db.DB"%>
<%@page import="lt.bit.data.Contact"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="styles.css">

    </head>
    <body>
        <h1>Person contacts list!</h1>

        <table>
            <tr>
                <th>Name</th>
                <th>Type</th>
            </tr>

            <%  String personIdStr = request.getParameter("personId");
                int personId = -1;

                try {
                    personId = Integer.parseInt(personIdStr);
                } catch (Exception e) {
                }
                if (personId > 0) {
                    for (Contact elem : DB.getPersonContacts(personId)) {%>
            <tr>
                <td><%=DB.getContactById(elem.getId()).getContact() %> </td>
                <td><%=DB.getContactById(elem.getId()).getType()%> </td>
                <td><a href="PersonContactEdit.jsp?ContactId=<%=elem.getId()%>&idx=<%=personId%>">Edit</a></td>
                <td><a href="RemovePersonContactServlet?idx=<%=elem.getId()%>&personId=<%=DB.getByContact(elem).getId()%>">Delete</a></td>
            </tr>  
            <%}
                }%>

        </table>
        <a href="PersonContactEdit.jsp?idx=<%=personId%>">Add</a>
        <a href='index.jsp'>Back</a>
    </body>
</html>
