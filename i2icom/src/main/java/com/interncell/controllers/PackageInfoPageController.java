package com.interncell.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.interncell.models.Package;
import com.interncell.models.PackageHolder;
import com.interncell.models.Packages;
import com.interncell.models.User;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

public class PackageInfoPageController implements Initializable {
    @FXML
    private AnchorPane anchor;
    @FXML
    private List<Circle> circleList;
    @FXML
    private List<Label> endDateList;
    @FXML
    private List<Label> usedMBs;
    @FXML
    private List<Label> usedMinutes;
    @FXML
    private List<Label> usedSmss;
    @FXML
    private List<Label> remainMinutes;
    @FXML
    private List<Label> remainMBs;
    @FXML
    private List<Label> remainSmss;
    private List<Package> pack; 

    public void setPackages(List<Package> pack) {
        this.pack = pack;
    }

    public List<Package> getPackages() {
        return pack;
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        PackageHolder holder = PackageHolder.getInstance();
        pack = holder.getPackage();
        System.out.println(pack.toString());
        System.out.println("hey anchor");
        String green = "rgb(14,170,0) ";
        String red = "rgb(148,0,0)";
        int forVoice = 0;
        int forSms = 0;
        int forData = 0;
        int remain;
        int used;
        int total;
        for(int i=0; i<pack.size();i++)
        {
            String packageType = pack.get(i).getPackageType();
            if("v".equals(packageType))
            {
                remain = pack.get(i).getUsedAmount();
                used = pack.get(i).getRemainedAmount();
                usedMinutes.get(forVoice).setText(Integer.valueOf(remain).toString());
                remainMinutes.get(forVoice).setText(Integer.valueOf(used).toString());
                total = remain/used;
                circleList.get(i).setStyle("-fx-fill:linear-gradient(" + red + (1-total)+"," + green + total+");");
                forVoice++;
            }
            else if("s".equals(packageType))
            {
                usedSmss.get(forSms).setText(Integer.valueOf(pack.get(i).getUsedAmount()).toString());
                remainSmss.get(forSms).setText(Integer.valueOf(pack.get(i).getRemainedAmount()).toString());
                forSms++;
            }
            else if("d".equals(packageType))
            {
                usedMBs.get(forData).setText(Integer.valueOf(pack.get(i).getUsedAmount()).toString());
                remainMBs.get(forData).setText(Integer.valueOf(pack.get(i).getRemainedAmount()).toString());
                forData++;
            }
            String date = pack.get(i).getEndDate();
            endDateList.get(i).setText(date);
            
        }
        for (Circle circle : circleList) {
            circle.setStyle("-fx-fill:linear-gradient(" + red + "0.5," + green + " 0.5);");
        }
    }

}
