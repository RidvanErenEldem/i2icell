package com.interncell.api;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.interncell.models.ChangePasswordConfirmation;
import com.interncell.models.ForgotPasswordConfirm;
import com.interncell.models.ForgotPasswordResponse;
import com.interncell.models.Packages;
import com.interncell.models.Register;
import com.interncell.models.RegisterConfirm;
import com.interncell.models.RegisterConfirmResult;
import com.interncell.models.RegisterResult;
import com.interncell.models.User;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.interncell.models.Package;



public class ApiConnector {
    static final Logger logger = LogManager.getLogger(ApiConnector.class);
    private String url;

    public User login(String path, String msisdn, String password) throws IOException {
        URL url = new URL(getUrl()+path);
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

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String output;
        StringBuffer response = new StringBuffer();

        while ((output = in.readLine()) != null) {
            response.append(output);
        }
        in.close();
        Gson g = new Gson();
        User user = g.fromJson(response.toString(), User.class);
        return user;
    }

    public List<Package> getPackages(String path, User user) throws IOException {

        URL url = new URL(getUrl()+path);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Authorization", "Bearer "+user.getJwt());
        int responseCode = con.getResponseCode();
        logger.info("Get packages response code is: ", responseCode);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String output;
        StringBuffer response = new StringBuffer();

        while ((output = in.readLine()) != null) {
            response.append(output);
        }
        in.close();

        final ObjectMapper objectMapper = new ObjectMapper();
        Packages packages = objectMapper.readValue(response.toString(), Packages.class);

        return packages.getPackages();
    }

    public RegisterResult register(String path, Register register) throws IOException {
        URL url = new URL(getUrl()+path);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Content-Type", "application/json; utf-8");

        con.setDoOutput(true);
        String jsonInputString = new ObjectMapper().writeValueAsString(register);

        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(jsonInputString);
        wr.flush();
        wr.close();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String output;
        StringBuffer response = new StringBuffer();

        while ((output = in.readLine()) != null) {
            response.append(output);
        }
        in.close();
        Gson g = new Gson();
        return g.fromJson(response.toString(), RegisterResult.class);
    }

    public boolean registerConfirm(String path, RegisterConfirm rc) throws IOException {
        URL url = new URL(getUrl()+path);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Content-Type", "application/json; utf-8");

        con.setDoOutput(true);
        String jsonInputString = new ObjectMapper().writeValueAsString(rc);

        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(jsonInputString);
        wr.flush();
        wr.close();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String output;
        StringBuffer response = new StringBuffer();

        while ((output = in.readLine()) != null) {
            response.append(output);
        }
        in.close();
        Gson g = new Gson();
        RegisterConfirmResult rcr = new RegisterConfirmResult();
        rcr = g.fromJson(response.toString(), RegisterConfirmResult.class);
        return rcr.isRegisterSuccess();
    }

    public boolean forgotPassword(String path , String email) throws IOException {
        URL url = new URL(getUrl()+path);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Content-Type", "application/json; utf-8");

        con.setDoOutput(true);
        String jsonInputString = "{\"email\":\"" + email + "\"}";
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(jsonInputString);
        wr.flush();
        wr.close();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String output;
        StringBuffer response = new StringBuffer();

        while ((output = in.readLine()) != null) {
            response.append(output);
        }
        in.close();
        Gson g = new Gson();
        ForgotPasswordResponse fpr = g.fromJson(response.toString(), ForgotPasswordResponse.class);
        return fpr.isForgotPassswordCodeGenerated();
    }

    public boolean changePasswordStatus(String path, ForgotPasswordConfirm fpc) throws IOException {
        URL url = new URL(getUrl()+path);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Content-Type", "application/json; utf-8");

        con.setDoOutput(true);
        String jsonInputString = new ObjectMapper().writeValueAsString(fpc);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(jsonInputString);
        wr.flush();
        wr.close();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String output;
        StringBuffer response = new StringBuffer();

        while ((output = in.readLine()) != null) {
            response.append(output);
        }
        in.close();
        Gson g = new Gson();
        ChangePasswordConfirmation cpc = g.fromJson(response.toString(), ChangePasswordConfirmation.class);
        return cpc.isPasswordChangeSuccessful();
    }

    public ApiConnector(String url) {
        setUrl(url);
    }

    public ApiConnector() {}

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
