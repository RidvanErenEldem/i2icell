package com.interncell.models;

public class Package {
    private String packageType;
    private String packageName;
    private int usedAmount;
    private int remainedAmount;
    private String endDate;
    private String businessZone;
    private boolean visible;

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public int getUsedAmount() {
        return usedAmount;
    }

    public void setUsedAmount(int usedAmount) {
        this.usedAmount = usedAmount;
    }

    public int getRemainedAmount() {
        return remainedAmount;
    }

    public void setRemainedAmount(int remainedAmount) {
        this.remainedAmount = remainedAmount;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getBusinessZone() {
        return businessZone;
    }

    public void setBusinessZone(String businessZone) {
        this.businessZone = businessZone;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Override
    public String toString() {
        return "Package{" +
                "packageType='" + packageType + '\'' +
                ", packageName='" + packageName + '\'' +
                ", usedAmount=" + usedAmount +
                ", remainedAmount=" + remainedAmount +
                ", endDate='" + endDate + '\'' +
                ", businessZone='" + businessZone + '\'' +
                ", visible=" + visible +
                '}';
    }
}
