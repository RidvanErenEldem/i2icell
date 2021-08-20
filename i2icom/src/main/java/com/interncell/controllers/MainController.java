package com.interncell.controllers;

import com.interncell.RegisterPage;
import com.interncell.LoginPage;

import javafx.event.ActionEvent;  
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.Node;

public class MainController {
    @FXML protected void registerAction(ActionEvent event) 
    {
        String whatPage = "register";
        loadStage(whatPage,event);
    }
    @FXML protected void loginAction(ActionEvent event)
    {
        String whatPage = "login";
        loadStage(whatPage,event);
    }
    

    public void loadStage(String whatPage, ActionEvent event)
    {
        var stage = new Stage();
        if(whatPage == "register")
            new RegisterPage().start(stage);
        else if(whatPage == "login")
            new LoginPage().start(stage);
        Node source = (Node)event.getSource(); 
        var mainStage = (Stage) source.getScene().getWindow();
        mainStage.close();
    }
}