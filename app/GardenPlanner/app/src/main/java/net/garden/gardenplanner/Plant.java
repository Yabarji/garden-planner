package net.garden.gardenplanner;

/**
 * Created by Varun on 2017-02-11.
 */

public class Plant {

    private String name;
    private int area;
    private String type;
    private boolean isVertical;
    private int pH;
    private int time;
    private int yield;

    private int ID;
    private int[] combative;
    private int[] companion;

    public int[] getCompanion() {
        return companion;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public int getArea() {
        return area;
    }

    public String getType() {
        return type;
    }

    public boolean isVertical() {
        return isVertical;
    }

    public int getpH() {
        return pH;
    }

    public int getTime() {
        return time;
    }

    public int getYield() {
        return yield;
    }

    public int getID() {
        return ID;
    }

    public int[] getCombative() {
        return combative;
    }

}
