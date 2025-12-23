package org.example.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import org.example.csv.CSVManager;
import org.example.models.Artiste;
import org.example.models.Categorie;
import org.example.models.Oeuvre;
import org.example.models.Panier;
import org.example.utils.SceneManager;
import org.example.utils.SessionManager;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class BoutiqueController implements Initializable {
    @FXML private ComboBox<String> comboCategorie;
    @FXML private ComboBox<String> comboArtiste;
    @FXML private TextField fieldPrixMin;
    @FXML private TextField fieldPrixMax;
    @FXML private FlowPane flowPaneOeuvres;
    @FXML private Label labelPanier;
    
    private ObservableList<Oeuvre> toutesLesOeuvres;
    private List<Artiste> artistes;
    private List<Categorie> categories;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Charger les données depuis CSV
        artistes = CSVManager.chargerArtistes();
        categories = CSVManager.chargerCategories();
        List<Oeuvre> oeuvresList = CSVManager.chargerOeuvres(artistes, categories);
        toutesLesOeuvres = FXCollections.observableArrayList(oeuvresList);
        
        // Remplir les ComboBox
        remplirComboBox();
        
        // Afficher toutes les œuvres
        afficherOeuvres(toutesLesOeuvres);
        
        // Mettre à jour le label du panier
        mettreAJourLabelPanier();
    }
    
    private void remplirComboBox() {
        // Remplir ComboBox Catégories
        List<String> nomsCategories = categories.stream()
            .map(Categorie::getNom)
            .collect(Collectors.toList());
        comboCategorie.setItems(FXCollections.observableArrayList(nomsCategories));
        
        // Remplir ComboBox Artistes
        List<String> nomsArtistes = artistes.stream()
            .map(Artiste::getNomComplet)
            .collect(Collectors.toList());
        comboArtiste.setItems(FXCollections.observableArrayList(nomsArtistes));
    }
    
    private void afficherOeuvres(List<Oeuvre> oeuvres) {
        flowPaneOeuvres.getChildren().clear();
        
        for (Oeuvre oeuvre : oeuvres) {
            VBox carte = creerCarteOeuvre(oeuvre);
            flowPaneOeuvres.getChildren().add(carte);
        }
    }
    
    private VBox creerCarteOeuvre(Oeuvre oeuvre) {
        VBox carte = new VBox(10);
        carte.setPadding(new Insets(15));
        carte.setStyle("-fx-background-color: white; -fx-border-color: #ddd; -fx-border-radius: 5; -fx-background-radius: 5;");
        carte.setPrefWidth(250);
        carte.setPrefHeight(300);
        
        // Titre
        Label labelTitre = new Label(oeuvre.getTitre());
        labelTitre.setStyle("-fx-font-size: 16; -fx-font-weight: bold;");
        labelTitre.setWrapText(true);
        
        // Artiste
        String nomArtiste = oeuvre.getArtiste() != null ? oeuvre.getArtiste().getNomComplet() : "Inconnu";
        Label labelArtiste = new Label("Artiste: " + nomArtiste);
        labelArtiste.setStyle("-fx-font-size: 12;");
        
        // Catégorie
        String nomCategorie = oeuvre.getCategorie() != null ? oeuvre.getCategorie().getNom() : "Inconnue";
        Label labelCategorie = new Label("Catégorie: " + nomCategorie);
        labelCategorie.setStyle("-fx-font-size: 12;");
        
        // Année
        Label labelAnnee = new Label("Année: " + oeuvre.getAnneeCreation());
        labelAnnee.setStyle("-fx-font-size: 12;");
        
        // Dimensions
        Label labelDimensions = new Label("Dimensions: " + oeuvre.getDimensions());
        labelDimensions.setStyle("-fx-font-size: 12;");
        
        // Prix
        Label labelPrix = new Label(oeuvre.getPrixFormate());
        labelPrix.setStyle("-fx-font-size: 18; -fx-font-weight: bold; -fx-text-fill: #4CAF50;");
        
        // Bouton "Ajouter au panier"
        Button btnAjouter = new Button("Ajouter au panier");
        btnAjouter.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 14;");
        btnAjouter.setMaxWidth(Double.MAX_VALUE);
        btnAjouter.setOnAction(e -> handleAjouterAuPanier(oeuvre));
        
        // Espaceur
        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);
        
        carte.getChildren().addAll(
            labelTitre,
            labelArtiste,
            labelCategorie,
            labelAnnee,
            labelDimensions,
            spacer,
            labelPrix,
            btnAjouter
        );
        
        return carte;
    }
    
    private void mettreAJourLabelPanier() {
        int nombreArticles = SessionManager.getInstance().getPanier().getNombreArticles();
        labelPanier.setText("Panier: " + nombreArticles + " article(s)");
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
    private void handlePanier() {
        SceneManager.loadScene("/views/panier.fxml");
    }
    
    @FXML
    private void handleRecherche() {
        appliquerFiltres();
    }
    
    @FXML
    private void handleAjouterAuPanier(Oeuvre oeuvre) {
        // Ajouter l'œuvre au panier
        Panier panier = SessionManager.getInstance().getPanier();
        panier.ajouterOeuvre(oeuvre, 1);
        
        // Mettre à jour le label du panier
        mettreAJourLabelPanier();
        
        // Afficher un message de confirmation
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Panier");
        alert.setHeaderText(null);
        alert.setContentText("L'œuvre \"" + oeuvre.getTitre() + "\" a été ajoutée au panier !");
        alert.showAndWait();
    }
    
    private void appliquerFiltres() {
        List<Oeuvre> oeuvresFiltrees = toutesLesOeuvres.stream()
            .filter(oeuvre -> {
                // Filtre par catégorie
                String categorieSelectionnee = comboCategorie.getSelectionModel().getSelectedItem();
                if (categorieSelectionnee != null && !categorieSelectionnee.isEmpty()) {
                    if (oeuvre.getCategorie() == null || !oeuvre.getCategorie().getNom().equals(categorieSelectionnee)) {
                        return false;
                    }
                }
                
                // Filtre par artiste
                String artisteSelectionne = comboArtiste.getSelectionModel().getSelectedItem();
                if (artisteSelectionne != null && !artisteSelectionne.isEmpty()) {
                    if (oeuvre.getArtiste() == null || !oeuvre.getArtiste().getNomComplet().equals(artisteSelectionne)) {
                        return false;
                    }
                }
                
                // Filtre par prix min
                String prixMinText = fieldPrixMin.getText().trim();
                if (!prixMinText.isEmpty()) {
                    try {
                        double prixMin = Double.parseDouble(prixMinText);
                        if (oeuvre.getPrix() < prixMin) {
                            return false;
                        }
                    } catch (NumberFormatException e) {
                        // Si le prix n'est pas un nombre valide, on ignore ce filtre
                    }
                }
                
                // Filtre par prix max
                String prixMaxText = fieldPrixMax.getText().trim();
                if (!prixMaxText.isEmpty()) {
                    try {
                        double prixMax = Double.parseDouble(prixMaxText);
                        if (oeuvre.getPrix() > prixMax) {
                            return false;
                        }
                    } catch (NumberFormatException e) {
                        // Si le prix n'est pas un nombre valide, on ignore ce filtre
                    }
                }
                
                return true;
            })
            .collect(Collectors.toList());
        
        afficherOeuvres(oeuvresFiltrees);
    }
}

