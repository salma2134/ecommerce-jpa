
package com.fstt.dao;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;

import com.fstt.model.Produit;
import com.fstt.util.JPAUtil;

@ApplicationScoped
public class ProduitDaoImpl implements ProduitDao {

    @Override
    public void ajouter(Produit p) {
        EntityManager em = JPAUtil.getEntityManager();

        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();

        em.close();
    }

    @Override
    public void modifier(Produit p) {
        EntityManager em = JPAUtil.getEntityManager();

        em.getTransaction().begin();
        em.merge(p);
        em.getTransaction().commit();

        em.close();
    }

    @Override
    public void supprimer(Long id) {
        EntityManager em = JPAUtil.getEntityManager();

        em.getTransaction().begin();

        Produit p = em.find(Produit.class, id);

        if (p != null) {
            em.remove(p);
        }

        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Produit trouver(Long id) {
        EntityManager em = JPAUtil.getEntityManager();

        Produit p = em.find(Produit.class, id);

        em.close();
        return p;
    }

    @Override
    public List<Produit> lister() {
        EntityManager em = JPAUtil.getEntityManager();

        List<Produit> produits = em.createQuery(
            "SELECT p FROM Produit p", Produit.class
        ).getResultList();

        em.close();
        return produits;
    }

    @Override
    public List<Produit> listerParCategorie(Long categorieId) {
        EntityManager em = JPAUtil.getEntityManager();

        List<Produit> produits = em.createQuery(
            "SELECT p FROM Produit p WHERE p.categorie.id = :id",
            Produit.class
        )
        .setParameter("id", categorieId)
        .getResultList();

        em.close();
        return produits;
    }
}
