
package com.fstt.controller;

import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fstt.dao.CategorieDao;
import com.fstt.model.Categorie;

@WebServlet("/CategorieServlet")
public class CategorieServlet extends HttpServlet {

    @Inject
    private CategorieDao categorieDao;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            action = "lister";
        }

        if (action.equals("lister")) {

            request.setAttribute("categories", categorieDao.lister());
            request.getRequestDispatcher("/WEB-INF/views/categories.jsp")
                   .forward(request, response);

        } else if (action.equals("ajouter")) {

            String nom = request.getParameter("nom");
            String description = request.getParameter("description");

            Categorie categorie = new Categorie(nom, description);
            categorieDao.ajouter(categorie);

            response.sendRedirect("CategorieServlet?action=lister");

        } else if (action.equals("supprimer")) {

            Long id = Long.parseLong(request.getParameter("id"));
            categorieDao.supprimer(id);

            response.sendRedirect("CategorieServlet?action=lister");

        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }
}
