package org.example.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.example.utils.SceneManager;
import org.example.utils.SessionManager;

import java.net.URL;
import java.util.ResourceBundle;

public class GestionStockController implements Initializable {
    @FXML private TableView<?> tableStock;
    @FXML private TextField fieldQuantite;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO: Charger le stock (si implémenté)
    }
    
    @FXML
    private void handleDashboard() {
        SceneManager.loadScene("/views/dashboard_admin.fxml");
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
    
    @FXML
    private void handleModifierStock() {
        // TODO: Modifier le stock de l'œuvre sélectionnée
    }
}

