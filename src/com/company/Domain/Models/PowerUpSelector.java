package com.company.Domain.Models;

import com.company.Domain.Models.Projectile.PowerUp;
import com.company.Domain.Utility.Coordinate;
import com.company.Domain.Utility.Velocity;
import com.company.Enums.IProjectileType;
import com.company.Enums.PowerUpType;

import java.util.ArrayList;

public class PowerUpSelector {
    Inventory inventory = Inventory.getInstance();
    GunFactory gun = GunFactory.getInstance();


    public PowerUpSelector() {}

    public void selectPowerUp(PowerUpType powerUp){

           if( inventory.removePowerUp(powerUp)) {
               gun.loadGun(new PowerUp(new Coordinate(0, 0), new Velocity(0, 0), powerUp, true, 0, 0));
           }else  System.err.println("There are no " +powerUp + " to use.");
    }

    public boolean hasPowerUp(PowerUpType powerUp){
        return inventory.getPowerUpAmount(powerUp) > 0;
    }
}
