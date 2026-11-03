
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>E-Commerce FSTT</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            background: #f5f5f5;
        }

        nav {
            background: #2c3e50;
            padding: 12px 20px;
        }

        nav a {
            color: white;
            text-decoration: none;
            margin-right: 18px;
        }

        .right {
            float: right;
        }

        .container {
            max-width: 1000px;
            margin: auto;
            padding: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            background: white;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }

        th {
            background: #ecf0f1;
        }

        .erreur {
            color: #c0392b;
        }

        form.bloc input,
        form.bloc select,
        form.bloc textarea {
            display: block;
            width: 300px;
            padding: 6px;
            margin: 6px 0 12px;
        }

        .btn {
            background: #2980b9;
            color: white;
            padding: 6px 12px;
            border: none;
            text-decoration: none;
            cursor: pointer;
        }

        .btn-delete {
            color: #c0392b;
        }

        .cats a {
            margin-right: 10px;
        }
    </style>

    <script>
        function confirmer() {
            return confirm("Voulez-vous vraiment supprimer ?");
        }
    </script>
</head>

<body>

<nav>
    <a href="${pageContext.request.contextPath}/ProduitServlet?action=lister">
        Vitrine
    </a>

    <a href="${pageContext.request.contextPath}/CategorieServlet?action=lister">
        Catégories
    </a>

    <a href="${pageContext.request.contextPath}/ProduitServlet?action=formAjouter">
        Nouveau produit
    </a>

    <span class="right">
        <c:choose>
            <c:when test="${not empty sessionScope.userId}">
                <a href="${pageContext.request.contextPath}/PanierServlet?action=afficher">
                    Mon panier
                </a>

                <span style="color:white">${sessionScope.userNom}</span>

                <a href="${pageContext.request.contextPath}/UtilisateurServlet?action=logout">
                    Déconnexion
                </a>
            </c:when>

            <c:otherwise>
                <a href="${pageContext.request.contextPath}/UtilisateurServlet?action=formLogin">
                    Connexion
                </a>

                <a href="${pageContext.request.contextPath}/UtilisateurServlet?action=formInscription">
                    Inscription
                </a>
            </c:otherwise>
        </c:choose>
    </span>
</nav>

<div class="container">
