
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/views/header.jsp" />

<h2>Inscription</h2>

<c:if test="${not empty erreur}">
    <p class="erreur">${erreur}</p>
</c:if>

<form action="${pageContext.request.contextPath}/UtilisateurServlet"
      method="post" class="bloc">

    <input type="hidden" name="action" value="inscrire">

    <input type="text" name="nom" placeholder="Nom" required>

    <input type="text" name="prenom" placeholder="Prénom" required>

    <input type="email" name="email" placeholder="Email" required>

    <input type="password" name="motDePasse"
           placeholder="Mot de passe" required>

    <input type="text" name="adresse" placeholder="Adresse">

    <button type="submit" class="btn">S'inscrire</button>
</form>

<jsp:include page="/WEB-INF/views/footer.jsp" />
