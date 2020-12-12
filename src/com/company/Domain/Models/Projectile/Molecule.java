package com.company.Domain.Models.Projectile;

import com.company.Domain.Utility.Coordinate;
import com.company.Domain.Utility.Velocity;
import com.company.Enums.MoleculeType;
import com.company.Enums.PowerUpType;

public class Molecule extends Projectile{
    MoleculeType moleculeType;

    public Molecule(Coordinate coordinate, Velocity velocity, boolean isAmmo, MoleculeType moleculeType, int height, int width) {
        super(coordinate, velocity, isAmmo, height, width);
        this.moleculeType = moleculeType;



    }
    public MoleculeType getMoleculeType(){
        return this.moleculeType;
    }

    @Override
    public void move() {
        super.move();
    }
}
