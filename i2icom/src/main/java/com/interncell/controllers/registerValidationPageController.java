package com.interncell.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.interncell.api.ApiConnector;
import com.interncell.models.RegisterConfirm;
import com.interncell.models.RegisterResult;
import com.interncell.models.RegisterResultHolder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class registerValidationPageController implements Initializable{
    @FXML private TextField code;
    @FXML private Label message;
    static final Logger logger = LogManager.getLogger(registerValidationPageController.class);
    private RegisterResult registerResult = new RegisterResult();

    public void setResult(RegisterResult registerResult)
    {
        this.registerResult = registerResult;
    }

    public RegisterResult getResult()
    {
        return registerResult;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        RegisterResultHolder holder = RegisterResultHolder.getInstance();
        registerResult = holder.getRegisterResult();
        
    }
    @FXML protected void validateAction(ActionEvent action)
    {
        RegisterConfirm confirmation = new RegisterConfirm();
        confirmation.setCodeReceivedViaEmail(code.getText());
        int id = registerResult.getRegisterConfirmationId();
        
        ApiConnector api = new ApiConnector("http://localhost:8080/api");
        String endpoint = "/register/"+id+"/confirm";
        try {
            
            boolean success = api.registerConfirm(endpoint,confirmation);
            if(success) {
                logger.info("Register successfully");
            }
            else
            {
                message.setText("*Invalid validation code. Please try again.");
                logger.info("Register failed");
            }
                
        } catch (IOException e) {
            message.setText("Opps");
            e.printStackTrace();
        }
    }
}
