
package com.fstt.dao;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;

import com.fstt.model.Utilisateur;
import com.fstt.model.Panier;
import com.fstt.util.JPAUtil;

@ApplicationScoped
public class UtilisateurDaoImpl implements UtilisateurDao {

    @Override
    public void ajouter(Utilisateur i) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();

        // Créer un panier pour le nouvel utilisateur
        if (i.getPanier() == null) {
            Panier p = new Panier();
            p.setUtilisateur(i);
            i.setPanier(p);
        }

        em.persist(i);
        em.getTransaction().commit();

        em.close();
    }

    @Override
    public void modifier(Utilisateur i) {
        EntityManager em = JPAUtil.getEntityManager();

        em.getTransaction().begin();
        em.merge(i);
        em.getTransaction().commit();

        em.close();
    }

    @Override
    public void supprimer(Long id) {
        EntityManager em = JPAUtil.getEntityManager();

        em.getTransaction().begin();

        Utilisateur i = em.find(Utilisateur.class, id);

        if (i != null) {
            em.remove(i);
        }

        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Utilisateur trouver(Long id) {
        EntityManager em = JPAUtil.getEntityManager();

        Utilisateur i = em.find(Utilisateur.class, id);

        em.close();
        return i;
    }

    @Override
    public List<Utilisateur> lister() {
        EntityManager em = JPAUtil.getEntityManager();

        List<Utilisateur> Utilisateurs = em.createQuery(
            "SELECT i FROM Utilisateur i", Utilisateur.class
        ).getResultList();

        em.close();
        return Utilisateurs;
    }

    @Override
    public Utilisateur authentifier(String email, String motDePasse) {
        EntityManager em = JPAUtil.getEntityManager();

        List<Utilisateur> resultat = em.createQuery(
            "SELECT i FROM Utilisateur i WHERE i.email = :e AND i.motDePasse = :m",
            Utilisateur.class
        )
        .setParameter("e", email)
        .setParameter("m", motDePasse)
        .getResultList();

        em.close();

        if (resultat.isEmpty()) {
            return null;
        }

        return resultat.get(0);
    }

    @Override
    public Utilisateur trouverParEmail(String email) {
        EntityManager em = JPAUtil.getEntityManager();

        List<Utilisateur> resultat = em.createQuery(
            "SELECT i FROM Utilisateur i WHERE i.email = :e",
            Utilisateur.class
        )
        .setParameter("e", email)
        .getResultList();

        em.close();

        if (resultat.isEmpty()) {
            return null;
        }

        return resultat.get(0);
    }
}
