package com.company.Enums;

public enum PowerUpType implements IProjectileType {
    ALPHA,
    BETA,
    GAMMA,
    SIGMA;
    public static final String ALPHA_powerUp = "ALPHA_powerUp";
    public static final String BETA_powerUp = "BETA_powerUp";
    public static final String GAMMA_powerUp = "GAMMA_powerUp";
    public static final String SIGMA_powerUp= "SIGMA_powerUp";
    @Override
    public String toString() {
        return super.toString() + "_powerUp";
    }
}
