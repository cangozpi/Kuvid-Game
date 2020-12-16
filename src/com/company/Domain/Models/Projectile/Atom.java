package com.company.Domain.Models.Projectile;

import com.company.Domain.Utility.Coordinate;
import com.company.Domain.Utility.Velocity;
import com.company.Enums.AtomType;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class Atom extends Projectile{

    private AtomType atomType;
    private boolean isAmmo;

    public Atom(Coordinate coordinate, Velocity velocity, AtomType atomType, boolean isAmmo, int height, int width) {
        super(coordinate, velocity, isAmmo, height, width);
        this.atomType = atomType;
        this.isAmmo = isAmmo;
    }

    public AtomType getAtomType() { return atomType; }

    public void setAtomType(AtomType atomType) { this.atomType = atomType; }

    @Override
    public void move(){
        int angle = getVelocity().getAngle();
        double speed = getVelocity().getSpeed();
        setXCoordinate(getXCoordinate() - speed*cos(Math.toRadians(180-angle)));
        setYCoordinate(getYCoordinate() - speed*sin(Math.toRadians(angle)));

    }

}
