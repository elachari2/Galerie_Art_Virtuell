package org.example;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.utils.SceneManager;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        SceneManager.setPrimaryStage(primaryStage);
        primaryStage.setTitle("Galerie d'Art Virtuel");
        primaryStage.setWidth(1200);
        primaryStage.setHeight(800);
        
        // Charger la page d'accueil
        SceneManager.loadScene("/views/home.fxml");
        
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
