<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Test Framework</title>
</head>
<body>
    <% System.out.println("[DEBUG] index.jsp est exécuté"); %>
    <h1>Test Framework</h1>
    <p>Cette page est générée par index.jsp</p>
    <hr>
    <p>Informations de debug :</p>
    <ul>
        <li>RequestURI: <%= request.getRequestURI() %></li>
        <li>ContextPath: <%= request.getContextPath() %></li>
        <li>ServletPath: <%= request.getServletPath() %></li>
    </ul>
</body>
</html>
