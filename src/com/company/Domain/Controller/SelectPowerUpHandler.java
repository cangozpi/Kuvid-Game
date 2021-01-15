package com.company.Domain.Controller;
import com.company.Domain.Models.PowerUpSelector;
import com.company.Enums.PowerUpType;


public class SelectPowerUpHandler  {
    private final PowerUpSelector powerUpSelector;
    public SelectPowerUpHandler() { this.powerUpSelector = new PowerUpSelector(); }
    public void selectPowerUp(PowerUpType powerUp){
        powerUpSelector.selectPowerUp(powerUp);
    }
    public boolean hasPowerUp(PowerUpType powerUpType){
        return powerUpSelector.hasPowerUp(powerUpType);
    }

}
