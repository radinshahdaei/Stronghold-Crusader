package Controller;

import Model.Commodity;
import Model.Game;
import Model.Store;
import View.InputOutput;
import Model.Building.Building;
import Model.Building.Factory.Factory;
import Model.Building.Storage.Storage;
import Model.Resources.Resource;
import Model.Resources.ResourceType;

import static View.InputOutput.output;

public class StoreMenuController {
    public static void showCommodities() {
        int id = 0;
        for (Commodity commodity : Store.commodities) {
            System.out.println("ID: " + id + "Resource type: " + commodity.resourceType.name + " Sell price: " + commodity.sellPrice + " Buy price: " + commodity.buyPrice
            + "Our storage: " + Game.currentGovernment.getResource(commodity.resourceType).getCount());
            id++;
        }
    }

    public static boolean buy(String name , int amount) {
        Commodity commodity = Store.getCommodityByName(name);
        if (commodity == null) {
            // output("there is no such commodity");
            return false;
        }
        int price = commodity.buyPrice * amount;
        if(price > Game.currentGovernment.getResource(ResourceType.GOLD).getCount()) {
            // System.out.println("Not enough gold!");
            return false;
        }
        // RegisterMenuController.captcha();
        String storageName = Factory.getStorageName(commodity.resourceType.resourceModel);
        for (Building building: Game.currentGovernment.getBuildings()) {
            if (building.getName().equals(storageName)) {
                ((Storage) building).addToStorage(Resource.createResource(commodity.resourceType, amount));
                break;
            }
        }
        for (Building building: Game.currentGovernment.getBuildings()) {
            if (building.getName().equals("stockpile")) {
                ((Storage) building).removeFromStorage(Resource.createResource(ResourceType.GOLD, price));
                break;
            }
        }
        return true;
    }

    public static boolean sell(String name , int amount) {
        Commodity commodity = Store.getCommodityByName(name);
        if (commodity == null) {
            InputOutput.output("there is no such commodity");
            return false;
        }
        if(Game.currentGovernment.getResource(commodity.resourceType).getCount() < amount) {
            System.out.println("Not enough resource!");
            return false;
        }
        // RegisterMenuController.captcha();
        int price = commodity.sellPrice * amount;
        String storageName = Factory.getStorageName(commodity.resourceType.resourceModel);
        for (Building building: Game.currentGovernment.getBuildings()) {
            if (building.getName().equals(storageName)) {
                ((Storage) building).removeFromStorage(Resource.createResource(commodity.resourceType, amount));
                break;
            }
        }
        for (Building building: Game.currentGovernment.getBuildings()) {
            if (building.getName().equals("stockpile")) {
                ((Storage) building).addToStorage(Resource.createResource(ResourceType.GOLD, price));
                break;
            }
        }
        return true;
    }
}
