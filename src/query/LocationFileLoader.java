package query;

import java.io.File;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author LukeRobbins2112
 */
public class LocationFileLoader{
    
    
	//Location <name, location>
    public static HashMap<String,Integer> loadLocationXML(String fileName){
        
        NodeList entries;

        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            File xmlFile = new File(fileName);
            if (!xmlFile.exists()) {
                System.err.println("**** XML File '" + fileName + "' cannot be found");
                System.exit(-1);
            }

            Document doc = db.parse(xmlFile);
            doc.getDocumentElement().normalize();
            entries = doc.getDocumentElement().getChildNodes();

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
            return null;
        }

        HashMap<String, Integer> locations = new HashMap<>();  // Create a temporary collection to store objects (i.e., a HashMap<String, Employee>)
        
        try{
            // Parse the individual records from the XML file 
            for (int i = 0; i < entries.getLength(); i++) {
                if (entries.item(i).getNodeType() == Node.TEXT_NODE) {
                    continue;
                }

                String entryName = entries.item(i).getNodeName();

                //Checks to make sure each node is actually a Location

                if (!entryName.equals("Location")) {
                    System.err.println("Unexpected node found: " + entryName);
                    continue;
                }

                // Get all named nodes
                Element elem = (Element) entries.item(i);
                String name = elem.getElementsByTagName("Name").item(0).getTextContent();
                int duration = Integer.parseInt(elem.getElementsByTagName("Duration").item(0).getTextContent());
                Rating rating = Rating.valueOf(elem.getElementsByTagName("Rating").item(0).getTextContent());
                String fName = elem.getElementsByTagName("FileName").item(0).getTextContent();

                // Create the domain object and add to temporary collection, then return collection
                Video vid = new VideoImpl(name, duration, rating, fName);
                videos.put(name, vid);
            }
        } catch(InvalidParameterException e){
            System.out.println(e.getMessage());
            return null;
        }
        
        return videos;
        
        
    }
    
}