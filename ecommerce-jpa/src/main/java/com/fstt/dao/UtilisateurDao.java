package com.fstt.dao;

import java.util.List;
import com.fstt.model.Utilisateur;

public interface UtilisateurDao {
    void ajouter(Utilisateur i);
    void modifier(Utilisateur i);
    void supprimer(Long id);
    Utilisateur trouver(Long id);
    List<Utilisateur> lister();
    Utilisateur authentifier(String email, String motDePasse);
    Utilisateur trouverParEmail(String email);
}