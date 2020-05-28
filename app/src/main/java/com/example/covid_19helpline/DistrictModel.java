package com.example.covid_19helpline;

public class DistrictModel {

    private String district;
    private String cases;
    private String deaths;
    private String recovered;
    private String active;

    public DistrictModel() {
    }

    public DistrictModel(String district, String cases, String deaths, String recovered, String active) {
        this.district = district;
        this.cases = cases;
        this.deaths = deaths;
        this.recovered = recovered;
        this.active = active;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCases() {
        return cases;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
}