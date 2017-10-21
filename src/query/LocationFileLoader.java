package query;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.security.InvalidParameterException;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author LukeRobbins2112
 */
public class LocationFileLoader{
    
    
	//Location <name, location>
    public static Trip loadLocationXML(String xmlString){
        
        NodeList entries;

        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

           /* File xmlFile = new File(fileName);
            if (!xmlFile.exists()) {
                System.err.println("**** XML File '" + fileName + "' cannot be found");
                System.exit(-1);
            }*/

            Document doc = db.parse(new InputSource( new StringReader( xmlString ) ));
            doc.getDocumentElement().normalize();
            //entries = doc.getDocumentElement().getChildNodes();
            entries = doc.getElementsByTagName("DistanceMatrixResponse");
            
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
            return null;
        }

        HashMap<String, Trip> trips = new HashMap<>();  // Create a temporary collection to store objects (i.e., a HashMap<String, Employee>)
        
        Trip trip = new Trip();
        
        try{
            // Parse the individual records from the XML file 
            for (int i = 0; i < entries.getLength(); i++) {
                if (entries.item(i).getNodeType() == Node.TEXT_NODE) {
                    continue;
                }

                String entryName = entries.item(i).getNodeName();

                //Checks to make sure each node is actually a distance matrix response

                if (!entryName.equals("DistanceMatrixResponse")) {
                    System.err.println("Unexpected node found: " + entryName);
                    continue;
                }

                // Get all named nodes
                Element elem = (Element) entries.item(i);
               // String name = elem.getElementsByTagName("Name").item(0).getTextContent();
                // Rating rating = Rating.valueOf(elem.getElementsByTagName("Rating").item(0).getTextContent());
                
                
                String origin = elem.getElementsByTagName("origin_address").item(0).getTextContent();
                String destination = elem.getElementsByTagName("destination_address").item(0).getTextContent();
                String tripDur = elem.getElementsByTagName("duration").item(0).getTextContent();
                tripDur = tripDur.substring(4); 
                //double tripDuration = Double.parseDouble(tripDur);
                
                trip = new Trip(origin, destination, tripDur);
                
                trips.put(origin, trip);
            }
        } catch(InvalidParameterException e){
            System.out.println(e.getMessage());
            return null;
        }
        
        return trip;
        
        
    }
    
}