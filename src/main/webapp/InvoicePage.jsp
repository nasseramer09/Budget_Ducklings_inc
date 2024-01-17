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

<table border="1">
    <tr>
        <th> Id </th>
        <th> Title </th>
        <th> Kategori </th>
        <th> Beskrivning
        <th> Pris </th>
        <th> Datum </th>
    </tr>
   <tr>
       <td>  1 </td>
       <td> Test 1 </td>
       <td> Resor </td>
       <td> Jobb resa till Stockholm </td>
       <td> 500 kr </td>
       <td> 20240115 </td>
   </tr>
</table>
</body>
</html>
