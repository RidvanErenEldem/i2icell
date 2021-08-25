package com.interncell.controllers;

import com.interncell.PackageInfoPage;
import com.interncell.models.User;
import com.interncell.models.UserHolder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class InfoPageController{
    @FXML private Label name;
    @FXML private Label lastName;
    @FXML private Label msisdn;
    @FXML private Label email;
    private User user = new User();

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @FXML private void showAction(ActionEvent event)
    {
        var stage = new Stage();
        new PackageInfoPage().start(stage, user);
        Node source = (Node)event.getSource(); 
        var infoPageStage = (Stage) source.getScene().getWindow();
        infoPageStage.close();
    }

    public void initialize() {
        var holder = UserHolder.getInstance();
        user = holder.getUser();
        name.setText(user.getName());
        lastName.setText(user.getLastName());
        msisdn.setText(user.getMsisdn());
        email.setText(user.getEmail());
    }
}
