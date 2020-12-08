package com.company.Domain.Controller;
import com.company.Domain.Models.PowerUpSelector;
public class SelectPowerUpHandler {
    private PowerUpSelector powerUpSelector;
    public SelectPowerUpHandler(PowerUpSelector powerUpSelector) { this.powerUpSelector = powerUpSelector; }
    public void selectPowerUp(){
        powerUpSelector.selectPowerUp();
    }
}
