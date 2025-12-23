package org.example.controllers;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.example.models.LignePanier;
import org.example.models.Panier;
import org.example.utils.SceneManager;
import org.example.utils.SessionManager;

import java.net.URL;
import java.util.ResourceBundle;

public class PanierController implements Initializable {
    @FXML private TableView<LignePanier> tablePanier;
    @FXML private TableColumn<LignePanier, String> colOeuvre;
    @FXML private TableColumn<LignePanier, Double> colPrixUnitaire;
    @FXML private TableColumn<LignePanier, Integer> colQuantite;
    @FXML private TableColumn<LignePanier, Double> colSousTotal;
    @FXML private TableColumn<LignePanier, Void> colActions;
    @FXML private Label labelTotal;
    
    private ObservableList<LignePanier> lignesPanier;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Charger le panier
        chargerPanier();
        
        // Configurer les colonnes
        configurerColonnes();
        
        // Afficher le total
        mettreAJourTotal();
    }
    
    private void chargerPanier() {
        Panier panier = SessionManager.getInstance().getPanier();
        lignesPanier = FXCollections.observableArrayList(panier.getLignesPanier());
        tablePanier.setItems(lignesPanier);
    }
    
    private void configurerColonnes() {
        // Colonne Œuvre
        colOeuvre.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getOeuvre().getTitre()));
        
        // Colonne Prix unitaire
        colPrixUnitaire.setCellValueFactory(cellData -> 
            new SimpleDoubleProperty(cellData.getValue().getPrixUnitaire()).asObject());
        colPrixUnitaire.setCellFactory(column -> new TableCell<LignePanier, Double>() {
            @Override
            protected void updateItem(Double prix, boolean empty) {
                super.updateItem(prix, empty);
                if (empty || prix == null) {
                    setText(null);
                } else {
                    setText(String.format("%.2f €", prix));
                }
            }
        });
        
        // Colonne Quantité
        colQuantite.setCellValueFactory(cellData -> 
            new SimpleIntegerProperty(cellData.getValue().getQuantite()).asObject());
        
        // Colonne Sous-total
        colSousTotal.setCellValueFactory(cellData -> 
            new SimpleDoubleProperty(cellData.getValue().getSousTotal()).asObject());
        colSousTotal.setCellFactory(column -> new TableCell<LignePanier, Double>() {
            @Override
            protected void updateItem(Double total, boolean empty) {
                super.updateItem(total, empty);
                if (empty || total == null) {
                    setText(null);
                } else {
                    setText(String.format("%.2f €", total));
                }
            }
        });
        
        // Colonne Actions (bouton supprimer)
        colActions.setCellFactory(column -> new TableCell<LignePanier, Void>() {
            private final Button btnSupprimer = new Button("Supprimer");
            
            {
                btnSupprimer.setOnAction(event -> {
                    LignePanier ligne = getTableView().getItems().get(getIndex());
                    Panier panier = SessionManager.getInstance().getPanier();
                    panier.retirerOeuvre(ligne.getOeuvre());
                    lignesPanier.remove(ligne);
                    mettreAJourTotal();
                });
            }
            
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(btnSupprimer);
                }
            }
        });
    }
    
    private void mettreAJourTotal() {
        Panier panier = SessionManager.getInstance().getPanier();
        labelTotal.setText("Total: " + panier.getMontantTotalFormate());
    }
    
    @FXML
    private void handleAccueil() {
        SceneManager.loadScene("/views/home.fxml");
    }
    
    @FXML
    private void handleBoutique() {
        SceneManager.loadScene("/views/boutique.fxml");
    }
    
    @FXML
    private void handleViderPanier() {
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Vider le panier");
        confirmation.setHeaderText(null);
        confirmation.setContentText("Êtes-vous sûr de vouloir vider le panier ?");
        
        confirmation.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                Panier panier = SessionManager.getInstance().getPanier();
                panier.vider();
                lignesPanier.clear();
                mettreAJourTotal();
                
                Alert info = new Alert(Alert.AlertType.INFORMATION);
                info.setTitle("Panier vidé");
                info.setHeaderText(null);
                info.setContentText("Le panier a été vidé avec succès.");
                info.showAndWait();
            }
        });
    }
    
    @FXML
    private void handleCommander() {
        Panier panier = SessionManager.getInstance().getPanier();
        
        if (panier.estVide()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Panier vide");
            alert.setHeaderText(null);
            alert.setContentText("Votre panier est vide. Ajoutez des articles avant de commander.");
            alert.showAndWait();
            return;
        }
        
        // TODO: Créer une commande depuis le panier
        // TODO: Rediriger vers la page de confirmation
        // Pour l'instant, on affiche juste un message
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Commande");
        alert.setHeaderText(null);
        alert.setContentText("Fonctionnalité de commande à implémenter.\nTotal: " + panier.getMontantTotalFormate());
        alert.showAndWait();
    }
}

