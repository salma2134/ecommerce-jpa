
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/WEB-INF/views/header.jsp" />

<h2>Mon panier</h2>

<c:if test="${empty panier.lignes}">
    <p>Votre panier est vide.</p>
</c:if>

<c:if test="${not empty panier.lignes}">

    <table>
        <tr>
            <th>Produit</th>
            <th>Prix unitaire</th>
            <th>Quantité</th>
            <th>Sous-total</th>
            <th>Action</th>
        </tr>

        <c:forEach var="l" items="${panier.lignes}">
            <tr>
                <td>${l.produit.designation}</td>

                <td>
                    <fmt:formatNumber value="${l.produit.prix}"
                                      minFractionDigits="2" /> DH
                </td>

                <td>
                    <form action="${pageContext.request.contextPath}/PanierServlet"
                          method="post">

                        <input type="hidden" name="action"
                               value="modifierQuantite">

                        <input type="hidden" name="id"
                               value="${l.produit.id}">

                        <input type="number" name="quantite"
                               value="${l.quantite}" min="0">

                        <button type="submit" class="btn">OK</button>
                    </form>
                </td>

                <td>
                    <fmt:formatNumber value="${l.sousTotal}"
                                      minFractionDigits="2" /> DH
                </td>

                <td>
                    <a class="btn-delete"
                       href="${pageContext.request.contextPath}/PanierServlet?action=retirerProduit&id=${l.produit.id}"
                       onclick="return confirmer()">
                        Retirer
                    </a>
                </td>
            </tr>
        </c:forEach>

        <tr>
            <th colspan="3">Total</th>
            <th colspan="2">
                <fmt:formatNumber value="${total}"
                                  minFractionDigits="2" /> DH
            </th>
        </tr>
    </table>

    <br>

    <a class="btn"
       href="${pageContext.request.contextPath}/PanierServlet?action=vider"
       onclick="return confirmer()">
        Vider le panier
    </a>

</c:if>

<p>
    <a href="${pageContext.request.contextPath}/ProduitServlet?action=lister">
        Continuer mes achats
    </a>
</p>

<jsp:include page="/WEB-INF/views/footer.jsp" />
