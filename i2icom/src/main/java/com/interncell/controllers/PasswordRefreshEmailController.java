package com.interncell.controllers;

import java.io.IOException;

import com.interncell.PasswordRefreshValidationPage;
import com.interncell.Validation;
import com.interncell.api.ApiConnector;
import com.interncell.models.ForgotPasswordConfirm;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class PasswordRefreshEmailController {
    static final Logger logger = LogManager.getLogger(PasswordRefreshEmailController.class);
    @FXML
    private TextField emailText;
    @FXML
    private Label message;
    private boolean validationResult = false;

    @FXML
    protected void sendCodeAction(ActionEvent event) {
        String regularExpression = "email";
        validationResult = Validation.validate(emailText.getText(), regularExpression);
        if (validationResult) {
            String email = emailText.getText();
            ApiConnector api = new ApiConnector("http://localhost:8080/api");
            try {
                boolean result = api.forgotPassword("/login/forgot-password", email);
                if (result) {
                    ForgotPasswordConfirm fpc = new ForgotPasswordConfirm();
                    fpc.setEmail(email);
                    var stage = new Stage();
                    new PasswordRefreshValidationPage().start(stage, fpc);
                    Node source = (Node) event.getSource();
                    var mainStage = (Stage) source.getScene().getWindow();
                    mainStage.close();
                } else {
                    message.setFont(new Font("System", 18));
                    message.setText("*Invalid e-mail.\n\tPlease try again.");
                    message.setTextFill(Color.rgb(119, 0, 0));
                    logger.info("Wrong email format");
                }

            } catch (IOException e) {
                logger.error("Error\n", e);
            }
        } else {
            message.setFont(new Font("System", 18));
            message.setText("*Invalid e-mail.\n\tPlease try again.");
            message.setTextFill(Color.rgb(119, 0, 0));
            logger.info("Wrong email format");
        }

    }

    @FXML
    protected void test(KeyEvent event) {
        String regularExpression = "email";
        validationResult = Validation.validate(emailText.getText(), regularExpression);
        logger.info("is email: " + validationResult);
    }
}
