package com.IAD;

import java.io.Serializable;

public class Hit implements Serializable {
    private String enterX = null;
    private double x;
    private String enterY = null;
    private double y;
    private String enterR = null;
    private int r;
    private String result = null;
    private boolean res;

    public String getEnterX(){
        return enterX;
    }
    public String getEnterY(){
        return enterY;
    }
    public String getEnterR(){
        return enterR;
    }
    public void setEnterX(String enterX){
        this.enterX = enterX;
    }
    public void setEnterY(String enterY){
        this.enterY = enterY;
    }
    public void setEnterR(String enterR){
        this.enterR = enterR;
    }
    public String getResult(){
        return result;
    }
    public void setResult(String result){
        this.result = result;
    }

    public double getX() {
        return x;
    }
    public void setX(double x) {
        this.x = x;
    }
    public double getY() {
        return y;
    }
    public void setY(double y) {
        this.y = y;
    }
    public int getR() {
        return r;
    }
    public void setR(int r) {
        this.r = r;
    }
    public boolean isRes() {
        return res;
    }
    public void setRes(boolean res) {
        this.res = res;
    }
}
