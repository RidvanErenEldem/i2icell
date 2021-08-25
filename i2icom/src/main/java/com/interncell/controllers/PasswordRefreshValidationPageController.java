package com.interncell.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.interncell.PasswordChangePage;
import com.interncell.models.ForgotPasswordConfirm;
import com.interncell.models.ForgotPasswordConfirmHolder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class PasswordRefreshValidationPageController implements Initializable {
    @FXML
    private TextField code;
    @FXML
    private Label message;
    private ForgotPasswordConfirm fpc = new ForgotPasswordConfirm();

    public void serForgotPasswordConfirm(ForgotPasswordConfirm fpc) {
        this.fpc = fpc;
    }

    public ForgotPasswordConfirm getForgotPasswordConfirm() {
        return fpc;
    }

    @FXML
    protected void validateAction(ActionEvent event) {
        System.out.println("Hey dude");
        String code = this.code.getText();
        if (code == null || code.length() == 0) {
            message.setText("*Invalid e-mail.\n\tPlease try again.");
            message.setTextFill(Color.rgb(119, 0, 0));
        } else {
            fpc.setCodeReceivedViaEmail(code);
            var stage = new Stage();
            new PasswordChangePage().start(stage, fpc);
            Node source = (Node) event.getSource();
            var mainStage = (Stage) source.getScene().getWindow();
            mainStage.close();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ForgotPasswordConfirmHolder holder = ForgotPasswordConfirmHolder.getInstance();
        fpc = holder.getForgotPasswordConfirm();
    }
}
