package org.example.models;

import java.time.LocalDateTime;

public class Favoris {
    private int id;
    private LocalDateTime dateAjout;
    
    // Relations
    private Utilisateur utilisateur;
    private Oeuvre oeuvre;
    
    // Constructeurs
    public Favoris() {
        this.dateAjout = LocalDateTime.now();
    }
    
    public Favoris(int id, Utilisateur utilisateur, Oeuvre oeuvre) {
        this.id = id;
        this.utilisateur = utilisateur;
        this.oeuvre = oeuvre;
        this.dateAjout = LocalDateTime.now();
    }
    
    // Getters et Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public LocalDateTime getDateAjout() {
        return dateAjout;
    }
    
    public void setDateAjout(LocalDateTime dateAjout) {
        this.dateAjout = dateAjout;
    }
    
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }
    
    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
    
    public Oeuvre getOeuvre() {
        return oeuvre;
    }
    
    public void setOeuvre(Oeuvre oeuvre) {
        this.oeuvre = oeuvre;
    }
    
    @Override
    public String toString() {
        return "Favoris: " + oeuvre.getTitre() + " - " + utilisateur.getNomComplet();
    }
}

