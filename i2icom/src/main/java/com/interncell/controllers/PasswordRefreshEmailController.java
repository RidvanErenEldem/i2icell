package com.interncell.controllers;

import com.interncell.Validation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class PasswordRefreshEmailController {
    static final Logger logger = LogManager.getLogger(PasswordRefreshEmailController.class);
    @FXML private TextField emailText;
    private boolean validationResult = false;

    @FXML protected void sendCodeAction(ActionEvent event)
    {
        
        logger.info("SendCode Action");
    }
    @FXML protected void test(KeyEvent event)
    {
        String regularExpression = "email";
        validationResult = Validation.validate(emailText.getText(), regularExpression);
        logger.info("is email: " + validationResult);
    }
}
