package com.company.Domain.Models;

import com.company.Domain.Models.Projectile.Atom;
import com.company.Domain.Utility.Coordinate;
import com.company.Domain.Utility.Velocity;
import com.company.Enums.AtomType;

public class AtomFactory {

    public AtomFactory(){

    }

    public Atom getInstance(Coordinate coordinate, Velocity velocity, AtomType atomType, boolean isAmmo, int height, int width){
        switch (atomType){
            case ALPHA:
                return new Atom(coordinate, velocity, AtomType.ALPHA, isAmmo, height, width);
            case BETA:
                return new Atom(coordinate, velocity, AtomType.BETA, isAmmo, height, width);
            case GAMMA:
                return new Atom(coordinate, velocity, AtomType.GAMMA, isAmmo, height, width);
            default:
                return new Atom(coordinate, velocity, AtomType.SIGMA, isAmmo, height, width);
        }
    }
}
