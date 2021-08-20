package com.interncell;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class SetStage {
    static final Logger logger = LogManager.getLogger(SetStage.class);
    public void stageSetter(String fxmlName, Stage stage) {
        try {
            stage.setTitle("InternCELL");
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/i2.png")));
            Parent root = FXMLLoader.load(getClass().getResource("/fxml"+fxmlName));
            Scene addFrame = new Scene(root);
            stage.setResizable(false);
            stage.setScene(addFrame);     
            stage.show();
        } catch (IOException e) {
            logger.warn("Can't load fxml file: ", e);
        }
    }
    /*static public Parent stageSetterWithReturn(String fxmlName, Stage stage) {
        try {
            stage.setTitle("InternCELL");
            stage.getIcons().add(new Image(SetStage.class.getResourceAsStream("/i2.png")));
            FXMLLoader loader = FXMLLoader.load(SetStage.class.getResource("/fxml"+fxmlName));
            Parent root = (Parent) loader.load();
            Scene addFrame = new Scene(root);
            stage.setScene(addFrame);     
            stage.show();
            return root;
        } catch (IOException e) {
            logger.warn("Can't load fxml file: ", e);
            return null;
        }
    }*/
}
