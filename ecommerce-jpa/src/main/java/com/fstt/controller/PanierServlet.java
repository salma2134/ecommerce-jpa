
package com.fstt.controller;

import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fstt.dao.PanierDao;
import com.fstt.dao.ProduitDao;
import com.fstt.model.Panier;
import com.fstt.model.Produit;
import com.fstt.model.LignePanier;

@WebServlet("/PanierServlet")
public class PanierServlet extends HttpServlet {

    @Inject
    private PanierDao panierDao;

    @Inject
    private ProduitDao produitDao;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("InternauteServlet?action=formLogin");
            return;
        }

        Long userId = (Long) session.getAttribute("userId");

        String action = request.getParameter("action");

        if (action == null) {
            action = "afficher";
        }

        Panier panier = panierDao.trouverParUtilisateur(userId);

        if (panier == null) {
            response.sendError(500, "Panier introuvable");
            return;
        }

        if (action.equals("afficher")) {

            request.setAttribute("panier", panier);
            request.setAttribute("total", panier.calculerTotal());

            request.getRequestDispatcher("/WEB-INF/views/panier.jsp")
                   .forward(request, response);

        } else if (action.equals("ajouterProduit")) {

            Long id = Long.parseLong(request.getParameter("id"));

            Produit produit = produitDao.trouver(id);

            if (produit != null) {
                panier.ajouterProduit(produit, 1);
                panierDao.enregistrer(panier);
            }

            response.sendRedirect("PanierServlet?action=afficher");

        } else if (action.equals("retirerProduit")) {

            Long id = Long.parseLong(request.getParameter("id"));

            panier.retirerProduit(id);
            panierDao.enregistrer(panier);

            response.sendRedirect("PanierServlet?action=afficher");

        } else if (action.equals("modifierQuantite")) {

            Long id = Long.parseLong(request.getParameter("id"));
            int quantite = Integer.parseInt(request.getParameter("quantite"));

            if (quantite <= 0) {

                panier.retirerProduit(id);

            } else {

                for (LignePanier ligne : panier.getLignes()) {

                    if (ligne.getProduit().getId().equals(id)) {
                        ligne.setQuantite(quantite);
                    }
                }
            }

            panierDao.enregistrer(panier);

            response.sendRedirect("PanierServlet?action=afficher");

        } else if (action.equals("vider")) {

            panier.vider();
            panierDao.enregistrer(panier);

            response.sendRedirect("PanierServlet?action=afficher");

        } else {

            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        doGet(request, response);
    }
}
