package org.example.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.example.models.Artiste;
import org.example.utils.SceneManager;
import org.example.utils.SessionManager;

import java.net.URL;
import java.util.ResourceBundle;

public class GestionArtistesController implements Initializable {
    @FXML private TextField fieldNom;
    @FXML private TextField fieldPrenom;
    @FXML private DatePicker datePickerNaissance;
    @FXML private TextField fieldNationalite;
    @FXML private TextArea textAreaBiographie;
    @FXML private TableView<Artiste> tableArtistes;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO: Charger les artistes
    }
    
    @FXML
    private void handleDashboard() {
        SceneManager.loadScene("/views/dashboard_admin.fxml");
    }
    
    @FXML
    private void handleGestionArtistes() {
        SceneManager.loadScene("/views/gestion_artistes.fxml");
    }
    
    @FXML
    private void handleDeconnexion() {
        SessionManager.getInstance().deconnecter();
        SceneManager.loadScene("/views/home.fxml");
    }
    
    @FXML
    private void handleAjouter() {
        // TODO: Ajouter un nouvel artiste
    }
    
    @FXML
    private void handleModifier() {
        // TODO: Modifier l'artiste sélectionné
    }
    
    @FXML
    private void handleSupprimer() {
        // TODO: Supprimer l'artiste sélectionné
    }
    
    @FXML
    private void handleReset() {
        fieldNom.clear();
        fieldPrenom.clear();
        datePickerNaissance.setValue(null);
        fieldNationalite.clear();
        textAreaBiographie.clear();
    }
}

