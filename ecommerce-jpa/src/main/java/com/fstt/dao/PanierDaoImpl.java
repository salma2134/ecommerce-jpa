
package com.fstt.dao;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;

import com.fstt.model.Panier;
import com.fstt.model.LignePanier;
import com.fstt.util.JPAUtil;

@ApplicationScoped
public class PanierDaoImpl implements PanierDao {

    @Override
    public Panier enregistrer(Panier p) {
        EntityManager em = JPAUtil.getEntityManager();

        em.getTransaction().begin();

        Panier panier = em.merge(p);

        em.getTransaction().commit();
        em.close();

        return panier;
    }

    @Override
    public Panier trouver(Long id) {
        EntityManager em = JPAUtil.getEntityManager();

        Panier p = em.find(Panier.class, id);

        if (p != null) {
            p.getLignes().size();
        }

        em.close();

        return p;
    }

    @Override
    public Panier trouverParUtilisateur(Long utilisateurId) {
        EntityManager em = JPAUtil.getEntityManager();

        List<Panier> resultat = em.createQuery(
            "SELECT p FROM Panier p WHERE p.utilisateur.id = :id",
            Panier.class
        )
        .setParameter("id", utilisateurId)
        .getResultList();

        if (resultat.isEmpty()) {
            em.close();
            return null;
        }

        Panier p = resultat.get(0);

        p.getLignes().size();

        for (LignePanier ligne : p.getLignes()) {
            ligne.getProduit().getDesignation();
        }

        em.close();

        return p;
    }
}
