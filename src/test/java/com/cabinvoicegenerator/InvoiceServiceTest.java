package com.cabinvoicegenerator;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InvoiceServiceTest {
	InvoiceGenerator invoiceGenerator = null;
	RideRepository rideRepository = null;
	Ride[] rides;
	InvoiceSummary expectedSummary = null;

	@Before
	public void setUp() {
		invoiceGenerator = new InvoiceGenerator();
		rideRepository = new RideRepository();
		invoiceGenerator.setRideRepository(rideRepository);
		rides = new Ride[] { new Ride(2.0, 5, CabRide.NORMAL), new Ride(0.1, 1, CabRide.PREMIUM)
		};
		expectedSummary = new InvoiceSummary(2, 45.0);
	}

	@Test
	public void givenDistanceAndTime_ShouldReturnTotalFare() {
		double distance=2.0;
		int time=5;
		double fare=invoiceGenerator.calculateFare(distance,time);
		assertEquals(25,fare,0.0);
	}
	
	@Test
	public void givenLessDistanceOrTimeShouldReturnMinFare() {
		double distance = 0.1;
		int time = 1;
		double fare = invoiceGenerator.calculateFare(distance, time);
		Assert.assertEquals(5, fare, 0.0);
	}

	@Test
	public void givenMultipleRidesShouldReturnTotalFare() {
		InvoiceSummary invoiceSummary = invoiceGenerator.calculateFare(rides);
		Assert.assertEquals(expectedSummary, invoiceSummary);
	}

	@Test
	public void givenUserIdAndRidesShouldReturnInvoiceSummary() {
		String userId = "abc@gmail.com";
		invoiceGenerator.addRides(userId, rides);
		InvoiceSummary invoiceSummary = invoiceGenerator.getInvoiceSummary(userId);
		Assert.assertEquals(expectedSummary, invoiceSummary);
	}
}
