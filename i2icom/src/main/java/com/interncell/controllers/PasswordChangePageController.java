package com.interncell.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.interncell.LoginPage;
import com.interncell.api.ApiConnector;
import com.interncell.models.ForgotPasswordConfirm;
import com.interncell.models.ForgotPasswordConfirmHolder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

public class PasswordChangePageController implements Initializable {
    static final Logger logger = LogManager.getLogger(PasswordRefreshEmailController.class);
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField passwordAgain;
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
    protected void submitAction(ActionEvent event) {
        String password = this.password.getText();
        String passwordAgain = this.passwordAgain.getText();
        if (password == passwordAgain && password.length() != 0) {
            fpc.setPassword(password);
            ApiConnector api = new ApiConnector("http://localhost:8080/api");
            boolean success;
            try {
                success = api.changePasswordStatus("/login/forgot-password/change-password", fpc);
                if (success) {
                    logger.info("password changed successfuly");
                    var stage = new Stage();
                    new LoginPage().start(stage);
                    Node source = (Node) event.getSource();
                    var mainStage = (Stage) source.getScene().getWindow();
                    mainStage.close();
                } else
                {
                    message.setVisible(true);
                    message.setText("Network error please try again later.");
                }
                    
            } catch (IOException e) {

                e.printStackTrace();
            }

        } else
            message.setVisible(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ForgotPasswordConfirmHolder holder = ForgotPasswordConfirmHolder.getInstance();
        fpc = holder.getForgotPasswordConfirm();
    }

}
