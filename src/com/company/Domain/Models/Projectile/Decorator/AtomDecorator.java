package com.company.Domain.Models.Projectile.Decorator;

import com.company.Domain.Models.Projectile.Atom;
import com.company.Domain.Models.Projectile.Projectile;
import com.company.Domain.Utility.Coordinate;
import com.company.Domain.Utility.Velocity;
import com.company.Enums.AtomType;

public abstract class AtomDecorator extends Atom {

    //reference to decorator's Component
    private Atom atomReference;

    public AtomDecorator(Coordinate coordinate, Velocity velocity, AtomType atomType, boolean isAmmo, int height, int width, Atom atom) {
        super(coordinate, velocity, atomType, isAmmo, height, width);
        this.atomReference = atom;
    }


    //methods to be implemented in concrete Decorator classes
    public abstract double getStabilityConstant();
    public abstract double getEfficiency();
    public abstract double getProtons();
    public abstract double getNeutrons();

    //methods to delegate to the component reference


    public void move() {
        atomReference.move();
    }

    //Atom class specific methods delegate to component reference
    public AtomType getAtomType() { return atomReference.getAtomType(); }

    public void setAtomType(AtomType atomType) { atomReference.setAtomType(atomType); }
}
