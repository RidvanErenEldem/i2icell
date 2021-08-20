package com.interncell.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

public class PackageInfoPageController implements Initializable {
    @FXML private AnchorPane anchor;
    @FXML private List<Circle> circleList;
    @FXML private List<Label> endDateList;

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("hey anchor");
        String green = "rgb(14,170,0) ";
        String red = "rgb(148,0,0)";
        for(Label endDate: endDateList) {
            endDate.setText("10.08.1999");
        }
        for(Circle circle : circleList)
        {
            circle.setStyle("-fx-fill:linear-gradient("+red+ "0.6," + green+" 0.4);");
        }
    }

    
}
