package org.example.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.example.utils.SceneManager;
import org.example.utils.SessionManager;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML private TextField fieldEmailConnexion;
    @FXML private PasswordField fieldMotDePasseConnexion;
    @FXML private TextField fieldNomInscription;
    @FXML private TextField fieldPrenomInscription;
    @FXML private TextField fieldEmailInscription;
    @FXML private PasswordField fieldMotDePasseInscription;
    @FXML private TextField fieldTelephoneInscription;
    @FXML private TextField fieldAdresseInscription;
    @FXML private Label labelErreurConnexion;
    @FXML private Label labelErreurInscription;
    @FXML private TabPane tabPane;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO: Charger les utilisateurs depuis CSV
    }
    
    @FXML
    private void handleRetour() {
        SceneManager.loadScene("/views/home.fxml");
    }
    
    @FXML
    private void handleConnexion() {
        String email = fieldEmailConnexion.getText();
        String motDePasse = fieldMotDePasseConnexion.getText();
        
        // TODO: Vérifier les identifiants dans CSV
        // Pour l'instant, simulation
        if (email.isEmpty() || motDePasse.isEmpty()) {
            labelErreurConnexion.setText("Veuillez remplir tous les champs");
            labelErreurConnexion.setVisible(true);
            return;
        }
        
        // TODO: Rechercher l'utilisateur
        // Utilisateur utilisateur = trouverUtilisateur(email, motDePasse);
        // if (utilisateur != null) {
        //     SessionManager.getInstance().connecter(utilisateur);
        //     if (SessionManager.getInstance().estAdmin()) {
        //         SceneManager.loadScene("/views/dashboard_admin.fxml");
        //     } else {
        //         SceneManager.loadScene("/views/dashboard_client.fxml");
        //     }
        // } else {
        //     labelErreurConnexion.setText("Email ou mot de passe incorrect");
        //     labelErreurConnexion.setVisible(true);
        // }
    }
    
    @FXML
    private void handleInscription() {
        // TODO: Valider les champs
        // TODO: Vérifier que l'email n'existe pas déjà
        // TODO: Créer un nouvel utilisateur
        // TODO: Sauvegarder dans CSV
        // TODO: Connecter l'utilisateur
        // TODO: Rediriger vers l'espace client
        
        labelErreurInscription.setText("Fonctionnalité à implémenter");
        labelErreurInscription.setVisible(true);
    }
}

