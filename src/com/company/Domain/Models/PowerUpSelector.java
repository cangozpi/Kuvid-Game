package com.company.Domain.Models;

import com.company.Enums.PowerUpType;

import java.util.ArrayList;

public class PowerUpSelector {
    Inventory inventory = Inventory.getInstance();
    GunFactory gun = GunFactory.getInstance();
    private static PowerUpSelector powerupSelector = null;

    private PowerUpSelector() {}
    public static PowerUpSelector getInstance() {
        if (powerupSelector == null) powerupSelector = new PowerUpSelector();
        return powerupSelector;
    }
    public void selectPowerUp(){
        ArrayList<PowerUpType> legalPowerUpList = new ArrayList<>();
        for (PowerUpType powerUpType: PowerUpType.values()){
            if(inventory.getPowerUpAmount(powerUpType)>0)legalPowerUpList.add(powerUpType);
        }
        if(!legalPowerUpList.isEmpty()) {
            PowerUpType selectedPowerUp = legalPowerUpList.get((int) (Math.random() * (legalPowerUpList.size())));
            inventory.removePowerUp(selectedPowerUp);
            gun.loadGun(selectedPowerUp);
        }else System.err.println("There are no legal PowerUps to use.");
    }

}
