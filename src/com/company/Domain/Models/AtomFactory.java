package com.company.Domain.Models;

import com.company.Domain.Models.Projectile.Atom;
import com.company.Domain.Models.Projectile.Decorator.AlphaDecorator;
import com.company.Domain.Utility.Coordinate;
import com.company.Domain.Utility.Velocity;
import com.company.Enums.AtomType;

public class AtomFactory {

    public AtomFactory(){

    }

    public Atom getInstance(Coordinate coordinate, Velocity velocity, AtomType atomType, boolean isAmmo, int height, int width){
        Atom referenceAtom;
        switch (atomType){
            case ALPHA:
                referenceAtom = new Atom(coordinate, velocity, AtomType.ALPHA, isAmmo, height, width);
                return new AlphaDecorator(coordinate, velocity, AtomType.ALPHA, isAmmo, height, width, referenceAtom);
            case BETA:
                referenceAtom = new Atom(coordinate, velocity, AtomType.BETA, isAmmo, height, width);
                return new AlphaDecorator(coordinate, velocity, AtomType.BETA, isAmmo, height, width, referenceAtom);
            case GAMMA:
                referenceAtom = new Atom(coordinate, velocity, AtomType.GAMMA, isAmmo, height, width);
                return new AlphaDecorator(coordinate, velocity, AtomType.GAMMA, isAmmo, height, width, referenceAtom);
            default: //case SIGMA:
                referenceAtom = new Atom(coordinate, velocity, AtomType.SIGMA, isAmmo, height, width);
                return new AlphaDecorator(coordinate, velocity, AtomType.SIGMA, isAmmo, height, width, referenceAtom);
        }
    }
}
