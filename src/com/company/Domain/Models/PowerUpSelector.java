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

    public void selectPowerUp(){
        ArrayList<PowerUpType> legalPowerUpList = new ArrayList<>();
        for (PowerUpType powerUpType: PowerUpType.values()){
            if(inventory.getPowerUpAmount(powerUpType)>0)legalPowerUpList.add(powerUpType);
        }
        if(!legalPowerUpList.isEmpty()) {
            PowerUpType selectedPowerUp = legalPowerUpList.get((int) (Math.random() * (legalPowerUpList.size())));
            inventory.removePowerUp(selectedPowerUp);
            gun.loadGun(new PowerUp(new Coordinate(0,0), new Velocity(0,0),selectedPowerUp,true,0,0));
        }else System.err.println("There are no legal PowerUps to use.");
    }

}
