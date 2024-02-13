package Services;

import Classes.Entities.Base.Entity;
import Classes.Entities.Enemies.Bestiarium;
import Classes.Map.MapCell;
import Classes.Map.Map;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.util.ArrayList;
import java.util.List;

import static Services.XMLReader.readXMLDocument;

public class MapInitializor {
    public static void initializeMap(String filepath){
        List<MapCell> map = new ArrayList<>();
        org.w3c.dom.Document doc = readXMLDocument(filepath);
        NodeList nList = doc.getElementsByTagName("MapCell");

        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                MapCell currentMapCell = new MapCell(
                        eElement.getElementsByTagName("title").item(0).getTextContent(),
                        eElement.getElementsByTagName("description").item(0).getTextContent());

                NodeList enemieNodeList = eElement.getElementsByTagName("id");
                currentMapCell.setAvailableEntitiesInCell(readEntityData(enemieNodeList));

                map.add(currentMapCell);
            }
        }
        //Initialize Singleton
        Map.getInstance().setMapCells(map);
    }

    private static List<Entity> readEntityData(NodeList enemieNodeList){
        List<Entity> enemies = new ArrayList<>();

        for(int e = 0; e < enemieNodeList.getLength(); e++){
            Node enemieNode = enemieNodeList.item(e);
            Element enemieElement = (Element) enemieNode;
            String id = enemieElement.getTextContent();
            enemies.add(Bestiarium.getInstance().getEntityByID(Integer.parseInt(id)));
        }
        return  enemies;
    }
}
