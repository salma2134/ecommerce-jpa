
package com.fstt.controller;

import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fstt.dao.UtilisateurDao;
import com.fstt.model.Utilisateur;

@WebServlet("/UtilisateurServlet")
public class UtilisateurServlet extends HttpServlet {

    @Inject
    private UtilisateurDao utilisateurDao;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            action = "formLogin";
        }

        if (action.equals("formInscription")) {

            request.getRequestDispatcher("/WEB-INF/views/inscription.jsp")
                   .forward(request, response);

        } else if (action.equals("inscrire")) {

            String email = request.getParameter("email");

            if (utilisateurDao.trouverParEmail(email) != null) {

                request.setAttribute("erreur", "Cet email est déjà utilisé.");
                request.getRequestDispatcher("/WEB-INF/views/inscription.jsp")
                       .forward(request, response);

            } else {

                Utilisateur i = new Utilisateur();

                i.setNom(request.getParameter("nom"));
                i.setPrenom(request.getParameter("prenom"));
                i.setEmail(email);
                i.setMotDePasse(request.getParameter("motDePasse"));
                i.setAdresse(request.getParameter("adresse"));

                utilisateurDao.ajouter(i);

                response.sendRedirect("UtilisateurServlet?action=formLogin");
            }

        } else if (action.equals("formLogin")) {

            request.getRequestDispatcher("/WEB-INF/views/login.jsp")
                   .forward(request, response);

        } else if (action.equals("login")) {

            String email = request.getParameter("email");
            String motDePasse = request.getParameter("motDePasse");

            Utilisateur i = utilisateurDao.authentifier(email, motDePasse);

            if (i == null) {

                request.setAttribute("erreur", "Email ou mot de passe incorrect.");
                request.getRequestDispatcher("/WEB-INF/views/login.jsp")
                       .forward(request, response);

            } else {

                HttpSession session = request.getSession();

                session.setAttribute("userId", i.getId());
                session.setAttribute("userNom", i.getPrenom() + " " + i.getNom());

                response.sendRedirect("ProduitServlet?action=lister");
            }

        } else if (action.equals("logout")) {

            HttpSession session = request.getSession(false);

            if (session != null) {
                session.invalidate();
            }

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
