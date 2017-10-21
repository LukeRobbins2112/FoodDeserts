package trip;

import java.io.IOException;
import java.util.HashMap;

import locationobjects.DesertArea;
import locationobjects.GroceryStore;
import maps.GoogleMatrixRequest;
import xmloperations.DesertLocationLoader;
import xmloperations.GroceryStoreLoader;
import xmloperations.LocationFileLoader;

public class ShortestTrips {
	
	private static final double MAX = 4000000;
	
	public ShortestTrips(){
		
	}
	
	//returns the trip to the closest Aldi for each DesertLocation
	
	public HashMap<String, Trip> shortTrips() throws IOException{
		
	    
		/*HashMap<String, GroceryStore> stores = new HashMap<String, GroceryStore>();				//test approach, XML for actual
		GroceryStore aldi1 = new GroceryStore("aldi1", "41.7743058812", "-87.6056480253");
		GroceryStore aldi2 = new GroceryStore("aldi2", "41.7799200473", "-87.6409255107");
		GroceryStore aldi3 = new GroceryStore("aldi3", "41.760880872", "-87.6246657846");
		stores.put("aldi1", aldi1);
		stores.put("aldi2", aldi2);
		stores.put("aldi3", aldi3);*/
		
		
	    HashMap<String, DesertArea> desertNodes = DesertLocationLoader.loadDesertXML("DesertLocations.xml");
	    HashMap<String, GroceryStore> storeNodes = GroceryStoreLoader.loadStoreXML("GroceryStores.xml");
	    HashMap<String, Trip> trips = new HashMap<String, Trip>();
		GoogleMatrixRequest request = new GoogleMatrixRequest();
		
		for (String desert: desertNodes.keySet()){
			Trip shortestTrip = new Trip();
			double shortestTime = MAX;
			
			for (String store : storeNodes.keySet()){
				String originLatLong = desertNodes.get(desert).getLatitude() + "," + desertNodes.get(desert).getLongitude();
				String destLatLong = storeNodes.get(store).getLatitude() + "," + storeNodes.get(store).getLongitude();
				String tripResult = request.googleTripResults(originLatLong, destLatLong);
				if (LocationFileLoader.loadLocationXML(tripResult).getTravelTime() < shortestTime)
					shortestTrip = LocationFileLoader.loadLocationXML(tripResult);
			}
			
			trips.put(desert, shortestTrip);
		}
		
		
		return trips;
	}
	
	
	

}
