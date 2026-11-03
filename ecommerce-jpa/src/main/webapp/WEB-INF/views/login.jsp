
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/views/header.jsp" />

<h2>Connexion</h2>

<c:if test="${not empty erreur}">
    <p class="erreur">${erreur}</p>
</c:if>

<form action="${pageContext.request.contextPath}/UtilisateurServlet"
      method="post" class="bloc">

    <input type="hidden" name="action" value="login">

    <input type="email" name="email" placeholder="Email" required>

    <input type="password" name="motDePasse"
           placeholder="Mot de passe" required>

    <button type="submit" class="btn">Se connecter</button>
</form>

<p>
    Pas de compte ?
    <a href="${pageContext.request.contextPath}/UtilisateurServlet?action=formInscription">
        Inscrivez-vous
    </a>
</p>

<jsp:include page="/WEB-INF/views/footer.jsp" />
