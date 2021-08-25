package com.interncell;

import com.interncell.models.RegisterResult;
import com.interncell.models.RegisterResultHolder;

import javafx.stage.Stage;

public class RegisterValidationPage {
    public void start(Stage stage, RegisterResult result) {
        var holder = RegisterResultHolder.getInstance();
        holder.setRegisterResult(result);
        var registerValidationStage = new SetStage();
        registerValidationStage.stageSetter("/registerValidationPage.fxml", stage);
    }
}

