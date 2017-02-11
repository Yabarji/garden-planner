package net.garden.gardenplanner;

import java.util.ArrayList;

/**
 * Created by Varun on 2017-02-11.
 */

public class Garden {
    private boolean isIndoor;
    private int length;
    private int width;
    private boolean isPotted;
    private int numPots = 0;

    private ArrayList<Plant> plants;

    public Garden(boolean isIndoor, int length, int width, boolean isPotted) {
        this.isIndoor = isIndoor;
        this.length = length;
        this.width = width;
        this.isPotted = isPotted;

        plants = new ArrayList<>();
    }

    public void setNumPots(int numPots) {
        this.numPots = numPots;
    }

    public boolean isIndoor() {
        return isIndoor;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public boolean isPotted() {
        return isPotted;
    }

    public int getNumPots() {
        return numPots;
    }

    public void setIndoor(boolean indoor) {
        isIndoor = indoor;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setPotted(boolean potted) {
        isPotted = potted;
    }

    public void setPlants(ArrayList<Plant> plants) {
        this.plants = plants;
    }

    public ArrayList<Plant> getPlants() {
        return plants;
    }

    public void addPlant(Plant plant) {
        plants.add(plant);
    }

}
