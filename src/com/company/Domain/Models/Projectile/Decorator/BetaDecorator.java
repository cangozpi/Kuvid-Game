package com.company.Domain.Models.Projectile.Decorator;

import com.company.Domain.Models.Projectile.Atom;
import com.company.Domain.Utility.Coordinate;
import com.company.Domain.Utility.Velocity;
import com.company.Enums.AtomType;
import com.company.Enums.ShieldType;

import java.util.HashMap;
import java.util.Random;

public class BetaDecorator extends AtomDecorator{

    private Random rand = new Random();
    private double stabilityConstant;
    private double protons;
    private double efficiency;
    private double neutrons;
    private HashMap<ShieldType,Integer> shieldMap;



    public BetaDecorator(Coordinate coordinate, Velocity velocity, AtomType atomType, boolean isAmmo, int height, int width, Atom atom) {
        super(coordinate, velocity, atomType, isAmmo, height, width, atom);

        stabilityConstant = 0.9;
        protons = 16;

        //randomly have neutrons in the range of 15, 16, 17, 18, 21
        int randNum = rand.nextInt(5);
        switch (randNum){
            case 0:
                neutrons = 15;
                break;
            case 1:
                neutrons = 16;
                break;
            case 2:
                neutrons = 17;
                break;
            case 3:
                neutrons = 18;
                break;
            default://for case 4:
                neutrons = 21;
                break;
        }

        //Efficiency = Beta stability constant - ( 0.5 * | # of neutrons - # of protons | /  # of protons)
        efficiency = stabilityConstant - ((0.5 * (neutrons - protons)) / protons) ;

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
                if (getProtons() != getNeutrons()){
                    this.efficiency *= (1 - this.efficiency) * Math.abs(getProtons() - getNeutrons()) / getProtons();
                }else{
                    this.efficiency *= (1 - this.efficiency)* 0.05;  //eta efficieny boost
                }

                break;
            case LOTA:
                newMultiplier = 0.93;
                this.efficiency *= (1 - this.efficiency)* 0.1;     //lota efficieny boost
                break;
            case THETA:
                newMultiplier = 0.91;
                this.efficiency *= (1 - this.efficiency) * (rand.nextInt(10) + 5 )/10*.0;

                break;
            case ZETA:
                newMultiplier = 0.89;
                if (getProtons() == getNeutrons()){
                    this.efficiency *= (1 - this.efficiency) * 0.2;
                }
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
