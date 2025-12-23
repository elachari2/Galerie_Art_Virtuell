package org.example.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.example.utils.SceneManager;
import org.example.utils.SessionManager;

import java.net.URL;
import java.util.ResourceBundle;

public class GestionCommandesController implements Initializable {
    @FXML private ComboBox<String> comboStatut;
    @FXML private TableView<?> tableCommandes;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO: Charger les commandes
        // TODO: Remplir le ComboBox des statuts
    }
    
    @FXML
    private void handleDashboard() {
        SceneManager.loadScene("/views/dashboard_admin.fxml");
    }
    
    @FXML
    private void handleGestionCommandes() {
        SceneManager.loadScene("/views/gestion_commandes.fxml");
    }
    
    @FXML
    private void handleDeconnexion() {
        SessionManager.getInstance().deconnecter();
        SceneManager.loadScene("/views/home.fxml");
    }
    
    @FXML
    private void handleFiltreStatut() {
        // TODO: Filtrer par statut
    }
    
    @FXML
    private void handleResetFiltre() {
        comboStatut.getSelectionModel().clearSelection();
        // TODO: Réinitialiser les données
    }
    
    @FXML
    private void handleVoirDetails() {
        // TODO: Afficher les détails de la commande
    }
    
    @FXML
    private void handleValider() {
        // TODO: Valider la commande sélectionnée
    }
    
    @FXML
    private void handleAnnuler() {
        // TODO: Annuler la commande sélectionnée
    }
}

