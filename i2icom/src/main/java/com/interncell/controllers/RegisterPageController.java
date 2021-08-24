package com.interncell.controllers;

import java.io.IOException;

import com.interncell.Validation;
import com.interncell.registerValidationPage;
import com.interncell.api.ApiConnector;
import com.interncell.models.Register;
import com.interncell.models.RegisterResult;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class RegisterPageController {
    @FXML private TextField name;
    @FXML private TextField email;
    @FXML private TextField msisdn;
    @FXML private PasswordField password;
    @FXML private PasswordField passwordAgain;
    @FXML private Rectangle mainFrame;


    static final Logger logger = LogManager.getLogger(RegisterPageController.class);

    private static String getFirstName(String fullName) {
        int index = fullName.lastIndexOf(" ");
        if (index > -1) {
            return fullName.substring(0, index);
        }
        return fullName;
    }

    private static String getLastName(String fullName) {
        int index = fullName.lastIndexOf(" ");
        if (index > -1) {
            return fullName.substring(index + 1 , fullName.length());
        }
        return "";
    }

    private Register setRegister()
    {
        Register register = new Register();
        String password = this.password.getText();
        String passwordAgain = this.passwordAgain.getText();
        String name = this.name.getText();
        String email = this.email.getText();
        String msisdn = this.msisdn.getText();
        if(password == null || password.length() == 0 || passwordAgain == password)
            return null;
        else
            register.setPassword(password);
        if(name == null || name.length() == 0)
            return null;
        else
        {
            register.setName(getFirstName(name));
            register.setLastName(getLastName(name));
        }
        if(email == null || email.length() == 0)
            return null;
        else if (Validation.validate(email, "email") == false)
            return null;
        else
            register.setEmail(email);
        if(msisdn == null || msisdn.length() == 0)
            return null;
        else
            register.setMsisdn(msisdn);
        return register;
    }

    @FXML protected void registerBtnAction(ActionEvent event)
    {
        Register register = setRegister();
        logger.info(register + " "+register.getEmail());
        String debugText = "Register "+name.getText()+" "+email.getText()+" "+msisdn.getText()+" "+password.getText()+" "+passwordAgain.getText();
        logger.info(debugText);
        ApiConnector api = new ApiConnector("http://localhost:8080/api");
        try {
            RegisterResult result = api.register("/register",register);
            if(result.isRegisterRequestSuccess())
            {
                logger.info(result);
                Stage stage = new Stage();
                new registerValidationPage().start(stage, result);
                Node source = (Node)event.getSource(); 
                var registerStage = (Stage) source.getScene().getWindow();
                registerStage.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
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
}
