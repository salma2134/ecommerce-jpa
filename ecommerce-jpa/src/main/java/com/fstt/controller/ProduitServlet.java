
package com.fstt.controller;

import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fstt.dao.CategorieDao;
import com.fstt.dao.ProduitDao;
import com.fstt.model.Produit;

@WebServlet("/ProduitServlet")
public class ProduitServlet extends HttpServlet {

    @Inject
    private ProduitDao produitDao;

    @Inject
    private CategorieDao categorieDao;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            action = "lister";
        }

        if (action.equals("lister")) {

            request.setAttribute("produits", produitDao.lister());
            request.setAttribute("categories", categorieDao.lister());

            request.getRequestDispatcher("/WEB-INF/views/produits.jsp")
                   .forward(request, response);

        } else if (action.equals("listerParCategorie")) {

            Long catId = Long.parseLong(request.getParameter("categorieId"));

            request.setAttribute("produits", produitDao.listerParCategorie(catId));
            request.setAttribute("categories", categorieDao.lister());
            request.setAttribute("categorieActive", catId);

            request.getRequestDispatcher("/WEB-INF/views/produits.jsp")
                   .forward(request, response);

        } else if (action.equals("detail")) {

            Long id = Long.parseLong(request.getParameter("id"));

            request.setAttribute("produit", produitDao.trouver(id));

            request.getRequestDispatcher("/WEB-INF/views/detailProduit.jsp")
                   .forward(request, response);

        } else if (action.equals("formAjouter")) {

            request.setAttribute("categories", categorieDao.lister());

            request.getRequestDispatcher("/WEB-INF/views/formProduit.jsp")
                   .forward(request, response);

        } else if (action.equals("ajouter")) {

            Produit p = new Produit();

            p.setDesignation(request.getParameter("designation"));
            p.setDescription(request.getParameter("description"));
            p.setPrix(Double.parseDouble(request.getParameter("prix")));
            p.setStock(Integer.parseInt(request.getParameter("stock")));
            p.setPhoto(request.getParameter("photo"));

            Long catId = Long.parseLong(request.getParameter("categorieId"));
            p.setCategorie(categorieDao.trouver(catId));

            produitDao.ajouter(p);

            response.sendRedirect("ProduitServlet?action=lister");

        } else if (action.equals("formModifier")) {

            Long id = Long.parseLong(request.getParameter("id"));

            request.setAttribute("produit", produitDao.trouver(id));
            request.setAttribute("categories", categorieDao.lister());

            request.getRequestDispatcher("/WEB-INF/views/formProduit.jsp")
                   .forward(request, response);

        } else if (action.equals("modifier")) {

            Long id = Long.parseLong(request.getParameter("id"));

            Produit p = produitDao.trouver(id);

            p.setDesignation(request.getParameter("designation"));
            p.setDescription(request.getParameter("description"));
            p.setPrix(Double.parseDouble(request.getParameter("prix")));
            p.setStock(Integer.parseInt(request.getParameter("stock")));
            p.setPhoto(request.getParameter("photo"));

            Long catId = Long.parseLong(request.getParameter("categorieId"));
            p.setCategorie(categorieDao.trouver(catId));

            produitDao.modifier(p);

            response.sendRedirect("ProduitServlet?action=lister");

        } else if (action.equals("supprimer")) {

            Long id = Long.parseLong(request.getParameter("id"));

            produitDao.supprimer(id);

            response.sendRedirect("ProduitServlet?action=lister");

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
