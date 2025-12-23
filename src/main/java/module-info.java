module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    
    opens org.example to javafx.fxml;
    opens org.example.controllers to javafx.fxml;
    opens org.example.models to javafx.base;
    opens org.example.utils to javafx.fxml;
    opens org.example.csv to javafx.base;
    
    exports org.example;
    exports org.example.controllers;
    exports org.example.models;
    exports org.example.utils;
    exports org.example.csv;
}

