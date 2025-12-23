package org.example.models;

public class LigneCommande {
    private int id;
    private int quantite;
    private double prixUnitaire;
    
    // Relations
    private Commande commande;
    private Oeuvre oeuvre;
    
    // Constructeurs
    public LigneCommande() {
    }
    
    public LigneCommande(int id, Oeuvre oeuvre, int quantite, double prixUnitaire) {
        this.id = id;
        this.oeuvre = oeuvre;
        this.quantite = quantite;
        this.prixUnitaire = prixUnitaire;
    }
    
    // Getters et Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getQuantite() {
        return quantite;
    }
    
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
    
    public double getPrixUnitaire() {
        return prixUnitaire;
    }
    
    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }
    
    public Commande getCommande() {
        return commande;
    }
    
    public void setCommande(Commande commande) {
        this.commande = commande;
    }
    
    public Oeuvre getOeuvre() {
        return oeuvre;
    }
    
    public void setOeuvre(Oeuvre oeuvre) {
        this.oeuvre = oeuvre;
    }
    
    // Méthodes
    public double getSousTotal() {
        return prixUnitaire * quantite;
    }
    
    public String getSousTotalFormate() {
        return String.format("%.2f MAD", getSousTotal());
    }
    
    @Override
    public String toString() {
        return oeuvre.getTitre() + " x" + quantite + " = " + getSousTotalFormate();
    }
}

