package com.company.Domain.Models.Projectile;

import com.company.Domain.Utility.Coordinate;
import com.company.Domain.Utility.Velocity;
import com.company.Enums.IProjectileType;

import java.util.regex.Pattern;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public abstract class Projectile {
    Coordinate coordinate;
    Velocity velocity;
    boolean isAmmo;
    int width;
    int height;
    IProjectileType projectileType;
    private double speedMultiplier;




    public Projectile(Coordinate coordinate, Velocity velocity, boolean isAmmo,int height,int width,IProjectileType projectileType){
        this.coordinate = coordinate;
        this.velocity = velocity;
        this.isAmmo = isAmmo;
        this.height = height;
        this.width = width;
        this.projectileType = projectileType;
        this.speedMultiplier = 1;
    }
    public Projectile(Coordinate coordinate, Velocity velocity, boolean isAmmo,int height,int width){
        this.coordinate = coordinate;
        this.velocity = velocity;
        this.isAmmo = isAmmo;
        this.height = height;
        this.width = width;
        this.speedMultiplier = 1;

    }

    public double getSpeedMultiplier() { return speedMultiplier; }

    public void setSpeedMultiplier(double speedMultiplier) { this.speedMultiplier = speedMultiplier; }

    public IProjectileType getProjectileType(){return projectileType; }

    public int getWidth() {return width; }

    public void setWidth(int width) { this.width = width; }

    public int getHeight() { return height; }

    public void setHeight(int height) { this.height = height; }

    public void move(){
        int angle = getVelocity().getAngle();
        double speed = getVelocity().getSpeed();
        setXCoordinate(getXCoordinate() - speed*cos(Math.toRadians(180-angle)));
        setYCoordinate(getYCoordinate() - speed*sin(Math.toRadians(angle)));
    }

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

    public void setAmmo(boolean ammo) {
        isAmmo = ammo;
    }




}
