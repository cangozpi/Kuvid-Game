package com.company.Enums;

public enum ReactionBlockerType implements IProjectileType {
    ALPHA_B,
    BETA_B,
    GAMMA_B,
    SIGMA_B;
    public static final String ALPHA_B_blocker = "ALPHA_B_blocker";
    public static final String BETA_B_blocker = "BETA_B_blocker";
    public static final String GAMMA_B_blocker = "GAMMA_B_blocker";
    public static final String SIGMA_B_blocker= "SIGMA_B_blocker";
    @Override
    public String toString() {
        return super.toString() + "_blocker";
    }

}
