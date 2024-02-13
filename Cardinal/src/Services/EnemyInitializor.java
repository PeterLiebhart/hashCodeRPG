package Services;

import Classes.Entities.Base.Entity;
import Classes.Entities.Enemies.Bestiarium;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

import static Services.XMLReader.readXMLDocument;

public class EnemyInitializor {
    public static void initializeEnemies(String filepath) {
        List<Entity> enemies = new ArrayList<>();
        org.w3c.dom.Document doc = readXMLDocument(filepath);
        NodeList nList = doc.getElementsByTagName("Entity");

        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;

                String id = eElement.getElementsByTagName("id").item(0).getTextContent();
                String name = eElement.getElementsByTagName("name").item(0).getTextContent();
                String desc = eElement.getElementsByTagName("description").item(0).getTextContent();
                String health = eElement.getElementsByTagName("health").item(0).getTextContent();
                String attack = eElement.getElementsByTagName("attack").item(0).getTextContent();

                enemies.add(new Entity(Integer.parseInt(id), name, desc, Double.valueOf(health), Double.valueOf(attack)));

                /*System.out.println("Name : " + eElement.getElementsByTagName("name").item(0).getTextContent());
                System.out.println("Description : " + eElement.getElementsByTagName("description").item(0).getTextContent());
                System.out.println("Health : " + eElement.getElementsByTagName("health").item(0).getTextContent());
                System.out.println("Attack : " + eElement.getElementsByTagName("attack").item(0).getTextContent());
                System.out.println("EntityID : " + eElement.getElementsByTagName("id").item(0).getTextContent());
                System.out.println("-------------------------------------");*/
            }
        }
        Bestiarium.getInstance().setEntities(enemies);
    }
}
