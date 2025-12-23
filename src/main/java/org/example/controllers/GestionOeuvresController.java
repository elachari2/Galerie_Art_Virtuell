package org.example.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.example.models.Oeuvre;
import org.example.utils.SceneManager;
import org.example.utils.SessionManager;

import java.net.URL;
import java.util.ResourceBundle;

public class GestionOeuvresController implements Initializable {
    @FXML private TextField fieldTitre;
    @FXML private TextField fieldAnnee;
    @FXML private TextField fieldDimensions;
    @FXML private TextField fieldPrix;
    @FXML private TextField fieldCheminImage;
    @FXML private ComboBox<String> comboArtiste;
    @FXML private ComboBox<String> comboCategorie;
    @FXML private TableView<Oeuvre> tableOeuvres;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO: Charger les œuvres
        // TODO: Remplir les ComboBox
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
    private void handleDeconnexion() {
        SessionManager.getInstance().deconnecter();
        SceneManager.loadScene("/views/home.fxml");
    }
    
    @FXML
    private void handleAjouter() {
        // TODO: Valider et ajouter une nouvelle œuvre
    }
    
    @FXML
    private void handleModifier() {
        // TODO: Modifier l'œuvre sélectionnée
    }
    
    @FXML
    private void handleSupprimer() {
        // TODO: Supprimer l'œuvre sélectionnée
    }
    
    @FXML
    private void handleReset() {
        fieldTitre.clear();
        fieldAnnee.clear();
        fieldDimensions.clear();
        fieldPrix.clear();
        fieldCheminImage.clear();
        comboArtiste.getSelectionModel().clearSelection();
        comboCategorie.getSelectionModel().clearSelection();
    }
}

