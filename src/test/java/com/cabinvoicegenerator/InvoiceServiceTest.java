package com.cabinvoicegenerator;

import com.cabinvoicegenerator.service.InvoiceGenerator;
import com.cabinvoicegenerator.service.InvoiceSummary;
import com.cabinvoicegenerator.utility.CabRide;
import com.cabinvoicegenerator.utility.Ride;
import com.cabinvoicegenerator.utility.RideRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InvoiceServiceTest {
    InvoiceGenerator invoiceGenerator = null;
    RideRepository rideRepository = null;
    InvoiceSummary expectedInvoiceSummary = null;

    @Before
    public void setUp() {
        invoiceGenerator = new InvoiceGenerator();
        rideRepository = new RideRepository();
        invoiceGenerator.setRideRepository(rideRepository);
    }

    @Test
    public void givenDistanceAndTime_ShouldReturnTotalFare() {
        double distance = 2.0;
        int time = 5;
        double fare = invoiceGenerator.calculateFare(CabRide.NORMAL, distance, time);
        Assert.assertEquals(25, fare, 0.0);
    }

    @Test
    public void givenLessDistanceAndTime_ShouldReturnMinimumFare() {
        double distance = 0.1;
        int time = 1;
        double fare = invoiceGenerator.calculateFare(CabRide.NORMAL, distance, time);
        Assert.assertEquals(5, fare, 0.0);
    }

    @Test
    public void givenMultipleRides_ShouldReturnInvoiceSummary() {
        Ride[] rides = {
                new Ride(CabRide.NORMAL, 2.0, 5),
                new Ride(CabRide.NORMAL, 0.1, 1)
        };
        InvoiceSummary invoiceSummary = invoiceGenerator.calculateFare(rides);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 30);
        Assert.assertEquals(expectedInvoiceSummary, invoiceSummary);
    }

    @Test
    public void givenUserId_ShouldReturnInvoiceSummary() {
        Ride[] userRide1 = {new Ride(CabRide.NORMAL, 2.0, 3),
                new Ride(CabRide.NORMAL, 0.1, 1)};

        Ride[] userRide2 = {new Ride(CabRide.NORMAL, 7.3, 5),
                new Ride(CabRide.NORMAL, 5.7, 2)};
        invoiceGenerator.addRides("abc", userRide1);
        invoiceGenerator.addRides("xyz", userRide2);
        expectedInvoiceSummary = new InvoiceSummary(2, 28);
        InvoiceSummary invoiceSummary = invoiceGenerator.getInvoiceSummary("abc");
        Assert.assertEquals(expectedInvoiceSummary.totalFare,invoiceSummary.totalFare,0.0);
        Assert.assertEquals(invoiceSummary.totalFare,28,0.0);
    }

    @Test
    public void whenRideTypeIsNormal_ShouldReturnTotalFare() {
        double distance = 2.0;
        int time = 5;
        double fare = invoiceGenerator.calculateFare(CabRide.NORMAL,distance, time);
        Assert.assertEquals(25, fare, 0.0);
    }

    @Test
    public void whenRideTypeIsEconomy_ShouldReturnTotalFare() {
        double distance = 2.0;
        int time = 5;
        double fare = invoiceGenerator.calculateFare(CabRide.ECONOMY,distance, time);
        Assert.assertEquals(31.5, fare, 0.0);
    }

    @Test
    public void whenRideTypeIsPremium_ShouldReturnTotalFare() {
        double distance = 2.0;
        int time = 5;
        double fare = invoiceGenerator.calculateFare(CabRide.PREMIUM,distance, time);
        Assert.assertEquals(40, fare, 0.0);
    }
}
