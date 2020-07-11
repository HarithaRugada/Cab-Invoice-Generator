package com.cabinvoicegenerator.service;

import com.cabinvoicegenerator.utility.CabRide;
import com.cabinvoicegenerator.utility.Ride;
import com.cabinvoicegenerator.utility.RideRepository;

public class InvoiceGenerator {
    private RideRepository rideRepository;

    public void setRideRepository(RideRepository rideRepository) {
        this.rideRepository = rideRepository;
    }

    public double calculateFare(CabRide cabRideType, double distance, int time) {
        double totalFare = distance * cabRideType.costPerKilometer + time * cabRideType.costPerMinute;
        return Math.max(totalFare, cabRideType.minimumFarePerRide);
    }

    public InvoiceSummary calculateFare(Ride[] rides) {
        double totalFare = 0;
        for (Ride ride : rides) {
            totalFare += this.calculateFare(ride.cabRideType,ride.distance,ride.time);
        }
        return new InvoiceSummary(rides.length, totalFare);
    }

    public void addRides(String userId, Ride[] rides) {
        rideRepository.addRide(userId, rides);
    }

    public InvoiceSummary getInvoiceSummary(String userId) {
        return this.calculateFare(rideRepository.getRides(userId));
    }
}
