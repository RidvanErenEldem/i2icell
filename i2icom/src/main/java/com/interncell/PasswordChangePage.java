package com.interncell;

import com.interncell.models.ForgotPasswordConfirm;
import com.interncell.models.ForgotPasswordConfirmHolder;

import javafx.stage.Stage;

public class PasswordChangePage {
    public void start(Stage stage, ForgotPasswordConfirm fpc){
        var holder = ForgotPasswordConfirmHolder.getInstance();
        holder.setForgotPasswordConfirm(fpc);
        var passwordRefreshValidationStage = new SetStage();
        passwordRefreshValidationStage.stageSetter("/passwordChangePage.fxml", stage);
    }
}
