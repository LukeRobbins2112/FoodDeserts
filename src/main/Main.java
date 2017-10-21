package main;

import java.io.IOException;
import java.util.HashMap;

import locationobjects.*;
import maps.*;
import trip.*;
import xmloperations.*;

public class Main {

	public static void main(String[] args) throws IOException {
		
		ShortestTrips sts = new ShortestTrips();
		
		HashMap<String, Trip> trips = sts.shortTrips();
		TripOperations op = new TripOperations(trips);
		
		System.out.println(op.averageTripTime());

	}

}
