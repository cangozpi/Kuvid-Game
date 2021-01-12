package com.company.Domain.Models.Projectile.Decorator;

import com.company.Domain.Models.Projectile.Atom;
import com.company.Domain.Utility.Coordinate;
import com.company.Domain.Utility.Velocity;
import com.company.Enums.AtomType;
import com.company.Enums.ShieldType;

import java.util.HashMap;
import java.util.Random;

public class GammaDecorator extends AtomDecorator{

    private Random rand = new Random();
    private double stabilityConstant;
    private double protons;
    private double efficiency;
    private double neutrons;
    private HashMap<ShieldType,Integer> shieldMap;

    public GammaDecorator(Coordinate coordinate, Velocity velocity, AtomType atomType, boolean isAmmo, int height, int width, Atom atom) {
        super(coordinate, velocity, atomType, isAmmo, height, width, atom);

        stabilityConstant = 0.8;
        protons = 32;

        //randomly have neutrons in the range of 29, 32, 33
        int randNum = rand.nextInt(5);
        switch (randNum){
            case 0:
                neutrons = 29;
                break;
            case 1:
                neutrons = 32;
                break;
            default:
                neutrons = 33;
                break;
        }

        //Efficiency = Gamma stability constant +  ( | # of neutrons - # of protons | /  (2 * # of protons) )
        efficiency = stabilityConstant + ((neutrons - protons) / (2 * protons)) ;

    }
    @Override
    public HashMap<ShieldType,Integer> getShieldMap(){
        return shieldMap;
    }

    @Override
    public void addShield(ShieldType shieldType){

        //@REQUIRES: The argument shieldType must of type shieldType.

        //@MODIFIES: modifies the shield map of the atom and speed multiplier of the atom.

        /*@EFFECTS: atom speed is multiplied with the shield type's multiplier, in the atom's shield map corresponding
        type of shield's amount is incremented by 1.
         */

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
    public double getStabilityConstant() {
        return stabilityConstant;
    }

    @Override
    public double getEfficiency() {
        return efficiency;
    }

    @Override
    public double getProtons() {
        return protons;
    }

    @Override
    public double getNeutrons() {
        return neutrons;
    }
}
