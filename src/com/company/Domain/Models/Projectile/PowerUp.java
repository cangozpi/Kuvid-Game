package com.company.Domain.Models.Projectile;

import com.company.Domain.Utility.Coordinate;
import com.company.Domain.Utility.Velocity;
import com.company.Enums.PowerUpType;

public class PowerUp extends Projectile{
    private PowerUpType powerUpType;

    public PowerUp(Coordinate coordinate, Velocity velocity, PowerUpType powerUpType, boolean isAmmo, int height, int width) {
        super(coordinate, velocity, isAmmo, height, width);
        this.powerUpType = powerUpType;
    }

    //getters and setters
    public PowerUpType getPowerUpType() {
        return powerUpType;
    }

    public void setPowerUpType(PowerUpType powerUpType) {
        this.powerUpType = powerUpType;
    }


}
