package Classes.Map;

import java.util.List;

public class Map {
    List<MapCell> mapCells;
    private static Map single_instance = null;

    public Map() {
    }

    public static synchronized Map getInstance()
    {
        if (single_instance == null)
            single_instance = new Map();

        return single_instance;
    }

    public List<MapCell> getMapCells() {
        return mapCells;
    }

    public void setMapCells(List<MapCell> mapCells) {
        this.mapCells = mapCells;
    }
}
