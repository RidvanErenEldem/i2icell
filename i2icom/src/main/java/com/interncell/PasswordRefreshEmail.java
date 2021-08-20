package com.interncell;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PasswordRefreshEmail {
    @FXML private TextField emailText;
    public void start(Stage stage){
        SetStage passwordRefreshEmailStage = new SetStage();
        passwordRefreshEmailStage.stageSetter("/passwordRefreshEmail.fxml", stage);
        
    }
}