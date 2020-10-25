package com.cabinvoicegenerator;

import java.util.ArrayList;

public class InvoiceGenerator {
	private static final double COST_PER_KILOMETER = 10;
	private static final int COST_PER_TIME = 1;
	private static final double MIN_FARE = 5;
	private RideRepository rideRepository;
	
	public InvoiceGenerator() {
		this.rideRepository = new RideRepository();
	}
	
	public double calculateFare(double distance, int time) {
		double totalFare = distance * COST_PER_KILOMETER + time * COST_PER_TIME;
		return Math.max(MIN_FARE, totalFare);

	}
	
	public InvoiceSummary calculateFare(ArrayList<Ride> arrayList) {

		double totalFare = 0;
		for (Ride ride : arrayList) {
			totalFare += this.calculateFare(ride.distance, ride.time);
		}
		return new InvoiceSummary(arrayList.size(), totalFare);
	}

	public void addRides(String userId, Ride[] rides) {

		rideRepository.addRides(userId, rides);
	}

	public InvoiceSummary getInvoiceSummary(String userId) {

		return this.calculateFare(rideRepository.getRides(userId));
	}

}
