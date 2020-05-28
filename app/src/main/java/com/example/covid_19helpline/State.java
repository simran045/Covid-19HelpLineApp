package com.example.covid_19helpline;

public class State {
    private String state;
    //private String district;

    public State(){}

    public State( String state)
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
