module com.interncell {
    requires transitive javafx.fxml;
    requires javafx.controls;
    requires transitive javafx.graphics;
    requires transitive org.apache.logging.log4j;
    requires transitive com.google.gson;
    requires transitive com.jfoenix;
    
    
    opens com.interncell;
    opens com.interncell.controllers;
    opens com.interncell.api;

    exports com.interncell.models;
    exports com.interncell;
}
