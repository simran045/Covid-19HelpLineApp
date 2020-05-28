package com.example.covid_19helpline;

public class ExampleItem {

    private String location,number;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public ExampleItem(String location, String number) {
        this.location = location;
        this.number = number;
    }
}
