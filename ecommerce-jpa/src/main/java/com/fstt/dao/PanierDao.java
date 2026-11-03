package com.fstt.dao;

import com.fstt.model.Panier;

public interface PanierDao {
    Panier enregistrer(Panier p);
    Panier trouver(Long id);
    Panier trouverParUtilisateur(Long internauteId);
}