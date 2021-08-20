package com.interncell;

import javafx.stage.Stage;

public class LoginPage {
    public void start(Stage stage) {
        SetStage loginStage = new SetStage();
        loginStage.stageSetter("/loginPage.fxml", stage);
    }
}