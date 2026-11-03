
package com.fstt.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
public class Panier implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation = new Date();

    @OneToOne
    private Utilisateur utilisateur;

    @OneToMany(mappedBy = "panier", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LignePanier> lignes = new ArrayList<>();

    public Panier() {}

    public void ajouterProduit(Produit p, int quantite) {
        for (LignePanier ligne : lignes) {
            if (ligne.getProduit().getId().equals(p.getId())) {
                ligne.setQuantite(ligne.getQuantite() + quantite);
                return;
            }
        }

        LignePanier ligne = new LignePanier(p, quantite);
        ligne.setPanier(this);
        lignes.add(ligne);
    }

    public void retirerProduit(Long produitId) {
        lignes.removeIf(ligne ->
            ligne.getProduit().getId().equals(produitId)
        );
    }

    public double calculerTotal() {
        double total = 0;

        for (LignePanier ligne : lignes) {
            total += ligne.getSousTotal();
        }

        return total;
    }

    public void vider() {
        lignes.clear();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Utilisateur getUtiisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public List<LignePanier> getLignes() {
        return lignes;
    }

    public void setLignes(List<LignePanier> lignes) {
        this.lignes = lignes;
    }
}
