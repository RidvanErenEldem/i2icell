package com.interncell;

import javafx.stage.Stage;

public class PackageInfoPage {
    public void start(Stage stage) {
        SetStage packageInfoStage = new SetStage();
        packageInfoStage.stageSetter("/packageInfoPage.fxml", stage);
    }
}
