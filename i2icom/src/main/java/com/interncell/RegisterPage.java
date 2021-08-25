package com.interncell;

import javafx.stage.Stage;


public class RegisterPage {
    public void start(Stage stage) {
        var registerStage = new SetStage();
        registerStage.stageSetter("/registerPage.fxml", stage);
    }
}
