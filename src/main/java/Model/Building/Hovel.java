package Model.Building;

import Model.Game;
import Model.Resources.Resource;
import Model.User;

import java.util.ArrayList;

public class Hovel extends Building {
    public Hovel(int x, int y, ArrayList<Resource> price, User owner) {
        super("hovel", 100, x, y, 0, price, owner);
    }

    public static Building createBuilding(int x, int y, User owner) {
        Game.currentGovernment.addPopulation(10);
        // GameMenu.addPopulation(Game.currentGovernment, 8);
        return new Hovel(x, y, Resource.getResources("wood", "6"), owner);
    }
}
