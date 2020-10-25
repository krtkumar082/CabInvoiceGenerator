package com.cabinvoicegenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RideRepository {
	Map<String, ArrayList<Ride>> userToRides = null;

	public RideRepository() {
		userToRides = new HashMap<>();
	}

	public void addRides(String userId, Ride[] rides) {
		ArrayList<Ride> rideList = this.userToRides.get(userId);
		if (rideList == null) {
			rideList = new ArrayList<>(Arrays.asList(rides));	
		}
		this.userToRides.put(userId, rideList);
	}

	public ArrayList<Ride> getRides(String userId) {
		return this.userToRides.get(userId);
	}
}
