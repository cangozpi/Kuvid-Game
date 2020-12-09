package com.company.Domain.Models;


import com.company.Domain.Models.Projectile.Atom;
import com.company.Domain.Models.Projectile.PowerUp;
import com.company.Domain.Models.Projectile.Projectile;
import com.company.Domain.Observer.GunObserver;
import com.company.Domain.Utility.Coordinate;
import com.company.Domain.Utility.Velocity;
import com.company.Enums.AtomType;
import com.company.Enums.DirectionType;
import com.company.Enums.PowerUpType;

import static com.company.UI.Objects.GameWindowFactory.*;

public class GunFactory extends GunObserver {
    //instance variables
    private static GunFactory gun = null;
    private Coordinate position; //left top corner of gun
    private int angle; //looking left is 0, most right is 180 deg.
    private double gunWidth = L * 0.5;
    private double gunHeight = L;
    private Projectile ammo;
    private Coordinate rightestPointOfTheGun;
    private Coordinate leftistPointOfTheGUn;
    private boolean loaded;
    private GunFactory() {}
    public static GunFactory getInstance() {
        if (gun == null) gun = new GunFactory();
        return gun;
    }
    public void setGun(Coordinate position, Projectile ammo) {
        this.position = position;
        this.angle = 90;
        this.ammo = ammo;
        this.rightestPointOfTheGun = new Coordinate(position.getXCoordinate() + gunWidth, position.getYCoordinate());
        this.leftistPointOfTheGUn = position;
    }

    //methods
    public void shootGun(){

        Velocity projectileVelocity = new Velocity(getAngle(), 0);  //TODO: velocity
        ammo.setVelocity(projectileVelocity);
        ammo.setIsAmmo(false);
        GameFactory.getInstance().insertProjectile(ammo);

    }

    public void moveGun(DirectionType direction){
        //Code can be so complex that there are no obvious bugs or so simple that there are obviously no bugs
        if(direction.equals(DirectionType.RIGHT)){
            if(rightestPointOfTheGun.getXCoordinate() + gunWidth <= windowWidth){ // if can move right
                position.setXCoordinate(position.getXCoordinate() + L);
                rightestPointOfTheGun.setXCoordinate(rightestPointOfTheGun.getXCoordinate() + L);
                leftistPointOfTheGUn.setXCoordinate(rightestPointOfTheGun.getXCoordinate() + L);
                ammo.setXCoordinate(ammo.getXCoordinate()+ L);
                GunFactory.super.gunMovedEvent(position, angle, ammo);
            }
        }


        else if(direction.equals(DirectionType.LEFT)){
            if(leftistPointOfTheGUn.getXCoordinate() >= L){ // if can move left
                position.setXCoordinate(position.getXCoordinate() - L);
                rightestPointOfTheGun.setXCoordinate(rightestPointOfTheGun.getXCoordinate() - L);
                leftistPointOfTheGUn.setXCoordinate(rightestPointOfTheGun.getXCoordinate() - L);
                ammo.setXCoordinate(ammo.getXCoordinate()-L);
                GunFactory.super.gunMovedEvent(position, angle, ammo);
            }
        }
        
     }

    public void rotateGun(DirectionType direction){


        if(direction.equals(DirectionType.CLOCKWISE)){
            if(angle <= 170){ // if can rotate
                angle += 10;
                GunFactory.super.gunMovedEvent(position, angle, ammo);
            }
        }else if(direction.equals(DirectionType.ANTICLOCKWISE)){
            if(angle >= 10){ // if can rotate
                angle -= 10;
                GunFactory.super.gunMovedEvent(position, angle, ammo);
            }
        }

    }

    public void loadGunWithAtom(AtomType atomType){              // gets ammo type from atom selector

        double xCoord = 0;    //TODO: position and angle calculations to line it up with the tip of the gun
        double yCoord = 0;
        int ammoAngle = 0;

        Coordinate ammoCoord = new Coordinate(xCoord, yCoord);                                                // creates projectile and sends it to game
        Velocity ammoVelocity = new Velocity(ammoAngle, 0);
        setAmmo(new Atom(ammoCoord, ammoVelocity, atomType, true,L/10,L/10));    //TODO: atom factory
        GameFactory.getInstance().setAmmo(ammo);
        GunFactory.super.gunMovedEvent(position, angle, ammo);
    }
    public void loadGunWithPowerUp(PowerUpType powerUpType){          //powerup version

        double xCoord = 0;    //TODO: position and angle calculations to line it up with the tip of the gun
        double yCoord = 0;
        int ammoAngle = 0;

        Coordinate ammoCoord = new Coordinate(xCoord, yCoord);                                                // creates projectile and sends it to game
        Velocity ammoVelocity = new Velocity(ammoAngle, 0);
        setAmmo(new PowerUp(ammoCoord, ammoVelocity, powerUpType, true,0,0));    //TODO: powerup factory wifth
        GameFactory.getInstance().insertProjectile(ammo);
        GunFactory.super.gunMovedEvent(position, angle, ammo);

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

    public Projectile getAmmo() {
        return ammo;
    }

    public void setAmmo(Projectile ammo) {
        this.ammo = ammo;
    }

    public Coordinate getRightestPointOfTheGun() { return rightestPointOfTheGun; }

    public void setRightestPointOfTheGun(Coordinate rightestPointOfTheGun) { this.rightestPointOfTheGun = rightestPointOfTheGun; }

    public Coordinate getLeftistPointOfTheGUn() { return leftistPointOfTheGUn; }

    public void setLeftistPointOfTheGUn(Coordinate leftistPointOfTheGUn) { this.leftistPointOfTheGUn = leftistPointOfTheGUn; }
}
