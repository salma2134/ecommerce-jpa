package com.fstt.dao;

import java.util.List;
import com.fstt.model.Produit;

public interface ProduitDao {
    void ajouter(Produit p);
    void modifier(Produit p);
    void supprimer(Long id);
    Produit trouver(Long id);
    List<Produit> lister();
    List<Produit> listerParCategorie(Long categorieId);
}