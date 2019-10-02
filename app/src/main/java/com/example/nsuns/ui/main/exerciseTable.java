package com.example.nsuns.ui.main;

import java.util.ArrayList;
import java.util.Arrays;

public class exerciseTable {
    private double tm_t1;
    private double tm_t2;
    private String[][] t1Lifts;
    private String[][] t2Lifts;
    private ArrayList<Double> t1Percentages;
    private ArrayList<Double> t2Percentages;
    //ArrayList<String> accessories;;

    public exerciseTable() {
        tm_t1 = 0;
        tm_t2 = 0;
        t1Lifts = new String[9][];
        t2Lifts = new String[8][];
        t1Percentages = new ArrayList<>();
        t2Percentages = new ArrayList<>();
        //accessories = new ArrayList<>();
    }

    // constructors
    public void setTm_t1(double tm) { this.tm_t1 = tm; }
    public double getTm_t1() { return tm_t1; }
    public void setTm_t2(double tm) { this.tm_t2 = tm; }
    public double getTm_t2() { return tm_t2; }
    public String[][] gett1Lifts() { return this.t1Lifts; }
    public String[][] gett2Lifts() { return this.t2Lifts; }
    public ArrayList<Double> gett1Percentages() { return this.t1Percentages; }
    public ArrayList<Double> gett2Percentages() { return this.t2Percentages; }
    //public void sett1Lifts(String[][] list) { this.t1Lifts = list; }
    //public void sett2Lifts(String[][] list) { this.t2Lifts = list; }
    public void sett1Percentages(ArrayList<Double> list) { this.t1Percentages = list; }
    public void sett2Percentages(ArrayList<Double> list) { this.t2Percentages = list; }

}
