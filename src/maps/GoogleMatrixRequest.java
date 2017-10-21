package maps;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import trip.*;

import java.io.IOException;

public class GoogleMatrixRequest {

  private static final String API_KEY = "AIzaSyDjhAuWdeQW8tga6SU5yx-lJ8DyJvVoj7A"; 

  OkHttpClient client = new OkHttpClient();

  public String run(String url) throws IOException {
    Request request = new Request.Builder()
        .url(url)
        .build();

    Response response = client.newCall(request).execute();
    return response.body().string();
  }

  public String googleTripResults(String originLatLong, String destinationLatLong) throws IOException {
	  
    GoogleMatrixRequest request = new GoogleMatrixRequest();

    String url_request = "https://maps.googleapis.com/maps/api/distancematrix/xml?" 
    		+ "origins=" + originLatLong + "&" + "destinations=" + destinationLatLong 
    		+ "&mode=driving&" 
    		+ "&key=" + API_KEY;

    String response = request.run(url_request);
    System.out.println(response);
   	return response;
  }
}