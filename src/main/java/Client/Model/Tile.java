package Client.Model;

import Client.Controller.GameMenuController;
import Client.Controller.SampleFilter;
import Client.Model.Building.Building;
import Client.Model.Person.Military.MilitaryUnit;
import Client.Model.Person.Military.Soldier.Soldier;
import Client.Model.Person.Person;
import Client.View.Game.MapGUI;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;

import static Client.View.InputOutput.output;

public class Tile {
    private int x;
    private int y;
    private String texture;
    private boolean onFire = false;
    private boolean hasOil = false;
    private boolean hasKillingPit = false;
    private ArrayList<Person> people = new ArrayList<>();
    private Building building = null;
    private Pane mainPane = new Pane();
    private Pane dataPane = new Pane();

    private static HashMap<String, Image> textureImages = new HashMap<>();
    private static ArrayList<Client.Model.Pair> defPos= new ArrayList<>();

    static {
        textureImages.put("Earth", new Image(Tile.class.getResource("/Images/Textures/earth.png").toString()));
        textureImages.put("Dirt", new Image(Tile.class.getResource("/Images/Textures/dirt.png").toString()));
        textureImages.put("Grass", new Image(Tile.class.getResource("/Images/Textures/grass.png").toString()));
        textureImages.put("Iron", new Image(Tile.class.getResource("/Images/Textures/iron.png").toString()));
        textureImages.put("SmallPound", new Image(Tile.class.getResource("/Images/Textures/smallpound.png").toString()));
        textureImages.put("LargePound", new Image(Tile.class.getResource("/Images/Textures/largepound.png").toString()));
        textureImages.put("Sea", new Image(Tile.class.getResource("/Images/Textures/sea.png").toString()));
        textureImages.put("Stone", new Image(Tile.class.getResource("/Images/Textures/stone.png").toString()));
        textureImages.put("Rock", new Image(Tile.class.getResource("/Images/Textures/rock.png").toString()));
        textureImages.put("Sand", new Image(Tile.class.getResource("/Images/Textures/sand.png").toString()));
    }

