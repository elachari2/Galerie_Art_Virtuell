package org.example.models;

import java.util.ArrayList;
import java.util.List;

public class Categorie {
    private int id;
    private String nom;
    private String description;
    

    private List<Oeuvre> oeuvres = new ArrayList<>();
    
    // Constructeurs
    public Categorie() {
    }
    
    public Categorie(int id, String nom, String description) {
        this.id = id;
        this.nom = nom;
        this.description = description;
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
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public List<Oeuvre> getOeuvres() {
        return oeuvres;
    }
    
    public void setOeuvres(List<Oeuvre> oeuvres) {
        this.oeuvres = oeuvres;
    }
    
    public void ajouterOeuvre(Oeuvre oeuvre) {
        if (!oeuvres.contains(oeuvre)) {
            oeuvres.add(oeuvre);
            oeuvre.setCategorie(this);
        }
    }
    
    @Override
    public String toString() {
        return nom;
    }
}

