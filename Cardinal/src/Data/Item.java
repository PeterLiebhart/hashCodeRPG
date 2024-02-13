package src.Data;

public class Item {

    private String name;

    private int ID;

    private boolean isActive;

    public Item(){};

    public Item(String name, int ID, boolean isActive) {
        this.name = name;
        this.ID = ID;
        this.isActive = isActive;
    }
}
