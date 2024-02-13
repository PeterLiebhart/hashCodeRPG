package Data.Enemies;

import Classes.Entities.Base.Entity;
import java.util.List;
import java.util.Objects;

public class Bestiarium {
    private static Bestiarium single_instance = null;
    private static List<Entity> entities;

    public Bestiarium() {
    }
    public static synchronized Bestiarium getInstance()
    {
        if (single_instance == null)
            single_instance = new Bestiarium();

        return single_instance;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public void setEntities(List<Entity> entities) {
        Bestiarium.entities = entities;
    }

    public List<Entity> getEntitiesByName(String name){
        return getEntities().stream().filter(x -> Objects.equals(x.getName(), name)).toList();
    }

    public Entity getEntityByID(int id){
        return getEntities().stream().filter(x -> x.getId() == id).toList().getFirst();
    }
}
