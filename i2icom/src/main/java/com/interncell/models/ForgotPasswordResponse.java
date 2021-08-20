package com.interncell.models;

public class ForgotPasswordResponse {
    private boolean forgotPassswordCodeGenerated;
    
    public boolean isForgotPassswordCodeGenerated() {
        return forgotPassswordCodeGenerated;
    }

    public void setForgotPassswordCodeGenerated(boolean forgotPassswordCodeGenerated) {
        this.forgotPassswordCodeGenerated = forgotPassswordCodeGenerated;
    }
}
