package com.example.pillstime;

import java.util.ArrayList;

public class Medecin {
    private String name ;
    private String how ;
    private String phone_number;
    private int pills ;
    private String from ;
    private String to ;


    private ArrayList<String> times = new ArrayList<>();

    public ArrayList<String> getTimes() {
        return times;
    }

    public void setTimes(ArrayList<String> times) {
        this.times = times;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String  getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHow() {
        return how;
    }

    public void setHow(String how) {
        this.how = how;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public int getPills() {
        return pills;
    }

    public void setPills(int pills) {
        this.pills = pills;
    }

}
