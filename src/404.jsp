<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <title>Page Non Trouvée</title>
</head>
<body>
    <h1>404 - Page Non Trouvée</h1>
    <p>La page demandée n'existe pas : ${requestScope.urlPath}</p>
    <p><a href="${pageContext.request.contextPath}/">Retour à l'accueil</a></p>
</body>
</html>
