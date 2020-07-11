package com.cabinvoicegenerator.utility;

public class Ride {
    public int time;
    public double distance;

    public enum RideType{
        NORMAL,PREMIUM
    }

    public Ride(double distance, int time) {
        this.distance = distance;
        this.time = time;
    }
}
