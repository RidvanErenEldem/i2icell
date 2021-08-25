package com.interncell;

import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Validation {
    private Validation() {}
    static final Logger logger = LogManager.getLogger(Validation.class);
    public static boolean validate(String text, String whichRegex) 
    {
        String regex = null;
        switch (whichRegex) {
            case "msisdn": 
                regex = "5[0,3,4,5,6][0-9]\\d\\d\\d\\d\\d\\d\\d$";
                break;
            case "email":
                regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
                break;
            default:
                return false;
        }
        var pattern = Pattern.compile(regex);
        var matcher = pattern.matcher(text);
        if(matcher.matches())
            return true;
        else
            return false;
    }
}
