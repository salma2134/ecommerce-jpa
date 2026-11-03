
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/WEB-INF/views/header.jsp" />

<h2>Vitrine</h2>

<div class="cats">

    <a href="${pageContext.request.contextPath}/ProduitServlet?action=lister">
        Tous
    </a>

    <c:forEach var="c" items="${categories}">
        <a href="${pageContext.request.contextPath}/ProduitServlet?action=listerParCategorie&categorieId=${c.id}">
            ${c.nom}
        </a>
    </c:forEach>

</div>

<br>

<c:if test="${empty produits}">
    <p>Aucun produit disponible.</p>
</c:if>

<c:if test="${not empty produits}">

    <table>
        <tr>
            <th>Désignation</th>
            <th>Prix</th>
            <th>Stock</th>
            <th>Catégorie</th>
            <th>Actions</th>
        </tr>

        <c:forEach var="p" items="${produits}">
            <tr>
                <td>
                    <a href="${pageContext.request.contextPath}/ProduitServlet?action=detail&id=${p.id}">
                        ${p.designation}
                    </a>
                </td>

                <td>
                    <fmt:formatNumber value="${p.prix}"
                                      minFractionDigits="2" /> DH
                </td>

                <td>${p.stock}</td>

                <td>${p.categorie.nom}</td>

                <td>
                    <a href="${pageContext.request.contextPath}/PanierServlet?action=ajouterProduit&id=${p.id}">
                        Ajouter au panier
                    </a>

                    |

                    <a href="${pageContext.request.contextPath}/ProduitServlet?action=formModifier&id=${p.id}">
                        Modifier
                    </a>

                    |

                    <a class="btn-delete"
                       href="${pageContext.request.contextPath}/ProduitServlet?action=supprimer&id=${p.id}"
                       onclick="return confirmer()">
                        Supprimer
                    </a>
                </td>
            </tr>
        </c:forEach>
    </table>

</c:if>

<jsp:include page="/WEB-INF/views/footer.jsp" />
