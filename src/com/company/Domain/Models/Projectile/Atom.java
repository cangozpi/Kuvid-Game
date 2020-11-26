package com.company.Domain.Models.Projectile;

import com.company.Domain.Utility.Coordinate;
import com.company.Domain.Utility.Velocity;
import com.company.Enums.AtomType;

public class Atom extends Ammo{

    private AtomType atomType;

    public Atom(Coordinate coordinate, Velocity velocity, AtomType atomType) {
        super(coordinate, velocity);
        this.atomType = atomType;
    }


}
