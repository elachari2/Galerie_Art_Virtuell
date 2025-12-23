package org.example.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Artiste {
    private int id;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private String nationalite;
    private String biographie;
    

    private List<Oeuvre> oeuvres = new ArrayList<>();
    
    // Constructeurs
    public Artiste() {
    }
    
    public Artiste(int id, String nom, String prenom, LocalDate dateNaissance, 
                   String nationalite, String biographie) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.nationalite = nationalite;
        this.biographie = biographie;
    }
    
    // Getters et Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public String getPrenom() {
        return prenom;
    }
    
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    public LocalDate getDateNaissance() {
        return dateNaissance;
    }
    
    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
    
    public String getNationalite() {
        return nationalite;
    }
    
    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }
    
    public String getBiographie() {
        return biographie;
    }
    
    public void setBiographie(String biographie) {
        this.biographie = biographie;
    }
    
    public List<Oeuvre> getOeuvres() {
        return oeuvres;
    }
    
    public void setOeuvres(List<Oeuvre> oeuvres) {
        this.oeuvres = oeuvres;
    }
    
    // Méthodes
    public String getNomComplet() {
        return prenom + " " + nom;
    }
    
    public void ajouterOeuvre(Oeuvre oeuvre) {
        if (!oeuvres.contains(oeuvre)) {
            oeuvres.add(oeuvre);
            oeuvre.setArtiste(this);
        }
    }
    
    @Override
    public String toString() {
        return getNomComplet();
    }
}

