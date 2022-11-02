package com.grupp2.sankaskepp.Remaining;

public class XYposition {

    public String xValue;

    public String yValue;

    public String xyValue;

    public boolean wasHit;

    public boolean hasShip;

    public XYposition(String xValue, String yValue) {
        this.xValue = xValue;
        this.yValue = yValue;
        this.xyValue  = xValue.concat(yValue);
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

    public boolean isHasShip() {
        return hasShip;
    }

    public void setHasShip(boolean hasShip) {
        this.hasShip = hasShip;
    }

    public String getXyValue() {
        return xyValue;
    }

    public void setXyValue(String xyValue) {
        this.xyValue = xyValue;
    }
}
