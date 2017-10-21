package query;

public class Trip {
	
	private String origin;
	private String destination;
	private double travelTime;
	
	public Trip(String origin, String destination, double travelTime){
		this.origin = origin;
		this.destination = destination;
		this.travelTime = travelTime;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public double getTravelTime() {
		return travelTime;
	}

	public void setTravelTime(double travelTime) {
		this.travelTime = travelTime;
	}
	
	

}
