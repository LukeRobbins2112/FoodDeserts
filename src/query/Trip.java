package query;

public class Trip {
	
	private String origin;
	private String destination;
	private String travelTime;
	
	public Trip(){
		
	}
	
	public Trip(String origin, String destination, String travelTime){
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

	public String getTravelTime() {
		return travelTime;
	}

	public void setTravelTime(String travelTime) {
		this.travelTime = travelTime;
	}
	
	

}
