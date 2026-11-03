
package com.fstt.dao;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;

import com.fstt.model.Categorie;
import com.fstt.util.JPAUtil;

@ApplicationScoped
public class CategorieDaoImpl implements CategorieDao {

    @Override
    public void ajouter(Categorie c) {
        EntityManager em = JPAUtil.getEntityManager();

        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();

        em.close();
    }

    @Override
    public void modifier(Categorie c) {
        EntityManager em = JPAUtil.getEntityManager();

        em.getTransaction().begin();
        em.merge(c);
        em.getTransaction().commit();

        em.close();
    }

    @Override
    public void supprimer(Long id) {
        EntityManager em = JPAUtil.getEntityManager();

        em.getTransaction().begin();

        Categorie c = em.find(Categorie.class, id);

        if (c != null) {
            em.remove(c);
        }

        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Categorie trouver(Long id) {
        EntityManager em = JPAUtil.getEntityManager();

        Categorie c = em.find(Categorie.class, id);

        em.close();
        return c;
    }

    @Override
    public List<Categorie> lister() {
        EntityManager em = JPAUtil.getEntityManager();

        List<Categorie> categories = em.createQuery(
            "SELECT c FROM Categorie c", Categorie.class
        ).getResultList();

        em.close();
        return categories;
    }
}
