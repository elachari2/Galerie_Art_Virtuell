package org.example.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneManager {
    private static Stage primaryStage;
    
    public static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }
    
    public static void loadScene(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(fxmlPath));
            Parent root = loader.load();
            
            if (primaryStage.getScene() == null) {
                Scene scene = new Scene(root);
                primaryStage.setScene(scene);
            } else {
                primaryStage.getScene().setRoot(root);
            }
            
            primaryStage.show();
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement de la scène : " + fxmlPath);
            e.printStackTrace();
        }
    }
    
    public static void loadScene(String fxmlPath, int width, int height) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(fxmlPath));
            Parent root = loader.load();
            
            Scene scene = new Scene(root, width, height);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement de la scène : " + fxmlPath);
            e.printStackTrace();
        }
    }
}

