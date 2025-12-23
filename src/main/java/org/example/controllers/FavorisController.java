package org.example.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import org.example.utils.SceneManager;
import org.example.utils.SessionManager;

import java.net.URL;
import java.util.ResourceBundle;

public class FavorisController implements Initializable {
    @FXML private Label labelNombreFavoris;
    @FXML private FlowPane flowPaneFavoris;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO: Charger les favoris de l'utilisateur
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
}

