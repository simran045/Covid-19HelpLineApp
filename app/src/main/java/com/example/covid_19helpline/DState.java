package com.example.covid_19helpline;

public class DState {
    private String state;
    //private String district;

    public DState(){}

    public DState(String state)
    {
        this.state=state;
    }


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
