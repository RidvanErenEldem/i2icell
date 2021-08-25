package com.interncell;

import javafx.stage.Stage;

public class PasswordRefreshEmail {
    public void start(Stage stage){
        var passwordRefreshEmailStage = new SetStage();
        passwordRefreshEmailStage.stageSetter("/passwordRefreshEmail.fxml", stage);
    }
}