
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/WEB-INF/views/header.jsp" />

<h2>${produit.designation}</h2>

<p><b>Catégorie :</b> ${produit.categorie.nom}</p>

<p><b>Description :</b> ${produit.description}</p>

<p>
    <b>Prix :</b>
    <fmt:formatNumber value="${produit.prix}" minFractionDigits="2" /> DH
</p>

<p><b>Stock :</b> ${produit.stock}</p>

<a class="btn"
   href="${pageContext.request.contextPath}/PanierServlet?action=ajouterProduit&id=${produit.id}">
    Ajouter au panier
</a>

<a href="${pageContext.request.contextPath}/ProduitServlet?action=lister">
    Retour
</a>

<jsp:include page="/WEB-INF/views/footer.jsp" />
