package org.example.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.example.utils.SceneManager;
import org.example.utils.SessionManager;

import java.net.URL;
import java.util.ResourceBundle;

public class HistoriqueCommandesController implements Initializable {
    @FXML private TableView<?> tableCommandes;
    @FXML private TableColumn<?, ?> colId;
    @FXML private TableColumn<?, ?> colDate;
    @FXML private TableColumn<?, ?> colMontant;
    @FXML private TableColumn<?, ?> colStatut;
    @FXML private TableColumn<?, ?> colActions;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO: Charger les commandes du client
    }
    
    @FXML
    private void handleDashboard() {
        SceneManager.loadScene("/views/dashboard_client.fxml");
    }
    
    @FXML
    private void handleDeconnexion() {
        SessionManager.getInstance().deconnecter();
        SceneManager.loadScene("/views/home.fxml");
    }
    
    @FXML
    private void handleVoirDetails() {
        // TODO: Afficher les détails de la commande sélectionnée
    }
    
    @FXML
    private void handleAnnulerCommande() {
        // TODO: Annuler la commande sélectionnée (si possible)
    }
}

