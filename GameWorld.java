package org.csc133.a2;


import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.geom.Dimension;
import org.csc133.a2.gameObjects.*;

import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameWorld {
    Dimension worldSize;
    private int totalFireDMG;
    private int maxFireDMG;
    private int buildingArea = 0;
    private int damagePercent = 0;
    private int ticks;
    private int fireTotal = 0;
    private final int NUMBER_OF_BUILDINGS = 3;
    private static Helicopter helicopter;
    private Helipad helipad;
    private River river;

    private BuildingCollection buildings;
    private FireCollection fires;

    private CopyOnWriteArrayList<GameObject> gameObjects;
    private int rand;

    private double fireTotalSize;

    public GameWorld(){
    }

    public void init() {
        ticks = 0;
        helipad = new Helipad(worldSize);
        river = new River(worldSize);
        buildings = new BuildingCollection();
        fires = new FireCollection();
        rand = new Random().nextInt(10)+50;

        for (int x = 0; x < NUMBER_OF_BUILDINGS; x++) {
            buildings.add(new Building(x, worldSize));
        }

        for (int i = 0; i < 2; i++) {
            for (Building building : buildings) {
                Fire temp = new Fire(worldSize);
                building.setFireInBuilding(temp);
                getTotalFireSize();
                fires.add(temp);
                fireTotal++;
            }
        }

        while (this.fireTotalSize < 950) {
            for (Building building : buildings) {
                if (ticks % rand == 0) {
                    Fire temp = new Fire(worldSize);
                    building.setFireInBuilding(temp);
                    fires.add(temp);
                    fireTotal++;
                    getTotalFireSize();
                }
                if (this.fireTotalSize > 950) {
                    break;
                }
            }
        }
        for (Building building : buildings){
            buildingArea += building.getBuildingArea();
        }

        helicopter = new Helicopter(worldSize);
        gameObjects = new CopyOnWriteArrayList<>();

        gameObjects.add(helipad);
        gameObjects.add(river);
        gameObjects.add(buildings);
        gameObjects.add(fires);
        gameObjects.add(helicopter);
    }

    public void quit(){
        Display.getInstance().exitApplication();
    }

    void tick(){
        ticks++;
        getTotalFireSize();
        getTotalDMG();
        for(Fire fire: fires){
            if(ticks%rand == 0) {
                fire.grow();
                getTotalDMG();
            }
            if(fire.getSize() < 8){
                fireTotal--;
                fires.remove(fire);
            }
        }

        for (Building building : buildings){
            building.adjustValue(this.damagePercent*1000/3);
            building.setDMG(this.damagePercent/3);
        }

        helicopter.move();
        helicopter.useFuel();
        victoryScreen();
    }

    public CopyOnWriteArrayList<GameObject> getGameObjectCollection() {
        return gameObjects;
    }

    public void drink(){
        if(river.isCollidingWith(helicopter)){
            helicopter.grabWaterFromRiver();
        }
    }

    public void fight(){
        for(Fire fire: fires){
            if(fire.isCollidingWith(helicopter)
                    && helicopter.getCurrentWaterInTank() > 1){
                fire.shrink(helicopter.getCurrentWaterInTank());
            }
        }
        helicopter.useWater();
    }

    void victoryScreen(){
        if (helipad.isCollidingWith(helicopter) && fireTotal == 0){
            if(Dialog.show("Game Over", "You Won!\nScore: "
                    + helicopter.getFuel() + "\nPlay Again?",
                    "Heck Yea!", "Some Other Time")) {
                // user clicked yes
                new Game();

            } else {
                //If user clicked no
                quit();
            }
        }
    }

    void turn(int turnAmount){
        helicopter.turn(turnAmount);
    }

    public void left() {
        helicopter.turn(-15);
    }

    public void right(){
        helicopter.turn(15);
    }

    public void adjustSpeed(int changeSpeed){
        helicopter.adjustSpeed(changeSpeed);
    }

    public String getHeading() {
        return String.valueOf(helicopter.getHeading());
    }

    private void getTotalFireSize() {
        this.fireTotalSize = 0;

        for (Fire fire : fires) {
            this.fireTotalSize += fire.currentSize();
        }
    }

    private void getTotalDMG() {
        this.totalFireDMG = 0;
        for (Fire fire : fires) {
            this.totalFireDMG += fire.getGreatestSize();
        }

        if(totalFireDMG>maxFireDMG){
            maxFireDMG=totalFireDMG;
        }

        this.damagePercent = (this.maxFireDMG*42000 / this.buildingArea);
    }

    public String getSpeed() {
        return String.valueOf(helicopter.getSpeed());
    }

    public String getFuel() {
        return String.valueOf(helicopter.getFuel());
    }

    public String getFireCount() {
        return String.valueOf(fires.size());
    }

    public String getDamage() {
        return (damagePercent + "%");
    }

    public String getLoss() {
        return ("$" + damagePercent * 1000);
    }

    public String getFireSize() {
        return String.valueOf((int)this.fireTotalSize);
    }

    public void setDimension(Dimension worldSize) {
        this.worldSize = worldSize;
    }

    public void quitGame() {
        quit();
    }

}

