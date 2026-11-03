package com.fstt.dao;

import java.util.List;
import com.fstt.model.Categorie;

public interface CategorieDao {
    void ajouter(Categorie c);
    
    void modifier(Categorie c);
    void supprimer(Long id);
    Categorie trouver(Long id);
    List<Categorie> lister();
}