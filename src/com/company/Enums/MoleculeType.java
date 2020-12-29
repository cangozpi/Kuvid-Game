package com.company.Enums;

public enum MoleculeType implements IProjectileType {
    ALPHA,
    BETA,
    ALPHA_L,
    BETA_L,
    GAMMA,
    SIGMA;
    public static final String ALPHA_molecule = "ALPHA_molecule";
    public static final String BETA_molecule = "BETA_molecule";
    public static final String ALPHA_L_molecule = "ALPHA_L_molecule";
    public static final String BETA_L_molecule = "BETA_L_molecule";
    public static final String GAMMA_molecule = "GAMMA_molecule";
    public static final String SIGMA_molecule= "SIGMA_molecule";
    @Override
    public String toString() {
        return super.toString() + "_molecule";
    }
}
