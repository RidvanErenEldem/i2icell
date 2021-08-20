package com.interncell.models;

public class ForgotPasswordConfirm {
    private String email;
    private String codeReceivedViaEmail;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCodeReceivedViaEmail() {
        return codeReceivedViaEmail;
    }

    public void setCodeReceivedViaEmail(String codeReceivedViaEmail) {
        this.codeReceivedViaEmail = codeReceivedViaEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
