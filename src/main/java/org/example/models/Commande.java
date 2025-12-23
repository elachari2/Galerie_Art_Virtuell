package org.example.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Commande {
    public enum Statut {
        EN_ATTENTE, VALIDEE, EN_LIVRAISON, LIVREE, ANNULEE
    }
    
    private int id;
    private LocalDateTime dateCommande;
    private Statut statut;
    private double montantTotal;
    private String adresseLivraison;
    private String modePaiement;
    
    // Relations
    private Utilisateur client;
    private List<LigneCommande> lignesCommande = new ArrayList<>();
    
    // Constructeurs
    public Commande() {
        this.dateCommande = LocalDateTime.now();
        this.statut = Statut.EN_ATTENTE;
    }
    
    public Commande(int id, Utilisateur client, String adresseLivraison, String modePaiement) {
        this.id = id;
        this.client = client;
        this.adresseLivraison = adresseLivraison;
        this.modePaiement = modePaiement;
        this.dateCommande = LocalDateTime.now();
        this.statut = Statut.EN_ATTENTE;
        this.montantTotal = 0.0;
    }
    
    // Getters et Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public LocalDateTime getDateCommande() {
        return dateCommande;
    }
    
    public void setDateCommande(LocalDateTime dateCommande) {
        this.dateCommande = dateCommande;
    }
    
    public Statut getStatut() {
        return statut;
    }
    
    public void setStatut(Statut statut) {
        this.statut = statut;
    }
    
    public double getMontantTotal() {
        return montantTotal;
    }
    
    public void setMontantTotal(double montantTotal) {
        this.montantTotal = montantTotal;
    }
    
    public String getAdresseLivraison() {
        return adresseLivraison;
    }
    
    public void setAdresseLivraison(String adresseLivraison) {
        this.adresseLivraison = adresseLivraison;
    }
    
    public String getModePaiement() {
        return modePaiement;
    }
    
    public void setModePaiement(String modePaiement) {
        this.modePaiement = modePaiement;
    }
    
    public Utilisateur getClient() {
        return client;
    }
    
    public void setClient(Utilisateur client) {
        this.client = client;
    }
    
    public List<LigneCommande> getLignesCommande() {
        return lignesCommande;
    }
    
    public void setLignesCommande(List<LigneCommande> lignesCommande) {
        this.lignesCommande = lignesCommande;
    }
    
    // Méthodes utilitaires
    public void ajouterLigne(LigneCommande ligne) {
        if (!lignesCommande.contains(ligne)) {
            lignesCommande.add(ligne);
            ligne.setCommande(this);
            calculerMontantTotal();
        }
    }
    
    public void calculerMontantTotal() {
        montantTotal = lignesCommande.stream()
                .mapToDouble(l -> l.getPrixUnitaire() * l.getQuantite())
                .sum();
    }
    
    public String getMontantFormate() {
        return String.format("%.2f MAD", montantTotal);
    }
    
    @Override
    public String toString() {
        return "Commande #" + id + " - " + getMontantFormate() + " (" + statut + ")";
    }
}

