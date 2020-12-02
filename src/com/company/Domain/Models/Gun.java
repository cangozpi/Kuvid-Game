package com.company.Domain.Models;

import com.company.Domain.Models.Projectile.Ammo;
import com.company.Domain.Utility.Coordinate;
import com.company.Enums.DirectionType;

import static com.company.UI.Objects.GameWindowFactory.*;

public class Gun {
    //instance variables
    private Coordinate position; //left top corner of gun
    private int angle; //looking left is 0, most right is 180 deg.
    private double gunWidth = L / 2;
    private double gunHeight = L;
    private Ammo ammo;

    public Gun(Coordinate position, Ammo ammo) {
        this.position = position;
        this.angle = 90;
        this.ammo = ammo;
    }

    //methods
    //private void shootGun(){} //TODO: implement this method

    private void moveGun(DirectionType direction){
        if(direction.equals(DirectionType.RIGHT)){//TODO: sin cos angle check
            if(position.getXCoordinate() + gunWidth <= windowWidth){ // if can move right
                position.setXCoordinate(position.getXCoordinate() + L);
            }
        }else if(direction.equals(DirectionType.LEFT)){
            if(position.getXCoordinate() >= L){ // if can move left
                position.setXCoordinate(position.getXCoordinate() - L);
            }
        }
     }

    private void rotateGun(DirectionType direction){
        if(direction.equals(DirectionType.CLOCKWISE)){
            if(angle <= 170){ // if can rotate
                angle += 10;
            }
        }else if(direction.equals(DirectionType.ANTICLOCKWISE)){
            if(angle >= 10){ // if can rotate
                angle -= 10;
            }
        }

    }

    //getters and setters
    public Coordinate getPosition() {
        return position;
    }

    public void setPosition(Coordinate position) {
        this.position = position;
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public Ammo getAmmo() {
        return ammo;
    }

    public void setAmmo(Ammo ammo) {
        this.ammo = ammo;
    }
}
