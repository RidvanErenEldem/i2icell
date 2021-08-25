package com.interncell;

import javafx.stage.Stage;

public class LoginPage {
    public void start(Stage stage) {
        var loginStage = new SetStage();
        loginStage.stageSetter("/loginPage.fxml", stage);
    }
}