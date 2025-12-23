package org.example.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.example.utils.SceneManager;
import org.example.utils.SessionManager;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardAdminController implements Initializable {
    @FXML private Label labelNombreOeuvres;
    @FXML private Label labelNombreArtistes;
    @FXML private Label labelCommandesAttente;
    @FXML private Label labelNombreClients;
    @FXML private TableView<?> tableCommandesRecentes;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO: Charger les statistiques
    }
    
    @FXML
    private void handleDashboard() {
        SceneManager.loadScene("/views/dashboard_admin.fxml");
    }
    
    @FXML
    private void handleGestionOeuvres() {
        SceneManager.loadScene("/views/gestion_oeuvres.fxml");
    }
    
    @FXML
    private void handleGestionArtistes() {
        SceneManager.loadScene("/views/gestion_artistes.fxml");
    }
    
    @FXML
    private void handleGestionCommandes() {
        SceneManager.loadScene("/views/gestion_commandes.fxml");
    }
    
    @FXML
    private void handleGestionUtilisateurs() {
        SceneManager.loadScene("/views/gestion_utilisateurs.fxml");
    }
    
    @FXML
    private void handleGestionStock() {
        SceneManager.loadScene("/views/gestion_stock.fxml");
    }
    
    @FXML
    private void handleDeconnexion() {
        SessionManager.getInstance().deconnecter();
        SceneManager.loadScene("/views/home.fxml");
    }
}

