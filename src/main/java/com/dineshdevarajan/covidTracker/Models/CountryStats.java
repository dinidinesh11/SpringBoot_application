package com.dineshdevarajan.covidTracker.Models;

public class CountryStats {
    private String state;
    private String country;
    private int lastUpdated;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(int lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public String toString() {
        return "CountryStats{" +
                "state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", lastUpdated=" + lastUpdated +
                '}';
    }
}
