package com.company.Domain.Models;


import com.company.Domain.Models.Projectile.Atom;
import com.company.Domain.Models.Projectile.PowerUp;
import com.company.Domain.Models.Projectile.Projectile;
import com.company.Domain.Utility.Coordinate;
import com.company.Domain.Utility.Velocity;
import com.company.Enums.AtomType;
import com.company.Enums.DirectionType;
import com.company.Enums.PowerUpType;

import static com.company.UI.Objects.GameWindowFactory.*;

public class Gun {
    //instance variables
    private Coordinate position; //left top corner of gun
    private int angle; //looking left is 0, most right is 180 deg.
    private double gunWidth = L * 0.5;
    private double gunHeight = L;
    private Projectile ammo;
    private Coordinate rightestPointOfTheGun;
    private Coordinate leftistPointOfTheGUn;
    private boolean loaded;

    public Gun(Coordinate position, Projectile ammo) {
        this.position = position;
        this.angle = 90;
        this.ammo = ammo;
        this.rightestPointOfTheGun = new Coordinate(position.getXCoordinate() + gunWidth, position.getYCoordinate());
        this.leftistPointOfTheGUn = position;
    }

    //methods
    private void shootGun(){

        Velocity projectileVelocity = new Velocity(getAngle(), 0);  //TODO: velocity
        ammo.setVelocity(projectileVelocity);
        ammo.setIsAmmo(false);

        GameFactory.getInstance().insertProjectile(ammo);        //TODO: not a new projectile, it should update the old one

    }

    private void moveGun(DirectionType direction){

        if(direction.equals(DirectionType.RIGHT)){
            if(rightestPointOfTheGun.getXCoordinate() + gunWidth <= windowWidth){ // if can move right
                position.setXCoordinate(position.getXCoordinate() + L);
                rightestPointOfTheGun.setXCoordinate(rightestPointOfTheGun.getXCoordinate() + L);
                leftistPointOfTheGUn.setXCoordinate(rightestPointOfTheGun.getXCoordinate() + L);
            }
        }


        else if(direction.equals(DirectionType.LEFT)){
            if(leftistPointOfTheGUn.getXCoordinate() >= L){ // if can move left
                position.setXCoordinate(position.getXCoordinate() - L);
                rightestPointOfTheGun.setXCoordinate(rightestPointOfTheGun.getXCoordinate() - L);
                leftistPointOfTheGUn.setXCoordinate(rightestPointOfTheGun.getXCoordinate() - L);

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

    private void loadGunWithAtom(AtomType atomType){              // gets ammo type from atom selector

        double xCoord = 0;    //TODO: position and angle calculations to line it up with the tip of the gun
        double yCoord = 0;
        int ammoAngle = 0;

        Coordinate ammoCoord = new Coordinate(xCoord, yCoord);                                                // creates projectile and sends it to game
        Velocity ammoVelocity = new Velocity(ammoAngle, 0);
        setAmmo(new Atom(ammoCoord, ammoVelocity, atomType, true));    //TODO: atom factory
        GameFactory.getInstance().insertProjectile(ammo);
    }
    private void loadGunWithPowerUp(PowerUpType powerUpType){          //powerup version

        double xCoord = 0;    //TODO: position and angle calculations to line it up with the tip of the gun
        double yCoord = 0;
        int ammoAngle = 0;

        Coordinate ammoCoord = new Coordinate(xCoord, yCoord);                                                // creates projectile and sends it to game
        Velocity ammoVelocity = new Velocity(ammoAngle, 0);
        setAmmo(new PowerUp(ammoCoord, ammoVelocity, powerUpType, true));    //TODO: powerup factory
        GameFactory.getInstance().insertProjectile(ammo);

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
