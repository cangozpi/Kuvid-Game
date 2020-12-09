package com.company.Domain.Models.Projectile;

import com.company.Domain.Utility.Coordinate;
import com.company.Domain.Utility.Velocity;

public abstract class Projectile {
    Coordinate coordinate;
    Velocity velocity;
    boolean isAmmo;
    int width;
    int height;


    public Projectile(Coordinate coordinate, Velocity velocity, boolean isAmmo,int height,int width){
        this.coordinate = coordinate;
        this.velocity = velocity;
        this.isAmmo = isAmmo;
        this.height = height;
        this.width = width;
    }


    public int getWidth() {return width; }

    public void setWidth(int width) { this.width = width; }

    public int getHeight() { return height; }

    public void setHeight(int height) { this.height = height; }

    public void move(){ }

    public boolean isAmmo() { return isAmmo; }

    public void setIsAmmo(boolean isAmmo) { this.isAmmo = isAmmo; }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public Velocity getVelocity() {
        return velocity;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public double getXCoordinate(){ return this.coordinate.getXCoordinate();}

    public double getYCoordinate(){ return this.coordinate.getYCoordinate();}

    public void setXCoordinate(double xCoordinate){ this.coordinate.setXCoordinate(xCoordinate);}

    public void setYCoordinate(double yCoordinate){ this.coordinate.setYCoordinate(yCoordinate);}

    public void setVelocity(Velocity velocity) {
        this.velocity = velocity;
    }
}
