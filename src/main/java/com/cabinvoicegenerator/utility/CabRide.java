package com.cabinvoicegenerator.utility;

public enum CabRide {
    NORMAL(10.0,1.0,5.0),
    ECONOMY(12.0,1.5,10.0),
    PREMIUM(15.0,2.0,20.0);

    public double costPerKilometer;
    public double costPerMinute;
    public double minimumFarePerRide;

    CabRide(double costPerKilometer, double costPerMinute, double minimumFarePerRide) {
        this.costPerKilometer = costPerKilometer;
        this.costPerMinute = costPerMinute;
        this.minimumFarePerRide = minimumFarePerRide;
    }

        public double calculateCostOfCabRide(Ride ride){
        double rideCost=ride.distance*costPerKilometer+ride.time+costPerMinute;
        return Math.max(rideCost,minimumFarePerRide);
    }
}

