package com.interncell.api;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import com.google.gson.Gson;
import com.interncell.models.User;


public class ApiConnector {

    private String url;

    public User isAuthenticated(String msisdn, String password) throws IOException {
        URL url = new URL(getUrl());
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Content-Type", "application/json; utf-8");

        con.setDoOutput(true);
        String jsonInputString = "{\"msisdn\":\"" + msisdn + "\", \"password\":\"" + password + "\"}";
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(jsonInputString);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
//        System.out.println("nSending 'POST' request to URL : " + url);
//        System.out.println("Post Data : " + jsonInputString);
//        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String output;
        StringBuffer response = new StringBuffer();

        while ((output = in.readLine()) != null) {
            response.append(output);
        }
        in.close();
        Gson g = new Gson();
        User user = g.fromJson(response.toString(),User.class);
//        System.out.println(user.getEmail());
//        System.out.println(response.toString());
        if (user.isLoginSuccess()){
            return user;
        }else {
            return null;
        }
    }

    public ApiConnector(String url) {
        setUrl(url);
    }

    ApiConnector() {

    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public static boolean isUrlValid(String url) {
        try {
            URL obj = new URL(url);
            obj.toURI();
            return true;
        } catch (MalformedURLException e) {
            return false;
        } catch (URISyntaxException e) {
            return false;
        }
    }
    
}
