package com.company.Domain.Models;


import com.company.Domain.Models.Projectile.Atom;
import com.company.Domain.Models.Projectile.PowerUp;
import com.company.Domain.Models.Projectile.Projectile;

import com.company.Domain.Utility.Coordinate;
import com.company.Domain.Utility.Velocity;
import com.company.Enums.AtomType;
import com.company.Enums.DirectionType;
import com.company.Enums.IProjectileType;
import com.company.Enums.PowerUpType;
import com.company.UI.Objects.GameWindowFactory;

import java.util.regex.Pattern;

import static com.company.UI.Objects.GameWindowFactory.*;

public class GunFactory{
    //instance variables
    private static GunFactory gun = null;
    private Coordinate position; //left top corner of gun
    private int angle; //looking left is 0, most right is 180 deg.
    private double gunWidth = L * 0.5;
    private double gunHeight = L;
    private Projectile ammo;
    private Coordinate rightestPointOfTheGun;
    private Coordinate leftistPointOfTheGUn;
    private Inventory inventory;

    private GunFactory() {}

    public static GunFactory getInstance() {
        if (gun == null) {
            gun = new GunFactory();
            gun.setPosition(new Coordinate(((GameFactory.getInstance().getGameWindowWidth() / 2) - GameFactory.getInstance().L / 4),
                    GameFactory.getInstance().getGameWindowHeight() -  GameFactory.getInstance().L));
            gun.setAngle(90);
            gun.rightestPointOfTheGun = new Coordinate(gun.getPosition().getXCoordinate() + gun.getGunWidth(), gun.getPosition().getYCoordinate());
            gun.leftistPointOfTheGUn = gun.getPosition();
            gun.loadGun(AtomType.ALPHA);
            gun.setInventory();

        }
        return gun;
    }
    public void setGun(Coordinate position, Projectile ammo) {    /// temporary
        this.position = position;
        this.angle = 90;

        this.rightestPointOfTheGun = new Coordinate(position.getXCoordinate() + gunWidth, position.getYCoordinate());
        this.leftistPointOfTheGUn = position;
    }

    //methods
    public void shootGun(){

        Velocity projectileVelocity = new Velocity(getAngle(), 10);  //TODO: velocity
        ammo.setVelocity(projectileVelocity);
        ammo.setIsAmmo(false);
        GameFactory.getInstance().insertProjectileFromGun(ammo);
        ammo = null;
    }

    public void moveGun(DirectionType direction){
        //gun position has problems need some calculations
        if(direction.equals(DirectionType.RIGHT)){
            if(rightestPointOfTheGun.getXCoordinate() + gunWidth <= windowWidth){ // if can move right
                position.setXCoordinate(position.getXCoordinate() + L);
                rightestPointOfTheGun.setXCoordinate(rightestPointOfTheGun.getXCoordinate() + L);
                leftistPointOfTheGUn.setXCoordinate(rightestPointOfTheGun.getXCoordinate() + L);
                ammo.setXCoordinate(ammo.getXCoordinate()+ L);
                GameFactory.getInstance().moveGun(position, angle, ammo);
            }

        }



        else if(direction.equals(DirectionType.LEFT)){
            if(leftistPointOfTheGUn.getXCoordinate() >= L){ // if can move left
                position.setXCoordinate(position.getXCoordinate() - L);
                rightestPointOfTheGun.setXCoordinate(rightestPointOfTheGun.getXCoordinate() - L);
                leftistPointOfTheGUn.setXCoordinate(rightestPointOfTheGun.getXCoordinate() - L);

                    ammo.setXCoordinate(ammo.getXCoordinate()- L);
                    GameFactory.getInstance().moveGun(position, angle, ammo);
            }
        }


    }

    public void rotateGun(DirectionType direction){


        if(direction.equals(DirectionType.CLOCKWISE)){
            if(angle >= 10){ // if can rotate
                angle -= 10;
                GameFactory.getInstance().moveGun(position, angle, ammo);
            }


        }else if(direction.equals(DirectionType.ANTICLOCKWISE)){
            if(angle <= 170) { // if can rotate
                angle += 10;

                GameFactory.getInstance().moveGun(position, angle, ammo);
            }
        }

    }

    public void loadGun(IProjectileType ammoType){              // gets ammo type from atom selector

        double xCoord = getPosition().getXCoordinate();    //TODO: position and angle calculations to line it up with the tip of the gun
        double yCoord = getPosition().getYCoordinate() - L/10;
        int ammoAngle = getAngle();
        Coordinate ammoCoord = new Coordinate(xCoord, yCoord);                                                // creates projectile and sends it to game
        Velocity ammoVelocity = new Velocity(ammoAngle, 0);


        if(ammo != null) {
            inventory.addAmmo(ammo.getProjectileType());
        }

        if(Pattern.matches(".*atom$", ammoType.toString())) {

            AtomFactory atomFactory = new AtomFactory();
            setAmmo(atomFactory.getInstance(ammoCoord, ammoVelocity, ammoType, true,L/10,L/10));
        }else{
            setAmmo(new PowerUp(ammoCoord, ammoVelocity, ammoType, true,0,0));    //TODO: powerup factory width

        }

        GameFactory.getInstance().setAmmo(ammo);






        GameFactory.getInstance().setAmmo(getAmmo());


    }



    //getters and setters

    public void setAmmo(Projectile ammo) {
        this.ammo = ammo;
    }

    public Projectile getAmmo() {
        return ammo;
    }



    public double getGunWidth() {
        return gunWidth;
    }

    public void setGunWidth(double gunWidth) {
        this.gunWidth = gunWidth;
    }

    public double getGunHeight() {
        return gunHeight;
    }

    public void setGunHeight(double gunHeight) {
        this.gunHeight = gunHeight;
    }

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



    public void setInventory() {
        this.inventory = Inventory.getInstance();
    }

    public Coordinate getRightestPointOfTheGun() { return rightestPointOfTheGun; }

    public void setRightestPointOfTheGun(Coordinate rightestPointOfTheGun) { this.rightestPointOfTheGun = rightestPointOfTheGun; }

    public Coordinate getLeftistPointOfTheGUn() { return leftistPointOfTheGUn; }

    public void setLeftistPointOfTheGUn(Coordinate leftistPointOfTheGUn) { this.leftistPointOfTheGUn = leftistPointOfTheGUn; }
}
