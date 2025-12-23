package org.example.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.example.utils.SceneManager;
import org.example.utils.SessionManager;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardClientController implements Initializable {
    @FXML private Label labelNombreCommandes;
    @FXML private Label labelNombreFavoris;
    @FXML private Label labelArticlesPanier;
    @FXML private TableView<?> tableCommandes;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO: Charger les statistiques du client
        // TODO: Charger les dernières commandes
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
    private void handleHistorique() {
        SceneManager.loadScene("/views/historique_commandes.fxml");
    }
    
    @FXML
    private void handleFavoris() {
        SceneManager.loadScene("/views/favoris.fxml");
    }
    
    @FXML
    private void handlePanier() {
        SceneManager.loadScene("/views/panier_client.fxml");
    }
    
    @FXML
    private void handleDeconnexion() {
        SessionManager.getInstance().deconnecter();
        SceneManager.loadScene("/views/home.fxml");
    }
}

