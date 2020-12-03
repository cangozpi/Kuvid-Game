package com.company.Domain.Models.Projectile;

import com.company.Domain.Utility.Coordinate;
import com.company.Domain.Utility.Velocity;
import com.company.Enums.AtomType;

public class Atom extends Projectile{

    private AtomType atomType;
    private boolean isAmmo;

    public Atom(Coordinate coordinate, Velocity velocity, AtomType atomType, boolean isAmmo) {
        super(coordinate, velocity, isAmmo);
        this.atomType = atomType;
        this.isAmmo = isAmmo;
    }

    public AtomType getAtomType() { return atomType; }

    public void setAtomType(AtomType atomType) { this.atomType = atomType; }


}
