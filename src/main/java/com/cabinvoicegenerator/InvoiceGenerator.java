package com.cabinvoicegenerator;

public class InvoiceGenerator {
	private static final double COST_PER_KILOMETER = 10;
	private static final int COST_PER_TIME = 1;
	private static final double MIN_FARE = 5;
	
	public double calculateFare(double distance,int time) {
		double fare = distance * COST_PER_KILOMETER + time * COST_PER_TIME;
		if (fare < MIN_FARE)
			return MIN_FARE;
		return fare;
	}
	
	public InvoiceSummary calculateFare(Ride[] rides) {

		double totalFare = 0;
		for (Ride ride : rides) {
			totalFare += this.calculateFare(ride.distance, ride.time);
		}
		return new InvoiceSummary(rides.length, totalFare);
	}

}
