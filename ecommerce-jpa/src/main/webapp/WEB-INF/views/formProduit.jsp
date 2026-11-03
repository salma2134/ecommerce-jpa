
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/views/header.jsp" />

<h2>
    ${empty produit ? 'Nouveau produit' : 'Modifier le produit'}
</h2>

<form action="${pageContext.request.contextPath}/ProduitServlet"
      method="post" class="bloc">

    <input type="hidden" name="action"
           value="${empty produit ? 'ajouter' : 'modifier'}">

    <c:if test="${not empty produit}">
        <input type="hidden" name="id" value="${produit.id}">
    </c:if>

    <label>Désignation</label>
    <input type="text" name="designation"
           value="${produit.designation}" required>

    <label>Description</label>
    <textarea name="description">${produit.description}</textarea>

    <label>Prix</label>
    <input type="number" name="prix" step="0.01" min="0"
           value="${produit.prix}" required>

    <label>Stock</label>
    <input type="number" name="stock" min="0"
           value="${produit.stock}" required>

    <label>Photo</label>
    <input type="text" name="photo" value="${produit.photo}">

    <label>Catégorie</label>
    <select name="categorieId" required>
        <c:forEach var="c" items="${categories}">
            <option value="${c.id}"
                ${not empty produit and produit.categorie.id == c.id ? 'selected' : ''}>
                ${c.nom}
            </option>
        </c:forEach>
    </select>

    <button type="submit" class="btn">Enregistrer</button>

    <a href="${pageContext.request.contextPath}/ProduitServlet?action=lister">
        Annuler
    </a>
</form>

<jsp:include page="/WEB-INF/views/footer.jsp" />
