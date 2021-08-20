package com.interncell;

import com.interncell.models.User;
import com.interncell.models.UserHolder;

import javafx.stage.Stage;

public class InfoPage {
    public void start(Stage stage, User user) {
        try {
            UserHolder holder = UserHolder.getInstance();
            holder.setUser(user);
            SetStage infoStage = new SetStage();
            infoStage.stageSetter("/infoPage.fxml", stage);
        }
        catch (Exception e) {
            System.out.println("Err"+ e.getMessage());
        }
    }
}
