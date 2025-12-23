package org.example.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.example.utils.SceneManager;

import java.net.URL;
import java.util.ResourceBundle;

public class ContactController implements Initializable {
    @FXML private TextField fieldNom;
    @FXML private TextField fieldEmail;
    @FXML private TextArea textAreaMessage;
    @FXML private TextArea textAreaAPropos;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO: Charger le texte "À propos" depuis un fichier ou base de données
    }
    
    @FXML
    private void handleAccueil() {
        SceneManager.loadScene("/views/home.fxml");
    }
    
    @FXML
    private void handleEnvoyerMessage() {
        // TODO: Valider les champs
        // TODO: Envoyer le message (sauvegarder dans CSV ou envoyer par email)
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message envoyé");
        alert.setHeaderText(null);
        alert.setContentText("Votre message a été envoyé avec succès !");
        alert.showAndWait();
        
        // Réinitialiser les champs
        fieldNom.clear();
        fieldEmail.clear();
        textAreaMessage.clear();
    }
}

