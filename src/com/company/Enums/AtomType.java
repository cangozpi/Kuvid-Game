package com.company.Enums;

public enum AtomType implements IProjectileType {
    ALPHA,
    BETA,
    GAMMA,
    SIGMA;

    public static final String ALPHA_atom = "ALPHA_atom";
    public static final String BETA_atom = "BETA_atom";
    public static final String GAMMA_atom = "GAMMA_atom";
    public static final String SIGMA_atom= "SIGMA_atom";
    @Override
    public String toString() {
        return super.toString() + "_atom";
    }


}
