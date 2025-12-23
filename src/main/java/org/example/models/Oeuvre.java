package org.example.models;

public class Oeuvre {
    private int id;
    private String titre;
    private int anneeCreation;
    private String dimensions; // Ex: "73x92 cm"
    private double prix;
    private String cheminImage;
    
    private Artiste artiste;
    
    private Categorie categorie;
    
    // Constructeurs
    public Oeuvre() {
    }
    
    public Oeuvre(int id, String titre, int anneeCreation, String dimensions, 
                  double prix, String cheminImage, Artiste artiste, Categorie categorie) {
        this.id = id;
        this.titre = titre;
        this.anneeCreation = anneeCreation;
        this.dimensions = dimensions;
        this.prix = prix;
        this.cheminImage = cheminImage;
        this.artiste = artiste;
        this.categorie = categorie;
    }
    
    // Getters et Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getTitre() {
        return titre;
    }
    
    public void setTitre(String titre) {
        this.titre = titre;
    }
    
    public int getAnneeCreation() {
        return anneeCreation;
    }
    
    public void setAnneeCreation(int anneeCreation) {
        this.anneeCreation = anneeCreation;
    }
    
    public String getDimensions() {
        return dimensions;
    }
    
    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }
    
    public double getPrix() {
        return prix;
    }
    
    public void setPrix(double prix) {
        this.prix = prix;
    }
    
    public String getCheminImage() {
        return cheminImage;
    }
    
    public void setCheminImage(String cheminImage) {
        this.cheminImage = cheminImage;
    }
    
    public Artiste getArtiste() {
        return artiste;
    }
    
    public void setArtiste(Artiste artiste) {
        this.artiste = artiste;
    }
    
    public Categorie getCategorie() {
        return categorie;
    }
    
    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }
    
    // Méthodes
    public String getPrixFormate() {
        return String.format("%.2f €", prix);
    }
    
    @Override
    public String toString() {
        return titre + " (" + anneeCreation + ")";
    }
}

