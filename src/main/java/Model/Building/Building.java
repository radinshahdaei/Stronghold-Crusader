package Model.Building;

import Model.Resources.Resource;

import java.util.ArrayList;
import java.util.HashMap;

public class Building {
    public static HashMap<String, BuildingType> ALL_BUILDINGS = new HashMap<>();

    static {
        ALL_BUILDINGS.put("lookout tower", BuildingType.DEFENSIVE_BUILDING);
        ALL_BUILDINGS.put("perimeter tower", BuildingType.DEFENSIVE_BUILDING);
        ALL_BUILDINGS.put("defence turret", BuildingType.DEFENSIVE_BUILDING);
        ALL_BUILDINGS.put("square tower", BuildingType.DEFENSIVE_BUILDING);
        ALL_BUILDINGS.put("round tower", BuildingType.DEFENSIVE_BUILDING);

        ALL_BUILDINGS.put("armory", BuildingType.STORAGE);
        ALL_BUILDINGS.put("granary", BuildingType.STORAGE);
        ALL_BUILDINGS.put("stockpile", BuildingType.STORAGE);
        ALL_BUILDINGS.put("stable", BuildingType.STORAGE);

        ALL_BUILDINGS.put("iron mine", BuildingType.FACTORY);
        ALL_BUILDINGS.put("quarry", BuildingType.FACTORY);
        ALL_BUILDINGS.put("wood cutter", BuildingType.FACTORY);
        ALL_BUILDINGS.put("pitch rig", BuildingType.FACTORY);
        ALL_BUILDINGS.put("apple orchard", BuildingType.FACTORY);
        ALL_BUILDINGS.put("hops farmer", BuildingType.FACTORY);
        ALL_BUILDINGS.put("hunter post", BuildingType.FACTORY);
        ALL_BUILDINGS.put("diary farmer", BuildingType.FACTORY);
        ALL_BUILDINGS.put("wheat farmer", BuildingType.FACTORY);
        ALL_BUILDINGS.put("bakery", BuildingType.FACTORY);
        ALL_BUILDINGS.put("brewer", BuildingType.FACTORY);
    }

    private String name;
    private int hp;
    private int x;
    private int y;
    private int workers;
    private ArrayList<Resource> price = new ArrayList<>();

    public Building(String name, int hp, int x, int y, int workers, ArrayList<Resource> price) {
        this.name = name;
        this.hp = hp;
        this.x = x;
        this.y = y;
        this.workers = workers;
        this.price = price;
    }

    public int getWorkers() {
        return workers;
    }

    public int getHp() {
        return hp;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Resource> getPrice() {
        return price;
    }
}
