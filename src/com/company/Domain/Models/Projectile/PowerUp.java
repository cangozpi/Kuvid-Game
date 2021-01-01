package com.company.Domain.Models.Projectile;

import com.company.Domain.Models.GameFactory;
import com.company.Domain.Utility.Coordinate;
import com.company.Domain.Utility.Velocity;
import com.company.Enums.AtomType;
import com.company.Enums.IProjectileType;
import com.company.Enums.PowerUpType;

public class PowerUp extends Projectile{
    private IProjectileType projectileType;
    private PowerUpType powerUpType;

    public PowerUp(Coordinate coordinate, Velocity velocity, IProjectileType powerUpType, boolean isAmmo, int height, int width) {
        super(coordinate, velocity, isAmmo, height, width, powerUpType);
        this.projectileType = powerUpType;
        initializePowerUpType(powerUpType);
    }

    //getters and setters
    public IProjectileType getProjectileType() {
        return powerUpType;
    }

    public void initializePowerUpType(IProjectileType projectileType){
        switch (projectileType.toString()){
            case ("ALPHA_powerUp"):
                setPowerUpType(PowerUpType.ALPHA);
                break;
            case ("BETA_powerUp"):
                setPowerUpType(PowerUpType.BETA);
                break;
            case ("GAMMA_powerUp"):
                setPowerUpType(PowerUpType.GAMMA);
                break;
            case ("SIGMA_powerUp"):
                setPowerUpType(PowerUpType.SIGMA);
                break;
        }

    }

    public void setPowerUpType(PowerUpType powerUpType) {
        this.powerUpType = powerUpType;
    }

    @Override
    public void move() {
        setCoordinate(new Coordinate(getXCoordinate(),getYCoordinate()+ GameFactory.getInstance().getFallSpeed()));
    }

}
