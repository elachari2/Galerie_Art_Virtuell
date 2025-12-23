package org.example.models;

public class LignePanier {
    private Oeuvre oeuvre;
    private int quantite;
    private double prixUnitaire;
    
    // Constructeurs
    public LignePanier() {
    }
    
    public LignePanier(Oeuvre oeuvre, int quantite, double prixUnitaire) {
        this.oeuvre = oeuvre;
        this.quantite = quantite;
        this.prixUnitaire = prixUnitaire;
    }
    
    // Getters et Setters
    public Oeuvre getOeuvre() {
        return oeuvre;
    }
    
    public void setOeuvre(Oeuvre oeuvre) {
        this.oeuvre = oeuvre;
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

