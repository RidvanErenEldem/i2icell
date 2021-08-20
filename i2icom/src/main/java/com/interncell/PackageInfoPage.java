package com.interncell;

import java.io.IOException;
import java.util.List;

import com.interncell.api.ApiConnector;
import com.interncell.controllers.PackageInfoPageController;
import com.interncell.models.PackageHolder;
import com.interncell.models.Packages;
import com.interncell.models.User;

import javafx.stage.Stage;

public class PackageInfoPage {
    public void start(Stage stage, User user) {
        Packages packages = new Packages();
        ApiConnector api = new ApiConnector("http://localhost:8080/api");
        
        List<com.interncell.models.Package> packg;
        try {
            PackageHolder holder = PackageHolder.getInstance();
            packg = api.getPackages("/users/"+user.getUserId()+"/package",user);
            holder.setPackage(packg);
            SetStage packageInfoStage = new SetStage();
            packageInfoStage.stageSetter("/packageInfoPage.fxml", stage);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
}
