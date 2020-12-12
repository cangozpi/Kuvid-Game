package com.company.Domain.Models;

import com.company.Domain.Models.Projectile.Molecule;
import com.company.Domain.Utility.Coordinate;
import com.company.Domain.Utility.Velocity;
import com.company.Enums.MoleculeType;

public class MoleculeFactory {
    public MoleculeFactory(){

    }

    public Molecule createMolecule(Coordinate coordinate, Velocity velocity, boolean isAmmo, MoleculeType moleculeType, int height, int width){
        return new Molecule(coordinate, velocity, isAmmo, moleculeType, height, width);
    }
}
