package com.interncell.controllers;

import com.interncell.Validation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;

public class RegisterPageController {
    @FXML private TextField name;
    @FXML private TextField email;
    @FXML private TextField msisdn;
    @FXML private PasswordField password;
    @FXML private PasswordField passwordAgain;
    @FXML private Rectangle confirmFrame;
    @FXML private Label confirmLabel;
    @FXML private Button confirmButton;


    static final Logger logger = LogManager.getLogger(RegisterPageController.class);

    @FXML protected void registerBtnAction(ActionEvent event)
    {
        String debugText = "Register "+name.getText()+" "+email.getText()+" "+msisdn.getText()+" "+password.getText()+" "+passwordAgain.getText();
        logger.info(debugText);
        showUnshowMessageBox(true);
    }
    @FXML protected void emailKeyTyped(KeyEvent event)
    {
        String regularExpression = "email";
        boolean validationResult = Validation.validate(email.getText(), regularExpression);
        logger.info("email is: "+validationResult);
    }
    @FXML protected void msisdnKeyTyped(KeyEvent event)
    {
        String regularExpression = "msisdn";
        boolean validationResult = Validation.validate(msisdn.getText(), regularExpression);
        logger.info("msisdn is: "+ validationResult);
    }
    @FXML protected void confirmButtonAction(ActionEvent event)
    {
        logger.info("confirmButton Action");
        showUnshowMessageBox(false);
    }
    public void showUnshowMessageBox(boolean state)
    {
        if(state)
        {
            confirmFrame.toFront();
            confirmFrame.setVisible(true);
            confirmFrame.setDisable(false);

            confirmLabel.toFront();
            confirmLabel.setVisible(true);
            confirmLabel.setDisable(false);
        
            confirmButton.toFront();
            confirmButton.setVisible(true);
            confirmButton.setDisable(false);
        }
        else
        {
            confirmFrame.toBack();
            confirmFrame.setVisible(false);
            confirmFrame.setDisable(true);
            
            confirmLabel.toBack();
            confirmLabel.setVisible(false);
            confirmLabel.setDisable(true);

            confirmButton.toBack();
            confirmButton.setVisible(false);
            confirmButton.setDisable(true);
        }
        
    }
}
