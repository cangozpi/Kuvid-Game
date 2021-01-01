package com.company.Domain.Models;

import com.company.Domain.Models.Projectile.Atom;
import com.company.Domain.Models.Projectile.Decorator.AlphaDecorator;
import com.company.Domain.Models.Projectile.Decorator.BetaDecorator;
import com.company.Domain.Models.Projectile.Decorator.GammaDecorator;
import com.company.Domain.Models.Projectile.Decorator.SigmaDecorator;
import com.company.Domain.Utility.Coordinate;
import com.company.Domain.Utility.Velocity;
import com.company.Enums.AtomType;
import com.company.Enums.IProjectileType;

public class AtomFactory {

    public AtomFactory(){

    }

    public Atom getInstance(Coordinate coordinate, Velocity velocity, IProjectileType atomType, boolean isAmmo, int height, int width){
        Atom referenceAtom;
        switch (atomType.toString()){
            case AtomType.ALPHA_atom:
                referenceAtom = new Atom(coordinate, velocity, AtomType.ALPHA, isAmmo, height, width);
                return new AlphaDecorator(coordinate, velocity, AtomType.ALPHA, isAmmo, height, width, referenceAtom);
            case AtomType.BETA_atom:
                referenceAtom = new Atom(coordinate, velocity, AtomType.BETA, isAmmo, height, width);
                return new BetaDecorator(coordinate, velocity, AtomType.BETA, isAmmo, height, width, referenceAtom);
            case AtomType.GAMMA_atom:
                referenceAtom = new Atom(coordinate, velocity, AtomType.GAMMA, isAmmo, height, width);
                return new GammaDecorator(coordinate, velocity, AtomType.GAMMA, isAmmo, height, width, referenceAtom);
            default: //case SIGMA:
                referenceAtom = new Atom(coordinate, velocity, AtomType.SIGMA, isAmmo, height, width);
                return new SigmaDecorator(coordinate, velocity, AtomType.SIGMA, isAmmo, height, width, referenceAtom);
        }
    }
}
