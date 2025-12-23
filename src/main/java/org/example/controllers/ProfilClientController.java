package org.example.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.example.utils.SceneManager;
import org.example.utils.SessionManager;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfilClientController implements Initializable {
    @FXML private TextField fieldNom;
    @FXML private TextField fieldPrenom;
    @FXML private TextField fieldEmail;
    @FXML private TextField fieldTelephone;
    @FXML private TextField fieldAdresse;
    @FXML private PasswordField fieldNouveauMotDePasse;
    @FXML private PasswordField fieldConfirmerMotDePasse;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO: Charger les informations de l'utilisateur connecté
        chargerProfil();
    }
    
    private void chargerProfil() {
        var utilisateur = SessionManager.getInstance().getUtilisateurConnecte();
        if (utilisateur != null) {
            fieldNom.setText(utilisateur.getNom());
            fieldPrenom.setText(utilisateur.getPrenom());
            fieldEmail.setText(utilisateur.getEmail());
            fieldTelephone.setText(utilisateur.getTelephone());
            fieldAdresse.setText(utilisateur.getAdresse());
        }
    }
    
    @FXML
    private void handleDashboard() {
        SceneManager.loadScene("/views/dashboard_client.fxml");
    }
    
    @FXML
    private void handleProfil() {
        SceneManager.loadScene("/views/profil_client.fxml");
    }
    
    @FXML
    private void handleDeconnexion() {
        SessionManager.getInstance().deconnecter();
        SceneManager.loadScene("/views/home.fxml");
    }
    
    @FXML
    private void handleSauvegarder() {
        // TODO: Valider les champs
        // TODO: Mettre à jour l'utilisateur
        // TODO: Sauvegarder dans CSV
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Profil mis à jour");
        alert.setHeaderText(null);
        alert.setContentText("Vos informations ont été mises à jour avec succès !");
        alert.showAndWait();
    }
}

