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


import static com.company.UI.Objects.GameWindowFactory.*;

public class GunFactory {
    //instance variables
    private static GunFactory gun = null;
    private static int L;
    private Coordinate position; //left top corner of gun
    private int angle; //looking left is 0, most right is 180 deg.
    private double gunWidth = L * 0.5;
    private double gunHeight = L;
    private Projectile ammo;
    private Coordinate rightestPointOfTheGun;
    private Coordinate leftistPointOfTheGUn;
    private Inventory inventory;


    private GunFactory() {
    }

    public static GunFactory getInstance() {
        if (gun == null) {
            gun = new GunFactory();
            gun.L = GameFactory.getInstance().getL();
            gun.gunWidth = L * 0.5;
            gun.gunHeight = L;
            gun.setPosition(new Coordinate(((GameFactory.getInstance().getGameWindowWidth() / 2) - L / 4),
                    GameFactory.getInstance().getGameWindowHeight() - L - 35));
            gun.setAngle(90);
            gun.rightestPointOfTheGun = new Coordinate(gun.getPosition().getXCoordinate() + gun.getGunWidth(), gun.getPosition().getYCoordinate());
            gun.leftistPointOfTheGUn = gun.getPosition();
            gun.setInventory();
            GameFactory.getInstance().moveGun(new Coordinate(((GameFactory.getInstance().getGameWindowWidth() / 2) - L / 4), GameFactory.getInstance().getGameWindowHeight() - L - 35),
                    90, null);
        }
        return gun;
    }


    //methods
    public void shootGun() {
        if (ammo != null) {
            Velocity projectileVelocity = new Velocity(getAngle(), L / 30 * ammo.getSpeedMultiplier());
            ammo.setVelocity(projectileVelocity);
            ammo.setIsAmmo(false);
            GameFactory.getInstance().insertProjectileFromGun(ammo);
            ammo = null;
        }
    }

    public void moveGun(DirectionType direction) {
        // Requires gun to be initialized
        // Modifes this.position, this.ammo.position, gameFactory.gunPosition and gameFactory.ammo.position by + L/30 for the right direction and - L/30 for the left direction
        if (direction.equals(DirectionType.RIGHT)) {
            if( ammo.getXCoordinate() + ammo.getWidth()> getPosition().getXCoordinate() + gunWidth){
                if (ammo.getXCoordinate() + ammo.getWidth() + L / 30<= GameFactory.getInstance().getGameWindowWidth()) { // if can move right
                    position.setXCoordinate(position.getXCoordinate() + L / 30);
                    if (ammo != null) {
                        ammo.setXCoordinate(ammo.getXCoordinate() + L / 30);
                    }
                    GameFactory.getInstance().moveGun(position, angle, ammo);
                }
            } else if (getPosition().getXCoordinate() + L / 30 + L <= GameFactory.getInstance().getGameWindowWidth()) { // if can move right
                position.setXCoordinate(position.getXCoordinate() + L / 30);
                if (ammo != null) {
                    ammo.setXCoordinate(ammo.getXCoordinate() + L / 30);
                }
                GameFactory.getInstance().moveGun(position, angle, ammo);
            }

        } else if (direction.equals(DirectionType.LEFT)) {
            if( ammo.getXCoordinate() < getPosition().getXCoordinate()){
                if (ammo.getXCoordinate() >= L / 30) { // if can move left
                    position.setXCoordinate(position.getXCoordinate() - L / 30);
                    if (ammo != null) {
                        ammo.setXCoordinate(ammo.getXCoordinate() - L / 30);
                    }
                    GameFactory.getInstance().moveGun(position, angle, ammo);
                }
            }else if (getPosition().getXCoordinate() >= L / 30) { // if can move left
                position.setXCoordinate(position.getXCoordinate() - L / 30);
                if (ammo != null) {
                    ammo.setXCoordinate(ammo.getXCoordinate() - L / 30);
                }
                GameFactory.getInstance().moveGun(position, angle, ammo);
            }
        }


    }

    public void rotateGun(DirectionType direction) {
        int angleChange = 10;
        double xCoord = this.getPosition().getX();
        double yCoord = this.getPosition().getY();

        if (direction.equals(DirectionType.CLOCKWISE)) {
            if(angle != 0){
                angle -= 10;
                if (ammo.getProjectileType().toString().contains("powerUp")) {
                    xCoord += L * Math.cos(Math.toRadians(angle));
                    yCoord += L - L * Math.sin(Math.toRadians(angle)) - L/4;
                }else{
                xCoord += L * Math.cos(Math.toRadians(angle)) + Math.sqrt(Math.pow(L/10,2)+ Math.pow(3*L/20,2));
                yCoord += L - L * Math.sin(Math.toRadians(angle)) - ammo.getHeight();
                }
            }
        } else {
            if(angle != 180){
                angle += 10;
                if (ammo.getProjectileType().toString().contains("powerUp")) {
                    xCoord += L * Math.cos(Math.toRadians(angle));
                    yCoord += L - L * Math.sin(Math.toRadians(angle)) - L/4;
                }else{
                xCoord += L * Math.cos(Math.toRadians(angle)) + Math.sqrt(Math.pow(L/10,2)+ Math.pow(3*L/20,2));
                yCoord += L - L * Math.sin(Math.toRadians(angle)) - ammo.getHeight();
                }
            }
        }


        ammo.setXCoordinate(xCoord);
        ammo.setYCoordinate(yCoord);
        GameFactory.getInstance().moveGun(position, angle, ammo);
    }


    public void loadGun(Projectile newAmmo) {              // gets ammo from atom selector

        double xCoord = getPosition().getXCoordinate();   //TODO: position and angle calculations to line it up with the tip of the gun for powerUp
        double yCoord = getPosition().getYCoordinate();

        if (newAmmo.getProjectileType().toString().contains("powerUp")) {
            xCoord += L * Math.cos(Math.toRadians(angle));
            yCoord += L - L * Math.sin(Math.toRadians(angle)) - L/4;
        }else{
        xCoord += L * Math.cos(Math.toRadians(angle)) + Math.sqrt(Math.pow(L/10,2)+ Math.pow(3*L/20,2));
        yCoord += L - L * Math.sin(Math.toRadians(angle)) - L/10;
        }
        int ammoAngle = getAngle();
        Coordinate ammoCoord = new Coordinate(xCoord, yCoord);                                    // corrects location and velocity, and sends it to game
        Velocity ammoVelocity = new Velocity(ammoAngle, 0);

        if (this.ammo != null) {
            inventory.addAmmo(this.ammo);
        }

        setAmmo(newAmmo);
        this.ammo.setCoordinate(ammoCoord);
        this.ammo.setVelocity(ammoVelocity);
        this.ammo.setHeight(L / 10);
        this.ammo.setWidth(L / 10);
        GameFactory.getInstance().setAmmo(this.ammo);
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

    public Coordinate getRightestPointOfTheGun() {
        return rightestPointOfTheGun;
    }

    public void setRightestPointOfTheGun(Coordinate rightestPointOfTheGun) {
        this.rightestPointOfTheGun = rightestPointOfTheGun;
    }

    public Coordinate getLeftistPointOfTheGUn() {
        return leftistPointOfTheGUn;
    }

    public void setLeftistPointOfTheGUn(Coordinate leftistPointOfTheGUn) {
        this.leftistPointOfTheGUn = leftistPointOfTheGUn;
    }

    public int getL() {
        return this.L;
    }
}
