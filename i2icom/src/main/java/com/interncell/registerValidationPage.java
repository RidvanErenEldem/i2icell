package com.interncell;

import com.interncell.models.RegisterResult;
import com.interncell.models.RegisterResultHolder;

import javafx.stage.Stage;

public class registerValidationPage {
    public void start(Stage stage, RegisterResult result) {
        RegisterResultHolder holder = RegisterResultHolder.getInstance();
        holder.setRegisterResult(result);
        SetStage registerValidationStage = new SetStage();
        registerValidationStage.stageSetter("/registerValidationPage.fxml", stage);
    }
}

