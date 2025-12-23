package org.example.csv;

import org.example.models.Oeuvre;
import org.example.models.Artiste;
import org.example.models.Categorie;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CSVManager {
    private static final String DATA_DIR = "data/"; // Chemin relatif depuis resources/
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    // ========== GESTION DES ARTISTES ==========
    
    public static List<Artiste> chargerArtistes() {
        List<Artiste> artistes = new ArrayList<>();
        
        try (var inputStream = CSVManager.class.getResourceAsStream("/" + DATA_DIR + "artistes.csv")) {
            if (inputStream == null) {
                System.err.println("Le fichier artistes.csv n'existe pas dans les ressources.");
                return artistes;
            }
            
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String ligne = reader.readLine(); // Ignorer l'en-tête
                
                while ((ligne = reader.readLine()) != null) {
                    if (ligne.trim().isEmpty()) continue;
                    
                    String[] valeurs = parseCSVLine(ligne);
                    if (valeurs.length >= 6) {
                        try {
                            int id = Integer.parseInt(valeurs[0].trim());
                            String nom = valeurs[1].trim();
                            String prenom = valeurs[2].trim();
                            LocalDate dateNaissance = LocalDate.parse(valeurs[3].trim(), DATE_FORMATTER);
                            String nationalite = valeurs[4].trim();
                            String biographie = valeurs[5].trim();
                            
                            Artiste artiste = new Artiste(id, nom, prenom, dateNaissance, nationalite, biographie);
                            artistes.add(artiste);
                        } catch (Exception e) {
                            System.err.println("Erreur lors de la lecture d'une ligne d'artiste : " + ligne);
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier artistes.csv");
            e.printStackTrace();
        }
        
        return artistes;
    }
    
    public static void sauvegarderArtistes(List<Artiste> artistes) {
        // Pour l'écriture, on utilise le chemin du projet (pas dans le JAR)
        Path path = Paths.get("src/main/resources/" + DATA_DIR + "artistes.csv");
        
        try {
            Files.createDirectories(path.getParent());
        } catch (IOException e) {
            System.err.println("Erreur lors de la création du répertoire");
        }
        
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            // Écrire l'en-tête
            writer.write("id,nom,prenom,dateNaissance,nationalite,biographie");
            writer.newLine();
            
            // Écrire les données
            for (Artiste artiste : artistes) {
                writer.write(String.format("%d,%s,%s,%s,%s,%s",
                    artiste.getId(),
                    escapeCSV(artiste.getNom()),
                    escapeCSV(artiste.getPrenom()),
                    artiste.getDateNaissance().format(DATE_FORMATTER),
                    escapeCSV(artiste.getNationalite()),
                    escapeCSV(artiste.getBiographie())
                ));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture du fichier artistes.csv");
            e.printStackTrace();
        }
    }
    
    // ========== GESTION DES CATÉGORIES ==========
    
    public static List<Categorie> chargerCategories() {
        List<Categorie> categories = new ArrayList<>();
        
        try (var inputStream = CSVManager.class.getResourceAsStream("/" + DATA_DIR + "categories.csv")) {
            if (inputStream == null) {
                System.err.println("Le fichier categories.csv n'existe pas dans les ressources.");
                return categories;
            }
            
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String ligne = reader.readLine(); // Ignorer l'en-tête
                
                while ((ligne = reader.readLine()) != null) {
                    if (ligne.trim().isEmpty()) continue;
                    
                    String[] valeurs = parseCSVLine(ligne);
                    if (valeurs.length >= 3) {
                        try {
                            int id = Integer.parseInt(valeurs[0].trim());
                            String nom = valeurs[1].trim();
                            String description = valeurs[2].trim();
                            
                            Categorie categorie = new Categorie(id, nom, description);
                            categories.add(categorie);
                        } catch (Exception e) {
                            System.err.println("Erreur lors de la lecture d'une ligne de catégorie : " + ligne);
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier categories.csv");
            e.printStackTrace();
        }
        
        return categories;
    }
    
    public static void sauvegarderCategories(List<Categorie> categories) {
        Path path = Paths.get("src/main/resources/" + DATA_DIR + "categories.csv");
        
        try {
            Files.createDirectories(path.getParent());
        } catch (IOException e) {
            System.err.println("Erreur lors de la création du répertoire");
        }
        
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            // Écrire l'en-tête
            writer.write("id,nom,description");
            writer.newLine();
            
            // Écrire les données
            for (Categorie categorie : categories) {
                writer.write(String.format("%d,%s,%s",
                    categorie.getId(),
                    escapeCSV(categorie.getNom()),
                    escapeCSV(categorie.getDescription())
                ));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture du fichier categories.csv");
            e.printStackTrace();
        }
    }
    
    // ========== GESTION DES ŒUVRES ==========
    
    public static List<Oeuvre> chargerOeuvres(List<Artiste> artistes, List<Categorie> categories) {
        List<Oeuvre> oeuvres = new ArrayList<>();
        
        try (var inputStream = CSVManager.class.getResourceAsStream("/" + DATA_DIR + "oeuvres.csv")) {
            if (inputStream == null) {
                System.err.println("Le fichier oeuvres.csv n'existe pas dans les ressources.");
                return oeuvres;
            }
            
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String ligne = reader.readLine(); // Ignorer l'en-tête
                
                while ((ligne = reader.readLine()) != null) {
                    if (ligne.trim().isEmpty()) continue;
                    
                    String[] valeurs = parseCSVLine(ligne);
                    if (valeurs.length >= 8) {
                        try {
                            int id = Integer.parseInt(valeurs[0].trim());
                            String titre = valeurs[1].trim();
                            int annee = Integer.parseInt(valeurs[2].trim());
                            String dimensions = valeurs[3].trim();
                            double prix = Double.parseDouble(valeurs[4].trim());
                            String cheminImage = valeurs[5].trim();
                            int artisteId = Integer.parseInt(valeurs[6].trim());
                            int categorieId = Integer.parseInt(valeurs[7].trim());
                            
                            // Trouver l'artiste et la catégorie correspondants
                            Artiste artiste = trouverArtiste(artistes, artisteId);
                            Categorie categorie = trouverCategorie(categories, categorieId);
                            
                            if (artiste != null && categorie != null) {
                                Oeuvre oeuvre = new Oeuvre(id, titre, annee, dimensions, prix, cheminImage, artiste, categorie);
                                oeuvres.add(oeuvre);
                                artiste.ajouterOeuvre(oeuvre);
                                categorie.ajouterOeuvre(oeuvre);
                            } else {
                                System.err.println("Artiste ou catégorie introuvable pour l'œuvre : " + titre);
                            }
                        } catch (Exception e) {
                            System.err.println("Erreur lors de la lecture d'une ligne d'œuvre : " + ligne);
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier oeuvres.csv");
            e.printStackTrace();
        }
        
        return oeuvres;
    }
    
    public static void sauvegarderOeuvres(List<Oeuvre> oeuvres) {
        Path path = Paths.get("src/main/resources/" + DATA_DIR + "oeuvres.csv");
        
        try {
            Files.createDirectories(path.getParent());
        } catch (IOException e) {
            System.err.println("Erreur lors de la création du répertoire");
        }
        
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            // Écrire l'en-tête
            writer.write("id,titre,anneeCreation,dimensions,prix,cheminImage,artisteId,categorieId");
            writer.newLine();
            
            // Écrire les données
            for (Oeuvre oeuvre : oeuvres) {
                writer.write(String.format("%d,%s,%d,%s,%.2f,%s,%d,%d",
                    oeuvre.getId(),
                    escapeCSV(oeuvre.getTitre()),
                    oeuvre.getAnneeCreation(),
                    escapeCSV(oeuvre.getDimensions()),
                    oeuvre.getPrix(),
                    escapeCSV(oeuvre.getCheminImage()),
                    oeuvre.getArtiste().getId(),
                    oeuvre.getCategorie().getId()
                ));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture du fichier oeuvres.csv");
            e.printStackTrace();
        }
    }
    
    // ========== MÉTHODES UTILITAIRES ==========
    
    private static Artiste trouverArtiste(List<Artiste> artistes, int id) {
        return artistes.stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElse(null);
    }
    
    private static Categorie trouverCategorie(List<Categorie> categories, int id) {
        return categories.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
    }
    
    /**
     * Parse une ligne CSV en gérant les valeurs contenant des virgules entre guillemets
     */
    private static String[] parseCSVLine(String ligne) {
        List<String> valeurs = new ArrayList<>();
        boolean entreGuillemets = false;
        StringBuilder valeurCourante = new StringBuilder();
        
        for (char c : ligne.toCharArray()) {
            if (c == '"') {
                entreGuillemets = !entreGuillemets;
            } else if (c == ',' && !entreGuillemets) {
                valeurs.add(valeurCourante.toString());
                valeurCourante = new StringBuilder();
            } else {
                valeurCourante.append(c);
            }
        }
        valeurs.add(valeurCourante.toString());
        
        return valeurs.toArray(new String[0]);
    }
    
    /**
     * Échappe les valeurs CSV (ajoute des guillemets si nécessaire)
     */
    private static String escapeCSV(String valeur) {
        if (valeur == null) {
            return "";
        }
        if (valeur.contains(",") || valeur.contains("\"") || valeur.contains("\n")) {
            return "\"" + valeur.replace("\"", "\"\"") + "\"";
        }
        return valeur;
    }
}

