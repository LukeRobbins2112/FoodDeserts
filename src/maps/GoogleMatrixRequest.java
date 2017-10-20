package maps;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
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

  public static void main(String[] args) throws IOException {
	  
    GoogleMatrixRequest request = new GoogleMatrixRequest();
    String originLatLong = "41.8885198,-87.6354482";
    String desginationLatLong = "41.9051182,-87.6832578";
    
    
    /*String url_request = "https://maps.googleapis.com/maps/api/distancematrix/json?"
    		+ "origins=Vancouver+BC%7CSeattle&destinations=San+Francisco%7CVictoria+BC&"
    		+ "mode=bicycling&language=fr-FR&key=" + API_KEY;*/

    String url_request = "https://maps.googleapis.com/maps/api/distancematrix/xml?" 
    		+ "origins=" + originLatLong + "&" + "destinations=" + desginationLatLong 
    		+ "&mode=transit&" 
    		+ "&key=" + API_KEY;

    String response = request.run(url_request);
    System.out.println(response);
  }
}