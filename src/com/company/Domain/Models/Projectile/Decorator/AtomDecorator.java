package com.company.Domain.Models.Projectile.Decorator;

import com.company.Domain.Models.Projectile.Atom;
import com.company.Domain.Models.Projectile.Projectile;
import com.company.Domain.Utility.Coordinate;
import com.company.Domain.Utility.Velocity;
import com.company.Enums.AtomType;
import com.company.Enums.IProjectileType;
import com.company.Enums.ShieldType;

import java.util.HashMap;

public abstract class AtomDecorator extends Atom {

    //reference to decorator's Component
    private Atom atomReference;

    public AtomDecorator(Coordinate coordinate, Velocity velocity, IProjectileType atomType, boolean isAmmo, int height, int width, Atom atom) {
        super(coordinate, velocity, atomType, isAmmo, height, width);
        this.atomReference = atom;
    }


    //methods to be implemented in concrete Decorator classes
    public abstract double getStabilityConstant();
    public abstract double getEfficiency();
    public abstract double getProtons();
    public abstract double getNeutrons();

    //methods to delegate to the component reference



    //Atom class specific methods delegate to component reference}
    public void addShield(ShieldType shieldType){ atomReference.addShield(shieldType); }

    public HashMap<ShieldType,Integer> getShieldMap(){
        return atomReference.getShieldMap();

    }

    public AtomType getAtomType() { return atomReference.getAtomType(); }

    public void setAtomType(AtomType atomType) { atomReference.setAtomType(atomType); }

    public int getWidth() {return atomReference.getWidth(); }

    public void setWidth(int width) { atomReference.setWidth(width); }

    public int getHeight() { return atomReference.getHeight(); }

    public void setHeight(int height) { atomReference.setHeight(height); }

    public void move(){ atomReference.move();}

    public boolean isAmmo() { return atomReference.isAmmo(); }

    public void setIsAmmo(boolean isAmmo) { atomReference.setIsAmmo(isAmmo); }

    public Coordinate getCoordinate() {
        return atomReference.getCoordinate();
    }

    public Velocity getVelocity() {
        return atomReference.getVelocity();
    }

    public void setCoordinate(Coordinate coordinate) {
        atomReference.setCoordinate(coordinate);
    }

    public double getXCoordinate(){ return atomReference.getXCoordinate();}

    public double getYCoordinate(){ return atomReference.getYCoordinate();}

    public void setXCoordinate(double xCoordinate){ atomReference.setXCoordinate(xCoordinate);}

    public void setYCoordinate(double yCoordinate){ atomReference.setYCoordinate(yCoordinate);}

    public void setVelocity(Velocity velocity) {
        atomReference.setVelocity(velocity);
    }
}
