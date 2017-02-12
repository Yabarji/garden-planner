package net.garden.gardenplanner;

import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by Varun on 2017-02-11.
 */

public class Garden  {

    public static final String KEY = "garden";
    private boolean isIndoor;
    private int length;
    private int width;
    private boolean isPotted;
    private int numPots = 0;
    private int pH;
    private int area;

    private Plant[] plants;

    public Garden(boolean isIndoor, int length, int width, boolean isPotted, int numPot, int pH) {
        this.isIndoor = isIndoor;
        this.length = length;
        this.width = width;
        this.isPotted = isPotted;
        this.numPots = numPot;
        this.pH = pH;

        area = length * width;

        plants = new Plant[4];
    }

    public int getArea() {
        return area;
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

    public void setPlants(Plant[] plants) {
        this.plants = plants;
    }

    public Plant[] getPlants() {
        return plants;
    }

    @Override
    public String toString(){
        //Write out the object using Gson
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;
    }

}
