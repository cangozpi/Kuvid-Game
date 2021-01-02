package com.company.Domain.Models.Projectile;

import com.company.Domain.Utility.Coordinate;
import com.company.Domain.Utility.Velocity;
import com.company.Enums.AtomType;
import com.company.Enums.IProjectileType;
import com.company.Enums.ShieldType;

import java.util.HashMap;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class Atom extends Projectile{

    private IProjectileType projectileType;
    private AtomType atomType;
    private boolean isAmmo;
    private HashMap<ShieldType,Integer> shieldMap;
    private double speedMultiplier = 1;

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

    public HashMap<ShieldType,Integer> getShieldMap(){return shieldMap;}

    public double getSpeedMultiplier() {
        return speedMultiplier;
    }

    public void setSpeedMultiplier(double speedMultiplier) {
        this.speedMultiplier = speedMultiplier;
    }

    public void addShield(ShieldType shieldType){
        if(shieldMap == null){
            shieldMap = new HashMap<>();
            shieldMap.put(ShieldType.ETA,0);
            shieldMap.put(ShieldType.LOTA,0);
            shieldMap.put(ShieldType.THETA,0);
            shieldMap.put(ShieldType.ZETA,0);
        }

        shieldMap.replace(shieldType,shieldMap.get(shieldType)+1);

        double newMultiplier = 1;

        switch (shieldType){

            case ETA:
                newMultiplier = 0.95;
                break;
            case LOTA:
                newMultiplier = 0.93;
                break;
            case THETA:
                newMultiplier = 0.91;
                break;
            case ZETA:
                newMultiplier = 0.89;
                break;
        }
        setSpeedMultiplier(getSpeedMultiplier() * newMultiplier);
    }

    @Override
    public void move(){
        int angle = getVelocity().getAngle();
        double speed = getVelocity().getSpeed();
        setXCoordinate(getXCoordinate() - speed*cos(Math.toRadians(180-angle)));
        setYCoordinate(getYCoordinate() - speed*sin(Math.toRadians(angle)));

    }

}
