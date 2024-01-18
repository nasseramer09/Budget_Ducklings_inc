<%--
  Created by IntelliJ IDEA.
  User: nasseramer
  Date: 2024-01-17
  Time: 11:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> Invoice Page </title>
</head>
<body>
<h1> Välkommen <%=session.getAttribute("userName")%> </h1>
<h2>Samtliga betalningar</h2>

<tr><table border="1">

         <th> Id </th>
         <th> Title </th>
         <th> Kategori </th>
         <th> Beskrivning
         <th> Pris </th>
         <th> Datum </th>
     </    <c:forEach var="payment" items="${paymentDetails}">
         <tr>
              <td> ${payment.id} </td>
              <td> ${payment.title} </td>
              <td> ${payment.kategori}  </td>
              <td> ${payment.beskrivning} </td>
              <td> ${payment.pris}  </td>
              <td> ${payment.datum}  </td>
          </tr>
     </c:forEach>


</table>
</body>
</html>
