package com.interncell.controllers;

import java.io.IOException;

import com.interncell.InfoPage;
import com.interncell.PasswordRefreshEmail;
import com.interncell.Validation;
import com.interncell.api.ApiConnector;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LoginPageController {
    @FXML private TextField msisdn;
    @FXML private PasswordField password;
    @FXML private Label passwordLabel;
    @FXML private Label wrongText;
    static boolean isWrongAgain = false;

    static final Logger logger = LogManager.getLogger(LoginPageController.class);
    @FXML protected void loginBtnAction(ActionEvent event)
    {
        String debugText = "Login: " + msisdn.getText() + " " + password.getText();
        logger.debug(debugText);
        var stage = new Stage();
        var api = new ApiConnector("http://localhost:8080/api");
        try {
            String endPoint = "/login";
            var user = api.login(endPoint, msisdn.getText(), password.getText());
            if(user.isLoginSuccess())
            {
                logger.info(user.getEmail() +" "+ user.getName()+ " "+user.getLastName()+ " "+ user.getMsisdn() +" "+ user.getUserId());
                new InfoPage().start(stage, user);
                Node source = (Node)event.getSource(); 
                var mainStage = (Stage) source.getScene().getWindow();
                mainStage.close();
            }
            else if (!isWrongAgain)
            {
                String message = "*Wrong phone number or password";
                int move = -20;
                getErrorText(move, message);
                logger.info("wrong");
                isWrongAgain = true;
            }
        } 
        catch (IOException e) 
        {
            logger.error("IOException: ",e);
        }
    }
    


    @FXML protected void msisdnKeyTyped(KeyEvent event)
    {
        String regularExpression = "msisdn";
        boolean validationResult = Validation.validate(msisdn.getText(), regularExpression);
        logger.info("msisdn is: "+validationResult );
    }

    private void getErrorText(int layoutMove, String setText)
    {
        wrongText.setText(setText);
        passwordLabel.setLayoutY(passwordLabel.getLayoutY()  + layoutMove);
        password.setLayoutY(password.getLayoutY()+ layoutMove);
    }

    @FXML protected void forgotClick(MouseEvent event)
    {
        logger.debug("forget click");
        var stage = new Stage();
        new PasswordRefreshEmail().start(stage);
        Node source = (Node)event.getSource();
        var mainStage = (Stage) source.getScene().getWindow();
        mainStage.close();
    }
}
