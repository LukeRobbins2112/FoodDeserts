package trip;

import java.util.HashMap;

public class TripOperations {
	
	HashMap<String, Trip> trips;
	
	public TripOperations(HashMap<String, Trip> trips){
		this.trips = trips;
	}
	
	public double averageTripTime(){
		double total = 0;
		for (String tripName : trips.keySet()){
			total += this.trips.get(tripName).getTravelTime();
		}
		return total/this.trips.size();
	}

}
