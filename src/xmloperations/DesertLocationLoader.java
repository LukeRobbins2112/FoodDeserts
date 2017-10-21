package xmloperations;

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

import locationobjects.DesertArea;

/**
 *
 * @author LukeRobbins2112
 */
public class DesertLocationLoader{
    
    
	//Location <name, location>
    public static HashMap<String, DesertArea> loadDesertXML(String fileName){
        
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
        
        HashMap<String, DesertArea> desertNodes = new HashMap<>();  // Create a temporary collection to store objects (i.e., a HashMap<String, Employee>)

        DesertArea dNode;
        
        try{
            // Parse the individual records from the XML file 
            for (int i = 0; i < entries.getLength(); i++) {
                if (entries.item(i).getNodeType() == Node.TEXT_NODE) {
                    continue;
                }

                String entryName = entries.item(i).getNodeName();

                //Checks to make sure each node is actually a distance matrix response

                /*if (!entryName.equals("DistanceMatrixResponse")) {
                    System.err.println("Unexpected node found: " + entryName);
                    continue;
                }*/

                Element elem = (Element) entries.item(i);
                            
                String address = elem.getElementsByTagName("origin_address").item(0).getTextContent();
                String latitude = elem.getElementsByTagName("latitude").item(0).getTextContent();
                String longitude = elem.getElementsByTagName("longitude").item(0).getTextContent();
                
                dNode = new DesertArea(address, latitude, longitude);
                desertNodes.put(address, dNode);
                
            }
        } catch(InvalidParameterException e){
            System.out.println(e.getMessage());
            return null;
        }
        
        return desertNodes;
        
    }
    
}