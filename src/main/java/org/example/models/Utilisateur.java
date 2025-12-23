package org.example.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Utilisateur {
    public enum Role {
        CLIENT, ADMIN
    }
    
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private String telephone;
    private String adresse;
    private LocalDate dateInscription;
    private Role role;
    
    // Relations
    private List<Commande> commandes = new ArrayList<>();
    private List<Favoris> favoris = new ArrayList<>();
    private Panier panier;
    
    // Constructeurs
    public Utilisateur() {
        this.panier = new Panier(this);
    }
    
    public Utilisateur(int id, String nom, String prenom, String email, String motDePasse, 
                      String telephone, String adresse, Role role) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.telephone = telephone;
        this.adresse = adresse;
        this.dateInscription = LocalDate.now();
        this.role = role;
        this.panier = new Panier(this);
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
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getMotDePasse() {
        return motDePasse;
    }
    
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
    
    public String getTelephone() {
        return telephone;
    }
    
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    
    public String getAdresse() {
        return adresse;
    }
    
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    
    public LocalDate getDateInscription() {
        return dateInscription;
    }
    
    public void setDateInscription(LocalDate dateInscription) {
        this.dateInscription = dateInscription;
    }
    
    public Role getRole() {
        return role;
    }
    
    public void setRole(Role role) {
        this.role = role;
    }
    
    public List<Commande> getCommandes() {
        return commandes;
    }
    
    public void setCommandes(List<Commande> commandes) {
        this.commandes = commandes;
    }
    
    public List<Favoris> getFavoris() {
        return favoris;
    }
    
    public void setFavoris(List<Favoris> favoris) {
        this.favoris = favoris;
    }
    
    public Panier getPanier() {
        return panier;
    }
    
    public void setPanier(Panier panier) {
        this.panier = panier;
    }
    
    // Méthodes utilitaires
    public String getNomComplet() {
        return prenom + " " + nom;
    }
    
    public boolean isAdmin() {
        return role == Role.ADMIN;
    }
    
    public boolean isClient() {
        return role == Role.CLIENT;
    }
    
    public void ajouterCommande(Commande commande) {
        if (!commandes.contains(commande)) {
            commandes.add(commande);
            commande.setClient(this);
        }
    }
    
    @Override
    public String toString() {
        return getNomComplet() + " (" + email + ")";
    }
}

