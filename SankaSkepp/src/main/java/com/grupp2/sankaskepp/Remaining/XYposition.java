package com.grupp2.sankaskepp.Remaining;

public class XYposition {

    //Properties
    public String xValue;

    public String yValue;

    public String xyValue;

    public boolean wasHit;

    public boolean hasBoat;

    //Constructor

    public XYposition() {
    }

    public XYposition(String xValue, String yValue, boolean wasHit, boolean hasBoat) {
        this.xValue = xValue;
        this.yValue = yValue;
        this.wasHit = wasHit;
        this.hasBoat = hasBoat;
        this.xyValue  = xValue.concat(yValue);
    }


    //Getters & Setters
    public XYposition(String xyValue) {
        this.xyValue = xyValue;
    }

    public String getxValue() {
        return xValue;
    }

    public void setxValue(String xValue) {
        this.xValue = xValue;
    }

    public String getyValue() {
        return yValue;
    }

    public void setyValue(String yValue) {
        this.yValue = yValue;
    }

    public boolean isWasHit() {
        return wasHit;
    }

    public void setWasHit(boolean wasHit) {
        this.wasHit = wasHit;
    }

    public boolean isHasBoat() {
        return hasBoat;
    }

    public void setHasBoat(boolean hasBoat) {
        this.hasBoat = hasBoat;
    }

    public String getXyValue() {
        return xyValue;
    }

    public void setXyValue(String xyValue) {
        this.xyValue = xyValue;
    }
}
