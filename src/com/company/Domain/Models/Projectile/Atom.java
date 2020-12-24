package com.company.Domain.Models.Projectile;

import com.company.Domain.Utility.Coordinate;
import com.company.Domain.Utility.Velocity;
import com.company.Enums.AtomType;
import com.company.Enums.IProjectileType;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class Atom extends Projectile{

    private IProjectileType projectileType;
    private AtomType atomType;
    private boolean isAmmo;

    public Atom(Coordinate coordinate, Velocity velocity, IProjectileType atomType, boolean isAmmo, int height, int width) {
        super(coordinate, velocity, isAmmo, height, width, atomType);
        this.projectileType = atomType;
        this.isAmmo = isAmmo;
        initializeAtomType(atomType);

    }

    public IProjectileType getProjectileType() { return atomType; }

    public void initializeAtomType(IProjectileType atomType){
        switch (atomType.toString()){
            case ("ALPHA_atom"):
                this.atomType = (AtomType.ALPHA);
                break;
            case ("BETA_atom"):
                this.atomType = (AtomType.BETA);
                break;
            case ("GAMMA_atom"):
                this.atomType = (AtomType.GAMMA);
                break;
            case ("SIGMA_atom"):
                this.atomType = (AtomType.SIGMA);
                break;
        }

    }
    public AtomType getAtomType(){ return this.atomType;}
    public void setAtomType(AtomType atomType) { this.atomType = atomType; }

    @Override
    public void move(){
        int angle = getVelocity().getAngle();
        double speed = getVelocity().getSpeed();
        setXCoordinate(getXCoordinate() - speed*cos(Math.toRadians(180-angle)));
        setYCoordinate(getYCoordinate() - speed*sin(Math.toRadians(angle)));

    }

}
