package Services;

import Classes.Entities.Base.Entity;
import Data.Enemies.Bestiarium;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

import static Services.XMLReader.readXMLDocument;

public class ItemInitializor {
    public static void initializeItems(String filepath) {
        List<Entity> enemies = new ArrayList<>();
        org.w3c.dom.Document doc = readXMLDocument(filepath);
        NodeList nList = doc.getElementsByTagName("Item");

        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;

                //Change to item values
                String id = eElement.getElementsByTagName("id").item(0).getTextContent();
                String name = eElement.getElementsByTagName("name").item(0).getTextContent();
                String desc = eElement.getElementsByTagName("description").item(0).getTextContent();
                String health = eElement.getElementsByTagName("health").item(0).getTextContent();
                String attack = eElement.getElementsByTagName("attack").item(0).getTextContent();

                enemies.add(new Entity(Integer.parseInt(id), name, desc, Double.parseDouble(health), Double.parseDouble(attack)));
            }
        }
    }
    //Add to Singleton
}
