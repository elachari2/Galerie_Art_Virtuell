package org.example.utils;

import org.example.models.Panier;
import org.example.models.Utilisateur;

public class SessionManager {
    private static SessionManager instance;
    private Utilisateur utilisateurConnecte;
    private Panier panierTemporaire; // Panier temporaire même sans connexion
    
    private SessionManager() {
        panierTemporaire = new Panier();
    }
    
    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }
    
    public void connecter(Utilisateur utilisateur) {
        this.utilisateurConnecte = utilisateur;
        // Si l'utilisateur a un panier, on l'utilise, sinon on garde le panier temporaire
        if (utilisateur != null && utilisateur.getPanier() != null) {
            this.panierTemporaire = utilisateur.getPanier();
        }
    }
    
    public void deconnecter() {
        this.utilisateurConnecte = null;
    }
    
    public Utilisateur getUtilisateurConnecte() {
        return utilisateurConnecte;
    }
    
    public boolean estConnecte() {
        return utilisateurConnecte != null;
    }
    
    public boolean estAdmin() {
        return estConnecte() && utilisateurConnecte.isAdmin();
    }
    
    public boolean estClient() {
        return estConnecte() && utilisateurConnecte.isClient();
    }
    
    public Panier getPanier() {
        if (estConnecte() && utilisateurConnecte.getPanier() != null) {
            return utilisateurConnecte.getPanier();
        }
        return panierTemporaire;
    }
}

