package com.interncell;

import com.interncell.models.ForgotPasswordConfirm;
import com.interncell.models.ForgotPasswordConfirmHolder;

import javafx.stage.Stage;

public class PasswordRefreshValidationPage {
    public void start(Stage stage, ForgotPasswordConfirm fpc){
        ForgotPasswordConfirmHolder holder = ForgotPasswordConfirmHolder.getInstance();
        holder.setForgotPasswordConfirm(fpc);
        SetStage passwordRefreshValidationStage = new SetStage();
        passwordRefreshValidationStage.stageSetter("/passwordRefreshValidation.fxml", stage);
    }
}
