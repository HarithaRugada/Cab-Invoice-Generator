package com.cabinvoicegenerator.service;

import com.cabinvoicegenerator.utility.Ride;

import java.util.HashMap;
import java.util.Map;

public class InvoiceGenerator {
    private static  int costPerTime=1;
    private static double costPerkilometer = 10;
    private static double minimumFare = 5;
    private Map<Integer, InvoiceSummary> invoiceServiceMap = new HashMap<>();

    public double calculateFare(double distance, int time) {
        double totalFare = distance * costPerkilometer + time * costPerTime;
        return Math.max(totalFare, minimumFare);
    }

    public InvoiceSummary calculateFare(Ride[] rides) {
        double totalFare = 0;
        for (Ride ride : rides) {
            totalFare += this.calculateFare(ride.distance, ride.time);
        }
        return new InvoiceSummary(rides.length, totalFare);
    }

    public void InvoiceService(Ride[] userRides, int userId) {
        invoiceServiceMap.put(userId,this.calculateFare(userRides));
    }

    public InvoiceSummary InvoiceService(int userId) {
        return invoiceServiceMap.get(userId);
    }

    public static void rideCategory(Ride.RideType rideType){
        switch (rideType){
            case PREMIUM:
                costPerkilometer=15;
                costPerTime=2;
                minimumFare=20;
                break;
            case NORMAL:
                costPerkilometer=10;
                costPerTime=1;
                minimumFare=5;
                break;
        }
    }
}
