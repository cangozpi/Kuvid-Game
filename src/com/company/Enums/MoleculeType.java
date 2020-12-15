package com.company.Enums;

public enum MoleculeType implements IProjectileType {
    ALPHA_1,
    BETA_1,
    ALPHA_2,
    BETA_2,
    GAMMA,
    SIGMA;
    public static final String ALPHA_1_molecule = "ALPHA_1_molecule";
    public static final String BETA_1_molecule = "BETA_1_molecule";
    public static final String ALPHA_2_molecule = "ALPHA_2_molecule";
    public static final String BETA_2_molecule = "BETA_2_molecule";
    public static final String GAMMA_molecule = "GAMMA_molecule";
    public static final String SIGMA_molecule= "SIGMA_molecule";
    @Override
    public String toString() {
        return super.toString() + "_molecule";
    }
}
