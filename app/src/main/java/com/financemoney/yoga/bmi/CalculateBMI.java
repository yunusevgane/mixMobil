package com.financemoney.yoga.bmi;

public class CalculateBMI {

    private double inputfeet;
    private double inputinches;
    private double inputkg;

    public String getbmitype(double d) {
        return d <= 18.5d ? "Underweight" : d <= 24.9d ? "Normal Weight" : d <= 29.9d ? "Over Weight" : d <= 34.9d ? "Obesity" : d > 35.0d ? "Extremely Obesity" : "null";
    }

    public CalculateBMI(double d, double d2, double d3) {
        this.inputfeet = d;
        this.inputinches = d2;
        this.inputkg = d3;
    }

    public double getInputfeet() {
        return this.inputfeet;
    }

    public double getInputinches() {
        return this.inputinches;
    }

    public double getInputkg() {
        return this.inputkg;
    }

    public double camlculatebmi(double d, double d2, double d3) {
        double d4 = ((d3 * 30.48d) + (d2 * 2.54d)) / 100.0d;
        return ((double) Math.round((d / (d4 * d4)) * 100.0d)) / 100.0d;
    }
}
