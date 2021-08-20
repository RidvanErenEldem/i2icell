package com.interncell.models;

public class RegisterResult {
    private int registerConfirmationId;
    private boolean registerRequestSuccess;


    public int getRegisterConfirmationId() {
        return registerConfirmationId;
    }

    public void setRegisterConfirmationId(int registerConfirmationId) {
        this.registerConfirmationId = registerConfirmationId;
    }

    public boolean isRegisterRequestSuccess() {
        return registerRequestSuccess;
    }

    public void setRegisterRequestSuccess(boolean registerRequestSuccess) {
        this.registerRequestSuccess = registerRequestSuccess;
    }
}
