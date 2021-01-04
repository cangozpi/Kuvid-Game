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

public class GunFactory{
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


    private GunFactory() {}

    public static GunFactory getInstance() {
        if (gun == null) {
            gun = new GunFactory();
            gun.L = GameFactory.getInstance().getL();
            gun.gunWidth = L* 0.5;
            gun.gunHeight = L;
            gun.setPosition(new Coordinate(((GameFactory.getInstance().getGameWindowWidth() / 2) - L / 4),
                    GameFactory.getInstance().getGameWindowHeight() - L - 35));
            gun.setAngle(90);
            gun.rightestPointOfTheGun = new Coordinate(gun.getPosition().getXCoordinate() + gun.getGunWidth(), gun.getPosition().getYCoordinate());
            gun.leftistPointOfTheGUn = gun.getPosition();
            gun.setInventory();
            GameFactory.getInstance().moveGun(new Coordinate(((GameFactory.getInstance().getGameWindowWidth() / 2) - L / 4),GameFactory.getInstance().getGameWindowHeight() - L - 35),
                    90,null);
        }
        return gun;
    }


    //methods
    public void shootGun(){
        if(ammo != null){
            Velocity projectileVelocity = new Velocity(getAngle(), L/30 * ammo.getSpeedMultiplier());  //TODO: velocity
            ammo.setVelocity(projectileVelocity);
            ammo.setIsAmmo(false);
            GameFactory.getInstance().insertProjectileFromGun(ammo);
            ammo = null;
        }
    }

    public void moveGun(DirectionType direction){
        //gun position has problems need some calculations
        if(direction.equals(DirectionType.RIGHT)){
            if(getPosition().getXCoordinate() + gunWidth + L/30 + L <= GameFactory.getInstance().getGameWindowWidth()){ // if can move right
                position.setXCoordinate(position.getXCoordinate() + L/30);
                ammo.setXCoordinate(ammo.getXCoordinate() + L/30);
                GameFactory.getInstance().moveGun(position, angle, ammo);
            }

        }



        else if(direction.equals(DirectionType.LEFT)){
            if(getPosition().getXCoordinate() >= L/30){ // if can move left
                position.setXCoordinate(position.getXCoordinate() - L/30);
                ammo.setXCoordinate(ammo.getXCoordinate()- L/30);
                GameFactory.getInstance().moveGun(position, angle, ammo);
            }
        }


    }

    public void rotateGun(DirectionType direction){
        int angleChange = 10;

        if(direction.equals(DirectionType.CLOCKWISE)){
            if(angle >= 10){ // if can rotate
                angle -= 10;

   //             ammo.setXCoordinate(ammo.getXCoordinate() + 2*L*Math.sin(Math.toRadians(angleChange/2)) * Math.sin(Math.toRadians(angle-angleChange/2)));
   //             ammo.setYCoordinate(ammo.getYCoordinate() + 2*L*Math.sin(Math.toRadians(angleChange/2)) * Math.cos(Math.toRadians(angle-angleChange/2)));

                GameFactory.getInstance().moveGun(position, angle, ammo);
            }


        }else if(direction.equals(DirectionType.ANTICLOCKWISE)){
            if(angle <= 170) { // if can rotate
                angle += 10;

//                ammo.setXCoordinate(ammo.getXCoordinate() + 2*L*Math.sin(Math.toRadians(-angleChange/2))*Math.sin(Math.toRadians(angle + angleChange/2)));
//                ammo.setYCoordinate(ammo.getYCoordinate() + 2*L*Math.sin(Math.toRadians(-angleChange/2))*Math.cos(Math.toRadians(angle + angleChange/2)));
                GameFactory.getInstance().moveGun(position, angle, ammo);


            }
        }

    }

    public void loadGun(Projectile newAmmo){              // gets ammo type from atom selector     //TODO gets atom or powerup

        double xCoord = getPosition().getXCoordinate() + 3*L/4;    //TODO: position and angle calculations to line it up with the tip of the gun
        double yCoord = getPosition().getYCoordinate() - L/10;
        int ammoAngle = getAngle();
        Coordinate ammoCoord = new Coordinate(xCoord, yCoord);                                                // creates projectile and sends it to game
        Velocity ammoVelocity = new Velocity(ammoAngle, 0);

        if(this.ammo != null) {
            inventory.addAmmo(this.ammo);
        }

        setAmmo(newAmmo);
        this.ammo.setCoordinate(ammoCoord);
        this.ammo.setVelocity(ammoVelocity);
        this.ammo.setHeight((int)(L/10));
        this.ammo.setWidth((int)(L/10));
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

    public Coordinate getRightestPointOfTheGun() { return rightestPointOfTheGun; }

    public void setRightestPointOfTheGun(Coordinate rightestPointOfTheGun) { this.rightestPointOfTheGun = rightestPointOfTheGun; }

    public Coordinate getLeftistPointOfTheGUn() { return leftistPointOfTheGUn; }

    public void setLeftistPointOfTheGUn(Coordinate leftistPointOfTheGUn) { this.leftistPointOfTheGUn = leftistPointOfTheGUn; }

    public int getL() {
        return this.L;
    }
}
