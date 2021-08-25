package com.interncell.models;

public class ForgotPasswordConfirmHolder {
    private ForgotPasswordConfirm forgotPasswordConfirm;

    private final static ForgotPasswordConfirmHolder INSTANCE = new ForgotPasswordConfirmHolder();

    private ForgotPasswordConfirmHolder()
    {

    }

    public static ForgotPasswordConfirmHolder getInstance()
    {
        return INSTANCE;
    }

    public void setForgotPasswordConfirm(ForgotPasswordConfirm forgotPasswordConfirm)
    {
        this.forgotPasswordConfirm = forgotPasswordConfirm;
    }

    public ForgotPasswordConfirm getForgotPasswordConfirm()
    {
        return this.forgotPasswordConfirm;
    }
}
