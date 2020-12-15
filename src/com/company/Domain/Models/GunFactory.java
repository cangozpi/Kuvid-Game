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
import com.company.UI.Objects.GameWindowFactory;

import static com.company.UI.Objects.GameWindowFactory.*;

public class GunFactory extends GunObserver {
    //instance variables
    private static GunFactory gun = null;
    private Coordinate position; //left top corner of gun
    private int angle; //looking left is 0, most right is 180 deg.
    private double gunWidth = L * 0.5;
    private double gunHeight = L;
    private Atom ammoAtom;
    private PowerUp ammoPowerUp;
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
            gun.loadGunWithAtom(AtomType.ALPHA);
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
        if( ammoAtom == null){
            ammoPowerUp.setVelocity(projectileVelocity);
            ammoPowerUp.setIsAmmo(false);
            GameFactory.getInstance().insertPowerUpShotFromGun(ammoPowerUp);
        }else{
            ammoAtom.setVelocity(projectileVelocity);
            ammoAtom.setIsAmmo(false);
            GameFactory.getInstance().insertAtom(ammoAtom);
         }




    }

    public void moveGun(DirectionType direction){
        //Code can be so complex that there are no obvious bugs or so simple that there are obviously no bugs
        if(direction.equals(DirectionType.RIGHT)){
            if(rightestPointOfTheGun.getXCoordinate() + gunWidth <= windowWidth){ // if can move right
                position.setXCoordinate(position.getXCoordinate() + L);
                rightestPointOfTheGun.setXCoordinate(rightestPointOfTheGun.getXCoordinate() + L);
                leftistPointOfTheGUn.setXCoordinate(rightestPointOfTheGun.getXCoordinate() + L);
                if( ammoAtom == null) {
                    ammoPowerUp.setXCoordinate(ammoPowerUp.getXCoordinate()+ L);
                    GunFactory.super.gunMovedEvent(position, angle, ammoAtom, ammoPowerUp);
                }else {
                    ammoAtom.setXCoordinate(ammoAtom.getXCoordinate()+ L);
                    GunFactory.super.gunMovedEvent(position, angle, ammoAtom, ammoPowerUp);
                }

            }
        }


        else if(direction.equals(DirectionType.LEFT)){
            if(leftistPointOfTheGUn.getXCoordinate() >= L){ // if can move left
                position.setXCoordinate(position.getXCoordinate() - L);
                rightestPointOfTheGun.setXCoordinate(rightestPointOfTheGun.getXCoordinate() - L);
                leftistPointOfTheGUn.setXCoordinate(rightestPointOfTheGun.getXCoordinate() - L);
                if( ammoAtom == null) {
                    ammoPowerUp.setXCoordinate(ammoPowerUp.getXCoordinate()- L);
                    GunFactory.super.gunMovedEvent(position, angle, ammoAtom,ammoPowerUp);
                }else {
                    ammoAtom.setXCoordinate(ammoAtom.getXCoordinate()- L);
                    GunFactory.super.gunMovedEvent(position, angle, ammoAtom, ammoPowerUp);
                }
            }
        }

    }

    public void rotateGun(DirectionType direction){


        if(direction.equals(DirectionType.CLOCKWISE)){
            if(angle >= 10){ // if can rotate
                angle -= 10;
                if( ammoAtom == null) {

                    GunFactory.super.gunMovedEvent(position, angle, ammoAtom, ammoPowerUp);
                }else {

                    GunFactory.super.gunMovedEvent(position, angle, ammoAtom, ammoPowerUp);
                }

            }
        }else if(direction.equals(DirectionType.ANTICLOCKWISE)){
            if(angle <= 170){ // if can rotate
                angle += 10;
                if( ammoAtom == null) {

                    GunFactory.super.gunMovedEvent(position, angle, ammoAtom, ammoPowerUp);
                }else {

                    GunFactory.super.gunMovedEvent(position, angle, ammoAtom, ammoPowerUp);
                }
            }
        }

    }

    public void loadGunWithAtom(AtomType atomType){              // gets ammo type from atom selector

        double xCoord = getPosition().getXCoordinate();    //TODO: position and angle calculations to line it up with the tip of the gun
        double yCoord = getPosition().getYCoordinate() - L/10;
        int ammoAngle = getAngle();
        AtomFactory atomFactory = new AtomFactory();
        Coordinate ammoCoord = new Coordinate(xCoord, yCoord);                                                // creates projectile and sends it to game
        Velocity ammoVelocity = new Velocity(ammoAngle, 0);
        setAmmoAtom(atomFactory.getInstance(ammoCoord, ammoVelocity, atomType, true,L/10,L/10));
        GameFactory.getInstance().setAmmoAtom(getAmmoAtom());
        GunFactory.super.gunMovedEvent(position, angle, ammoAtom, ammoPowerUp);
        if( ammoAtom == null) {
            inventory.addPowerUp(ammoPowerUp.getPowerUpType());
            ammoPowerUp = null;
        }

    }
    public void loadGunWithPowerUp(PowerUpType powerUpType){          //powerup version

        double xCoord = 0;    //TODO: position and angle calculations to line it up with the tip of the gun
        double yCoord = 0;
        int ammoAngle = 0;

        Coordinate ammoCoord = new Coordinate(xCoord, yCoord);                                                // creates projectile and sends it to game
        Velocity ammoVelocity = new Velocity(ammoAngle, 0);
        setAmmoPowerUp(new PowerUp(ammoCoord, ammoVelocity, powerUpType, true,0,0));    //TODO: powerup factory wifth
        GameFactory.getInstance().insertPowerUpShotFromGun(ammoPowerUp);
        GunFactory.super.gunMovedEvent(position, angle, ammoAtom, ammoPowerUp);
        if( ammoAtom != null) {
            inventory.addAtom(ammoAtom.getAtomType());
            ammoAtom = null;
        }


    }


    //getters and setters

    public Atom getAmmoAtom() {
        return ammoAtom;
    }

    public void setAmmoAtom(Atom ammoAtom) {
        this.ammoAtom = ammoAtom;
    }

    public PowerUp getAmmoPowerUp() {
        return ammoPowerUp;
    }

    public void setAmmoPowerUp(PowerUp ammoPowerUp) {
        this.ammoPowerUp = ammoPowerUp;
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
