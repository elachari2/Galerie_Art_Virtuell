package org.example.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import org.example.utils.SceneManager;
import org.example.utils.SessionManager;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML private MenuItem menuLogin;
    @FXML private MenuItem menuDeconnexion;
    @FXML private MenuItem menuEspaceClient;
    @FXML private MenuItem menuEspaceAdmin;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mettreAJourMenu();
    }
    
    private void mettreAJourMenu() {
        boolean connecte = SessionManager.getInstance().estConnecte();
        menuLogin.setVisible(!connecte);
        menuDeconnexion.setVisible(connecte);
        menuEspaceClient.setVisible(SessionManager.getInstance().estClient());
        menuEspaceAdmin.setVisible(SessionManager.getInstance().estAdmin());
    }
    
    @FXML
    private void handleAccueil() {
        SceneManager.loadScene("/views/home.fxml");
    }
    
    @FXML
    private void handleGalerie() {
        SceneManager.loadScene("/views/galerie.fxml");
    }
    
    @FXML
    private void handleBoutique() {
        SceneManager.loadScene("/views/boutique.fxml");
    }
    
    @FXML
    private void handlePanier() {
        SceneManager.loadScene("/views/panier.fxml");
    }
    
    @FXML
    private void handleContact() {
        SceneManager.loadScene("/views/contact.fxml");
    }
    
    @FXML
    private void handleLogin() {
        SceneManager.loadScene("/views/login.fxml");
    }
    
    @FXML
    private void handleDeconnexion() {
        SessionManager.getInstance().deconnecter();
        mettreAJourMenu();
        SceneManager.loadScene("/views/home.fxml");
    }
    
    @FXML
    private void handleEspaceClient() {
        SceneManager.loadScene("/views/dashboard_client.fxml");
    }
    
    @FXML
    private void handleEspaceAdmin() {
        SceneManager.loadScene("/views/dashboard_admin.fxml");
    }
}

