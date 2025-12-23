package org.example.controllers;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.example.csv.CSVManager;
import org.example.models.Artiste;
import org.example.models.Categorie;
import org.example.models.Oeuvre;
import org.example.utils.SceneManager;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class GalerieController implements Initializable {
    @FXML private ComboBox<String> comboCategorie;
    @FXML private ComboBox<String> comboArtiste;
    @FXML private TextField fieldPrixMax;
    @FXML private TableView<Oeuvre> tableOeuvres;
    @FXML private TableColumn<Oeuvre, Integer> colId;
    @FXML private TableColumn<Oeuvre, String> colTitre;
    @FXML private TableColumn<Oeuvre, String> colArtiste;
    @FXML private TableColumn<Oeuvre, String> colCategorie;
    @FXML private TableColumn<Oeuvre, Double> colPrix;
    @FXML private TableColumn<Oeuvre, Integer> colAnnee;
    @FXML private Label labelNombreOeuvres;
    
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
        
        // Configurer les colonnes de la table
        configurerColonnes();
        
        // Remplir la table
        tableOeuvres.setItems(toutesLesOeuvres);
        
        // Remplir les ComboBox
        remplirComboBox();
        
        // Mettre à jour le label
        mettreAJourLabel();
    }
    
    private void configurerColonnes() {
        colId.setCellValueFactory(cellData -> 
            new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        colTitre.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getTitre()));
        colArtiste.setCellValueFactory(cellData -> {
            Artiste artiste = cellData.getValue().getArtiste();
            return new SimpleStringProperty(artiste != null ? artiste.getNomComplet() : "");
        });
        colCategorie.setCellValueFactory(cellData -> {
            Categorie categorie = cellData.getValue().getCategorie();
            return new SimpleStringProperty(categorie != null ? categorie.getNom() : "");
        });
        colPrix.setCellValueFactory(cellData -> 
            new SimpleDoubleProperty(cellData.getValue().getPrix()).asObject());
        colAnnee.setCellValueFactory(cellData -> 
            new SimpleIntegerProperty(cellData.getValue().getAnneeCreation()).asObject());
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
    
    private void mettreAJourLabel() {
        labelNombreOeuvres.setText("Nombre d'œuvres: " + tableOeuvres.getItems().size());
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
    private void handleFiltreCategorie() {
        appliquerFiltres();
    }
    
    @FXML
    private void handleFiltreArtiste() {
        appliquerFiltres();
    }
    
    @FXML
    private void handleRecherche() {
        appliquerFiltres();
    }
    
    @FXML
    private void handleResetFiltres() {
        comboCategorie.getSelectionModel().clearSelection();
        comboArtiste.getSelectionModel().clearSelection();
        fieldPrixMax.clear();
        tableOeuvres.setItems(toutesLesOeuvres);
        mettreAJourLabel();
    }
    
    private void appliquerFiltres() {
        ObservableList<Oeuvre> oeuvresFiltrees = FXCollections.observableArrayList(toutesLesOeuvres);
        
        // Filtre par catégorie
        String categorieSelectionnee = comboCategorie.getSelectionModel().getSelectedItem();
        if (categorieSelectionnee != null && !categorieSelectionnee.isEmpty()) {
            oeuvresFiltrees = oeuvresFiltrees.filtered(oeuvre -> 
                oeuvre.getCategorie() != null && 
                oeuvre.getCategorie().getNom().equals(categorieSelectionnee));
        }
        
        // Filtre par artiste
        String artisteSelectionne = comboArtiste.getSelectionModel().getSelectedItem();
        if (artisteSelectionne != null && !artisteSelectionne.isEmpty()) {
            oeuvresFiltrees = oeuvresFiltrees.filtered(oeuvre -> 
                oeuvre.getArtiste() != null && 
                oeuvre.getArtiste().getNomComplet().equals(artisteSelectionne));
        }
        
        // Filtre par prix max
        String prixMaxText = fieldPrixMax.getText().trim();
        if (!prixMaxText.isEmpty()) {
            try {
                double prixMax = Double.parseDouble(prixMaxText);
                oeuvresFiltrees = oeuvresFiltrees.filtered(oeuvre -> 
                    oeuvre.getPrix() <= prixMax);
            } catch (NumberFormatException e) {
                // Si le prix n'est pas un nombre valide, on ignore ce filtre
            }
        }
        
        tableOeuvres.setItems(oeuvresFiltrees);
        mettreAJourLabel();
    }
}

