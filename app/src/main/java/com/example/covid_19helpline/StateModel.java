package com.example.covid_19helpline;

public class StateModel {

    private String state;
    private int cases;
    private int deaths;
    private int recovered;

    public StateModel() {
    }

    public StateModel(String state, int cases, int deaths, int recovered) {
        this.state = state;
        this.cases = cases;
        this.deaths = deaths;
        this.recovered = recovered;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getCases() {
        return cases;
    }

    public void setCases(int cases) {
        this.cases = cases;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getRecovered() {
        return recovered;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }
}