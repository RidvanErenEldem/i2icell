package com.interncell;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Main extends Application {
    static final Logger logger = LogManager.getLogger(Main.class);
    @FXML private ImageView imageView;
    @Override
    public void start(Stage stage)
    {
        logger.info("Starting");
        var mainStage = new SetStage();
        mainStage.stageSetter("/main.fxml", stage);
    }
    public static void main(String[] args) {
        launch();
    }

}