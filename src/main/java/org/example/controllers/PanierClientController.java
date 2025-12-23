package org.example.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.example.utils.SceneManager;
import org.example.utils.SessionManager;

import java.net.URL;
import java.util.ResourceBundle;

public class PanierClientController implements Initializable {
    @FXML private TableView<?> tablePanier;
    @FXML private Label labelTotal;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO: Charger le panier du client
    }
    
    @FXML
    private void handleDashboard() {
        SceneManager.loadScene("/views/dashboard_client.fxml");
    }
    
    @FXML
    private void handleBoutique() {
        SceneManager.loadScene("/views/boutique.fxml");
    }
    
    @FXML
    private void handleDeconnexion() {
        SessionManager.getInstance().deconnecter();
        SceneManager.loadScene("/views/home.fxml");
    }
    
    @FXML
    private void handleViderPanier() {
        // TODO: Vider le panier
    }
    
    @FXML
    private void handleCommander() {
        // TODO: Créer une commande
    }
}

