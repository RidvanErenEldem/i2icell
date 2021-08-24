package com.interncell.models;

public class RegisterResultHolder {
    private RegisterResult registerResult;
    private final static RegisterResultHolder INSTANCE = new RegisterResultHolder();

    private RegisterResultHolder() {}

    public static RegisterResultHolder getInstance() {
        return INSTANCE;
    }

    public void setRegisterResult(RegisterResult registerResult) {
        this.registerResult = registerResult;
    }

    public RegisterResult getRegisterResult() {
        return this.registerResult;
    }

}
