package org.example.models;

import java.util.ArrayList;
import java.util.List;

public class Panier {
    private int id;
    private Utilisateur utilisateur;
    private List<LignePanier> lignesPanier = new ArrayList<>();
    
    // Constructeurs
    public Panier() {
    }
    
    public Panier(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
    
    // Getters et Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }
    
    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
    
    public List<LignePanier> getLignesPanier() {
        return lignesPanier;
    }
    
    public void setLignesPanier(List<LignePanier> lignesPanier) {
        this.lignesPanier = lignesPanier;
    }
    
    // Méthodes utilitaires
    public void ajouterOeuvre(Oeuvre oeuvre, int quantite) {
        LignePanier ligneExistante = trouverLigne(oeuvre);
        if (ligneExistante != null) {
            ligneExistante.setQuantite(ligneExistante.getQuantite() + quantite);
        } else {
            LignePanier nouvelleLigne = new LignePanier(oeuvre, quantite, oeuvre.getPrix());
            lignesPanier.add(nouvelleLigne);
        }
    }
    
    public void retirerOeuvre(Oeuvre oeuvre) {
        lignesPanier.removeIf(ligne -> ligne.getOeuvre().equals(oeuvre));
    }
    
    public void modifierQuantite(Oeuvre oeuvre, int nouvelleQuantite) {
        LignePanier ligne = trouverLigne(oeuvre);
        if (ligne != null) {
            if (nouvelleQuantite <= 0) {
                retirerOeuvre(oeuvre);
            } else {
                ligne.setQuantite(nouvelleQuantite);
            }
        }
    }
    
    public LignePanier trouverLigne(Oeuvre oeuvre) {
        return lignesPanier.stream()
                .filter(l -> l.getOeuvre().equals(oeuvre))
                .findFirst()
                .orElse(null);
    }
    
    public double getMontantTotal() {
        return lignesPanier.stream()
                .mapToDouble(LignePanier::getSousTotal)
                .sum();
    }
    
    public String getMontantTotalFormate() {
        return String.format("%.2f €", getMontantTotal());
    }
    
    public int getNombreArticles() {
        return lignesPanier.stream()
                .mapToInt(LignePanier::getQuantite)
                .sum();
    }
    
    public void vider() {
        lignesPanier.clear();
    }
    
    public boolean estVide() {
        return lignesPanier.isEmpty();
    }
}

