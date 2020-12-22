package com.company.Domain.Models.Projectile.Decorator;

import com.company.Domain.Models.Projectile.Atom;
import com.company.Domain.Utility.Coordinate;
import com.company.Domain.Utility.Velocity;
import com.company.Enums.AtomType;

import java.util.Random;

public class SigmaDecorator extends AtomDecorator{

    private Random rand = new Random();
    private double stabilityConstant;
    private double protons;
    private double efficiency;
    private double neutrons;

    public SigmaDecorator(Coordinate coordinate, Velocity velocity, AtomType atomType, boolean isAmmo, int height, int width, Atom atom) {
        super(coordinate, velocity, atomType, isAmmo, height, width, atom);

        stabilityConstant = 0.7;
        protons = 64;

        //randomly have neutrons in the range of 63, 64, 67
        int randNum = rand.nextInt(5);
        switch (randNum){
            case 0:
                neutrons = 63;
                break;
            case 1:
                neutrons = 64;
                break;
            default:
                neutrons = 67;
                break;
        }

        //Efficiency =  (1 + Sigma stability constant) / 2 +  ( | # of neutrons - # of protons | /  # of protons)
        efficiency = (1 + stabilityConstant) / 2 + ((neutrons - protons) / protons) ;

    }

    @Override
    public double getStabilityConstant() {
        return getStabilityConstant();
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
