package com.fstt.util;

import javax.persistence.EntityManager;

public class TestJPA {
    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();
        System.out.println("Connexion JPA OK : " + em.isOpen());
        em.close();
        JPAUtil.close();
    }
}