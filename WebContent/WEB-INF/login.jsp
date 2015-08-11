<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Login Page</title>
</head>

<h2>Authentification</h2>
<br><br>
<div>
Les logins sont de la même forme que les logins Sopra (ex: gphalippou). Pour la première connexion, n'importe quel mot de passe peut être saisi, il sera définitivement enregistré pour les futures connexions.
</div>
<form action="<%= request.getContextPath() %>/j_security_check" method=post>
    <p><strong>Nom d'utilisateur : </strong>
    <input type="text" name="j_username" size="25">
    <p><p><strong>Mot de passe : </strong>
    <input type="password" size="15" name="j_password">
    <p style="color: red;"><c:if test='${Retry != null}'>Mauvais login ou mot de passe</c:if><p>
    <input type="submit" value="OK">
    <input type="reset" value="Réinitialiser">
</form>
</html>