    static {
        defPos.add(new Client.Model.Pair(60, 60));
        defPos.add(new Client.Model.Pair(10, 10));
        defPos.add(new Client.Model.Pair(110, 110));
        defPos.add(new Client.Model.Pair(10, 110));
        defPos.add(new Client.Model.Pair(110, 10));
        defPos.add(new Client.Model.Pair(10, 60));
        defPos.add(new Client.Model.Pair(60, 10));
        defPos.add(new Client.Model.Pair(110, 60));
        defPos.add(new Client.Model.Pair(60, 110));
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public ArrayList<Person> getPeople() {
        return people;
    }

    public ArrayList<Person> getPeople(User owner) {
        ArrayList<Person> peopleNOT = new ArrayList<>();
        for (Person person : people) {
            if (!person.getOwner().equals(owner)) peopleNOT.add(person);
        }
        return peopleNOT;
    }

    public void setPeople(ArrayList<Person> people) {
        this.people = people;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public String getTexture() {
        return texture;
    }

    public void setTexture(String texture) {
        this.texture = texture;
    }

    public void setOnFire(boolean onFire) {
        this.onFire = onFire;
    }

    public void setHasOil(boolean hasOil) {
        this.hasOil = hasOil;
    }

    public void setHasKillingPit(boolean hasKillingPit) {
        this.hasKillingPit = hasKillingPit;
    }

    public boolean isOnFire() {
        return onFire;
    }

    public boolean isHasOil() {
        return hasOil;
    }

    public boolean isHasKillingPit() {
        return hasKillingPit;
    }

    public Pane getMainPane() {
        return mainPane;
    }

    public void setMainPane(Pane mainPane) {
        this.mainPane = mainPane;
    }

    public Pane getDataPane() {
        return dataPane;
    }

    public void setDataPane(Pane dataPane) {
        this.dataPane = dataPane;
    }

    public void updateDataPane() {
        if (dataPane.getChildren().size() != 0) dataPane.getChildren().clear();
        int firstY = 20;
        int firstX = 10;
        Text coordinate = new Text("Tile coordinate: " + String.valueOf(x) + ", " + String.valueOf(y));
        coordinate.setLayoutX(firstX);
        coordinate.setLayoutY(firstY);
        dataPane.getChildren().add(coordinate);
        firstY += 15;
        Text textureDate = new Text("Texture: " + texture);
        textureDate.setLayoutX(firstX);
        textureDate.setLayoutY(firstY);
        dataPane.getChildren().add(textureDate);
        if (building != null) {
            firstY += 15;
            Text buildingData = new Text(building.getName() + ": Health-> " + String.valueOf(building.getHp()) +
                    " Owner-> " + building.getOwner().getUsername());
            buildingData.setLayoutX(firstX);
            buildingData.setLayoutY(firstY);
            dataPane.getChildren().add(buildingData);
        }



        if (people.size() != 0) {
            for (Person person: people) {
                firstY += 15;
                MilitaryUnit militaryUnit = (MilitaryUnit) person;
                Text soldierData = new Text(militaryUnit.getName() + ": Health-> " + militaryUnit.getDefence() +
                        " Owner-> " + militaryUnit.getOwner().getUsername());
                soldierData.setLayoutX(firstX);
                soldierData.setLayoutY(firstY);
                firstY += 15;
                dataPane.getChildren().add(soldierData);
                Text soldierData1 = new Text("  Damage-> " + militaryUnit.getAttack() + " Status-> " +
                        militaryUnit.getStatus());
                soldierData1.setLayoutX(firstX);
                soldierData1.setLayoutY(firstY);
                dataPane.getChildren().add(soldierData1);
            }
        }
    }

    public void showOnPane() {
        mainPane.getChildren().clear();
        mainPane.setPrefSize(170, 170);
        Rectangle backGroundRectangle = new Rectangle(0, 0, 170, 170);
        backGroundRectangle.setFill(Color.TRANSPARENT);
        if (MapGUI.getSelectedTiles().contains(this)) {
            backGroundRectangle.setFill(Color.RED);
        }
        Rectangle rect = new Rectangle(1, 1, 168, 168);
        rect.setFill(new ImagePattern(textureImages.get(this.texture)));
        mainPane.getChildren().add(backGroundRectangle);
        mainPane.getChildren().add(rect);
        if (building != null) {
            ImageView buildingImage = new ImageView(new Image(building.getImageUrl()));
            buildingImage.setLayoutX(50);
            buildingImage.setLayoutY(50);
            buildingImage.setFitWidth(90);
            buildingImage.setFitHeight(90);
            mainPane.getChildren().add(buildingImage);
        }

        if (GameMenuController.game.onFire.contains(new Pair<>(x,y))){
            ImageView fire = new ImageView(new Image(Tile.class.getResource("/Images/Textures/fire.png").toString()));
            fire.setLayoutX(10);
            fire.setLayoutY(110);
            fire.setFitWidth(150);
            fire.setFitHeight(50);
            mainPane.getChildren().add(fire);
            this.setOnFire(true);
        }
        if (people.size() != 0) {
            for (Person person: people) {
                if (person instanceof Soldier) {
                    Soldier soldier = (Soldier) person;
                    ImageView soldierImage = new ImageView(new Image(soldier.getImageUrl()));
                    soldierImage.setLayoutX(defPos.get(people.indexOf(person) % 9).first);
                    soldierImage.setLayoutY(defPos.get(people.indexOf(person) % 9).second);
                    soldierImage.setFitWidth(45);
                    soldierImage.setFitHeight(45);
                    if (soldier.isSick()) {
                        SampleFilter sampleFilter = new SampleFilter(new Image(soldier.getImageUrl()));
                        soldierImage = new ImageView(sampleFilter.filter(0.3, 0.9, 0.3));
                        soldierImage.setLayoutX(75);
                        soldierImage.setLayoutY(75);
                        soldierImage.setScaleX(1.2);
                        soldierImage.setScaleY(1.2);
                    }
                    soldierImage.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            if (!Game.currentGovernment.getUser().equals(soldier.getOwner())) {
                                output("This unit does no belong to you", 'i');
                                return;
                            }

                            MapGUI.unitMenuGUI.setX(soldier.getX());
                            MapGUI.unitMenuGUI.setY(soldier.getY());
                            MapGUI.unitMenuGUI.setType(soldier.getName());
                            MapGUI.unitMenuGUI.run();
                        }
                    });
                    mainPane.getChildren().add(soldierImage);
                }
            }
        }
    }
}