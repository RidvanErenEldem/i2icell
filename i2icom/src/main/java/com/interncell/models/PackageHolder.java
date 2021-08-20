package com.interncell.models;

import java.util.List;

public class PackageHolder {
    private List<Package> pack;
    private final static PackageHolder INSTANCE = new PackageHolder();

    private PackageHolder()
    {}

    public static PackageHolder getInstance(){
        return INSTANCE;
    }

    public void setPackage(List<Package> pack)
    {
        this.pack = pack;
    }

    public List<Package> getPackage()
    {
        return this.pack;
    }
}
