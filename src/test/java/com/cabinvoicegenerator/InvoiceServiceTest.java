package com.cabinvoicegenerator;

import com.cabinvoicegenerator.service.InvoiceGenerator;
import com.cabinvoicegenerator.service.InvoiceSummary;
import com.cabinvoicegenerator.utility.Ride;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InvoiceServiceTest {
    InvoiceGenerator invoiceGenerator = null;

    @Before
    public void setUp() {
        invoiceGenerator = new InvoiceGenerator();
    }

    @Test
    public void givenDistanceAndTime_ShouldReturnTotalFare() {
        double distance = 2.0;
        int time = 5;
        double fare = invoiceGenerator.calculateFare(distance, time);
        Assert.assertEquals(25, fare, 0.0);
    }

    @Test
    public void givenLessDistanceAndTime_ShouldReturnMinimumFare() {
        double distance = 0.1;
        int time = 1;
        double fare = invoiceGenerator.calculateFare(distance, time);
        Assert.assertEquals(5, fare, 0.0);
    }

    @Test
    public void givenMultipleRides_ShouldReturnInvoiceSummary() {
        Ride[] rides = {
                new Ride(2.0, 5),
                new Ride(0.1, 1)
        };
        InvoiceSummary invoiceSummary = invoiceGenerator.calculateFare(rides);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 30);
        Assert.assertEquals(expectedInvoiceSummary, invoiceSummary);
    }

    @Test
    public void givenUserId_ShouldReturnServiceInvoice() {
        Ride[] userRide1 = {new Ride(2.0, 3),
                new Ride(0.1, 1)};
        invoiceGenerator.InvoiceService(userRide1, 111);
        Ride[] userRide2 = {new Ride(7.3, 5),
                new Ride(5.7, 2)};
        invoiceGenerator.InvoiceService(userRide2, 222);
        Assert.assertEquals(new InvoiceSummary(2, 54), invoiceGenerator.InvoiceService(111));
    }
}
