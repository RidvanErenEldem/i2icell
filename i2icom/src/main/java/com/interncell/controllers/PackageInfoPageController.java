package com.interncell.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.interncell.models.Package;
import com.interncell.models.PackageHolder;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class PackageInfoPageController implements Initializable {
    @FXML
    private AnchorPane anchor;
    @FXML private Label voiceLabel;
    @FXML private Label smsLabel;
    @FXML private Label dataLabel;
    @FXML private Polygon voicePolygon;
    @FXML private Polygon smsPolygon;
    @FXML private Polygon dataPolygon;
    private List<Package> pack;

    public void setPackages(List<Package> pack) {
        this.pack = pack;
    }

    public List<Package> getPackages() {
        return pack;
    }

    public void CreateRectangle(int x, int y, int width, int height) {
        Rectangle rectangle = new Rectangle(x, y, width, height);
        rectangle.setFill(Color.WHITE);
        rectangle.setArcHeight(0);
        rectangle.setArcWidth(0);
        rectangle.setStroke(Color.WHITE);
        rectangle.setStrokeWidth(15);
        rectangle.setStrokeType(StrokeType.CENTERED);
        rectangle.setStrokeLineCap(StrokeLineCap.ROUND);
        rectangle.setStrokeLineJoin(StrokeLineJoin.ROUND);
        rectangle.setStrokeMiterLimit(10);
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.rgb(0, 0, 0, .25));
        shadow.setOffsetX(5);
        shadow.setOffsetY(5);
        shadow.setRadius(10);
        shadow.setBlurType(BlurType.TWO_PASS_BOX);
        rectangle.setEffect(shadow);
        rectangle.toBack();
        anchor.getChildren().add(rectangle);
    }

    private void CreateLabel(String text, String fontWeight,int x, int y, int size)
    {
        Label label = new Label(text);
        label.setLayoutX(x);
        label.setLayoutY(y);
        label.setFont(fontSetter(fontWeight,size));
        anchor.getChildren().add(label);
    }

    private void CreateCircleBar(int x, int y, double usedAmount, double remainingAmount)
    {
        double totalAmount = remainingAmount+usedAmount;
        double percentage  = 100*usedAmount/totalAmount;
        Circle outerCircle = new Circle(x,y,50);
        LinearGradient g = LinearGradient.valueOf(
    "from 0.0% 0.0% to 0.0% 100.0% "+    // from top to bottom
    "rgb(148, 0, 0) 0%, "+               // red at the top
    "rgb(148, 0, 0) "+percentage+"%, "+  // red at percentage
    "rgb(14, 147, 0) "+percentage+"%, "+ // green at percentage
    "rgb(14, 147, 0) 100%"               // green at the bottom
);
        outerCircle.setFill(g);
        anchor.getChildren().add(outerCircle);
        Circle innerCircle = new Circle(x,y,39);
        innerCircle.setFill(Color.WHITE);
        anchor.getChildren().add(innerCircle);
    }
    private Font fontSetter(String type, int size)
    {
        if(type.equals("BOLD"))
            return Font.font("System",FontWeight.BOLD,size);
        else
            return Font.font("System",size);
    }

    private String DateShortener (String Date)
    {
        final String Seprator = "T";
        int DateSepratorIndex = Date.indexOf(Seprator);
        return Date.substring(0,DateSepratorIndex);
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        PackageHolder holder = PackageHolder.getInstance();
        pack = holder.getPackage();
        System.out.println(pack.toString());
        System.out.println("hey anchor");
        //String green = "rgb(14,170,0) ";
        //String red = "rgb(148,0,0)";
        int forVoice = 0;
        int forSms = 0;
        int forData = 0;
        int RectangleXPositon = 83;
        int RectangleYPosition = 184;
        int EndDateXPosition = 315;
        int EndDateYPosition = 184;
        int InfoXPosition = 265;
        int InfoYPosition = 220;
        int TitleXPositon = 92;
        int TitleYPositon = 179;
        int CircleXPosition = 152;
        int CircleYPosition = 254;
        String EndDate;
        
        for (int i = 0; i < pack.size(); i++) {
            String packageType = pack.get(i).getPackageType();
            if ("v".equals(packageType)) 
            {
                String usedMinutes;
                String remainingMinutes;
                String title;
                if(forVoice == 0)
                {
                    EndDateXPosition = 315;
                    RectangleXPositon = 83;
                    InfoXPosition = 265;
                    TitleXPositon = 92;
                    CircleXPosition = 152;
                }
                else if (forVoice%3 == 0)
                {
                    RectangleYPosition = RectangleYPosition + 191;
                    EndDateYPosition = EndDateYPosition + 191;
                    InfoYPosition = InfoYPosition + 191;
                    TitleYPositon = TitleYPositon + 191;
                    CircleYPosition = CircleYPosition + 191;
                    smsPolygon.setLayoutY(smsPolygon.getLayoutY()+191);
                    smsLabel.setLayoutY(smsLabel.getLayoutY()+191);
                    dataPolygon.setLayoutY(dataPolygon.getLayoutY()+191);
                    dataLabel.setLayoutY(dataLabel.getLayoutY()+191);
                }
                CreateRectangle(RectangleXPositon,RectangleYPosition,321,140);
                CreateLabel("Package Ends", "REGULAR",EndDateXPosition,EndDateYPosition,14);
                EndDate = DateShortener(pack.get(i).getEndDate());
                CreateLabel(EndDate, "REGULAR", EndDateXPosition,EndDateYPosition+16,14);
                CreateLabel("Used Minutes", "REGULAR",InfoXPosition,InfoYPosition,18);
                usedMinutes = (Integer.valueOf(pack.get(i).getUsedAmount())).toString();
                CreateLabel(usedMinutes,"BOLD",InfoXPosition,InfoYPosition+20,18);
                CreateLabel("Remaining", "REGULAR",InfoXPosition,InfoYPosition+40,18);
                remainingMinutes = (Integer.valueOf(pack.get(i).getRemainedAmount()).toString());
                CreateLabel(remainingMinutes,"BOLD",InfoXPosition,InfoYPosition+60,18);
                title = pack.get(i).getPackageName();
                CreateLabel(title,"BOLD",TitleXPositon,TitleYPositon,18);
                CreateCircleBar(CircleXPosition, CircleYPosition,Integer.parseInt(usedMinutes),Integer.parseInt(remainingMinutes));
                forVoice++;
            } 
            else if ("s".equals(packageType))
            {
                String usedSms;
                String remainSms;
                String title;
                if(forSms == 0)
                {
                    EndDateXPosition = 315;
                    EndDateYPosition = EndDateYPosition + 191;
                    RectangleXPositon = 83;
                    RectangleYPosition = RectangleYPosition + 191;
                    InfoXPosition = 265;
                    InfoYPosition = InfoYPosition + 191;
                    TitleXPositon = 92;
                    TitleYPositon = TitleYPositon + 191;
                    CircleXPosition = 152;
                    CircleYPosition = CircleYPosition + 191;
                }
                else if(forSms%3 == 0)
                {
                    RectangleYPosition = RectangleYPosition + 191;
                    EndDateYPosition = EndDateYPosition + 191;
                    InfoYPosition = InfoYPosition + 191;
                    TitleYPositon = TitleYPositon + 191;
                    CircleYPosition = CircleYPosition + 191;
                    dataPolygon.setLayoutY(dataPolygon.getLayoutY()+191);
                    dataLabel.setLayoutY(dataLabel.getLayoutY()+191);
                }
                CreateRectangle(RectangleXPositon,RectangleYPosition,321,140);
                CreateLabel("Package Ends", "REGULAR",EndDateXPosition,EndDateYPosition,14);
                EndDate = DateShortener(pack.get(i).getEndDate());
                CreateLabel(EndDate, "REGULAR", EndDateXPosition,EndDateYPosition+16,14);
                CreateLabel("Used Sms","REGULAR",InfoXPosition,InfoYPosition,18);
                usedSms = (Integer.valueOf(pack.get(i).getUsedAmount()).toString());
                CreateLabel(usedSms, "BOLD",InfoXPosition,InfoYPosition+20,18);
                CreateLabel("Remaining", "REGULAR",InfoXPosition,InfoYPosition+40,18);
                remainSms = (Integer.valueOf(pack.get(i).getRemainedAmount()).toString());
                CreateLabel(remainSms,"BOLD",InfoXPosition,InfoYPosition+60,18);
                title = pack.get(i).getPackageName();
                CreateLabel(title, "BOLD",TitleXPositon,TitleYPositon,18);
                CreateCircleBar(CircleXPosition, CircleYPosition,Integer.parseInt(usedSms),Integer.parseInt(remainSms));
                forSms++;
            } 
            else if ("d".equals(packageType)) 
            {
                String usedMB;
                String remainMB;
                String title;
                if(forData == 0)
                {
                    EndDateXPosition = 315;
                    EndDateYPosition = EndDateYPosition + 191;
                    RectangleXPositon = 83;
                    RectangleYPosition = RectangleYPosition + 191;
                    InfoXPosition = 265;
                    InfoYPosition = InfoYPosition + 191;
                    TitleXPositon = 92;
                    TitleYPositon = TitleYPositon + 191;
                    CircleXPosition = 152;
                    CircleYPosition = CircleYPosition + 191;
                }
                else if(forData%3 == 0)
                {
                    RectangleYPosition = RectangleYPosition + 191;
                    EndDateYPosition = EndDateYPosition + 191;
                    InfoYPosition = InfoYPosition + 191;
                    TitleYPositon = TitleYPositon + 191;
                    CircleYPosition = CircleYPosition + 191;
                }
                CreateRectangle(RectangleXPositon,RectangleYPosition,321,140);
                CreateLabel("Package Ends", "REGULAR",EndDateXPosition,EndDateYPosition,14);
                EndDate = DateShortener(pack.get(i).getEndDate());
                CreateLabel(EndDate, "REGULAR", EndDateXPosition,EndDateYPosition+16,14);
                CreateLabel("Used MB", "REGULAR",InfoXPosition,InfoYPosition,18);
                usedMB = (Integer.valueOf(pack.get(i).getUsedAmount())).toString();
                CreateLabel(usedMB,"BOLD",InfoXPosition,InfoYPosition+20,18);
                CreateLabel("Remaining", "REGULAR",InfoXPosition,InfoYPosition+40,18);
                remainMB = (Integer.valueOf(pack.get(i).getRemainedAmount()).toString());
                CreateLabel(remainMB,"BOLD",InfoXPosition,InfoYPosition+60,18);
                title = pack.get(i).getPackageName();
                CreateLabel(title,"BOLD",TitleXPositon,TitleYPositon,18);
                CreateCircleBar(CircleXPosition, CircleYPosition,Integer.parseInt(usedMB),Integer.parseInt(remainMB));
                forData++;
            }
            RectangleXPositon = RectangleXPositon + 387;
            EndDateXPosition = EndDateXPosition + 387;
            InfoXPosition = InfoXPosition + 387;
            TitleXPositon = TitleXPositon + 387;
            CircleXPosition = CircleXPosition + 387;
            if(RectangleXPositon > 857)
                RectangleXPositon = 83;
            if(EndDateXPosition> 1089)
                EndDateXPosition = 315;
            if(InfoXPosition > 1039)
                InfoXPosition = 265;
            if(TitleXPositon > 866)
                TitleXPositon = 92;
            if(CircleXPosition > 1312)
                CircleXPosition = 152;
        }
    }

}
