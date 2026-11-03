

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/views/header.jsp" />

<h2>Catégories</h2>

<c:if test="${not empty erreur}">
    <p class="erreur">${erreur}</p>
</c:if>

<form action="${pageContext.request.contextPath}/CategorieServlet"
      method="post" class="bloc">

    <input type="hidden" name="action" value="ajouter">

    <input type="text" name="nom"
           placeholder="Nom de la catégorie" required>

    <input type="text" name="description"
           placeholder="Description">

    <button type="submit" class="btn">Ajouter</button>
</form>

<table>
    <tr>
        <th>ID</th>
        <th>Nom</th>
        <th>Description</th>
        <th>Action</th>
    </tr>

    <c:forEach var="c" items="${categories}">
        <tr>
            <td>${c.id}</td>
            <td>${c.nom}</td>
            <td>${c.description}</td>
            <td>
                <a class="btn-delete"
                   href="${pageContext.request.contextPath}/CategorieServlet?action=supprimer&id=${c.id}">
                    Supprimer
                </a>
            </td>
        </tr>
    </c:forEach>
</table>

<jsp:include page="/WEB-INF/views/footer.jsp" />
