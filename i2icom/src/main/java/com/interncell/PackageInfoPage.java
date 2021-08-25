package com.interncell;

import java.io.IOException;
import java.util.List;

import com.interncell.api.ApiConnector;
import com.interncell.models.PackageHolder;
import com.interncell.models.User;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.stage.Stage;

public class PackageInfoPage {
    static final Logger logger = LogManager.getLogger(PackageInfoPage.class);
    public void start(Stage stage, User user) {
        var api = new ApiConnector("http://localhost:8080/api");
        List<com.interncell.models.Package> packg;
        try {
            var holder = PackageHolder.getInstance();
            packg = api.getPackages("/users/"+user.getUserId()+"/package",user);
            holder.setPackage(packg);
            var packageInfoStage = new SetStage();
            packageInfoStage.stageSetter("/packageInfoPage.fxml", stage);
        } catch (IOException e) {
            logger.error("Can't load fxml file ", e);
        }
        
    }
}
