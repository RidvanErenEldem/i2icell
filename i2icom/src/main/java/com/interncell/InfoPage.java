package com.interncell;

import com.interncell.models.User;
import com.interncell.models.UserHolder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.stage.Stage;

public class InfoPage {
    static final Logger logger = LogManager.getLogger(InfoPage.class);
    public void start(Stage stage, User user) {
        try {
            var holder = UserHolder.getInstance();
            holder.setUser(user);
            var infoStage = new SetStage();
            infoStage.stageSetter("/infoPage.fxml", stage);
        }
        catch (Exception e) {
            logger.error("Error: ", e);
        }
    }
}
