package com.company.Domain.Controller;
import com.company.Domain.Models.PowerUpSelector;



public class SelectPowerUpHandler  {
    private final PowerUpSelector powerUpSelector;
    public SelectPowerUpHandler() { this.powerUpSelector = new PowerUpSelector(); }
    public void selectPowerUp(){
        powerUpSelector.selectPowerUp();
    }


}
