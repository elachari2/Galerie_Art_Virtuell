package org.example.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.example.utils.SceneManager;
import org.example.utils.SessionManager;

import java.net.URL;
import java.util.ResourceBundle;

public class GestionUtilisateursController implements Initializable {
    @FXML private ComboBox<String> comboRole;
    @FXML private TableView<?> tableUtilisateurs;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO: Charger les utilisateurs
        // TODO: Remplir le ComboBox des rôles
    }
    
    @FXML
    private void handleDashboard() {
        SceneManager.loadScene("/views/dashboard_admin.fxml");
    }
    
    @FXML
    private void handleGestionUtilisateurs() {
        SceneManager.loadScene("/views/gestion_utilisateurs.fxml");
    }
    
    @FXML
    private void handleDeconnexion() {
        SessionManager.getInstance().deconnecter();
        SceneManager.loadScene("/views/home.fxml");
    }
    
    @FXML
    private void handleFiltreRole() {
        // TODO: Filtrer par rôle
    }
    
    @FXML
    private void handleResetFiltre() {
        comboRole.getSelectionModel().clearSelection();
        // TODO: Réinitialiser les données
    }
    
    @FXML
    private void handleVoirDetails() {
        // TODO: Afficher les détails de l'utilisateur
    }
    
    @FXML
    private void handleSupprimer() {
        // TODO: Supprimer l'utilisateur sélectionné
    }
}

