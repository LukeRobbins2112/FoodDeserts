package query;

import java.io.IOException;

import maps.GoogleMatrixRequest;

public class UserInterface {
	
	public UserInterface(){
		
	}
	
	public static void main(String[] args) throws IOException{
		GoogleMatrixRequest gmr = new GoogleMatrixRequest();
		//LocationFileLoader lfl = new LocationFileLoader();
		Trip t = LocationFileLoader.loadLocationXML(gmr.googleTripResults());
		String origin = t.getOrigin();
		String dest = t.getDestination();
		String distTime = t.getTravelTime();
		
		System.out.println("Origin: " + origin);
		System.out.println("Dest: " + dest);
		System.out.println("Time: " + distTime);
		
	}
	
	
	

}
