package com.company.Domain.Models.Projectile.Decorator;

import com.company.Domain.Models.Projectile.Atom;
import com.company.Domain.Utility.Coordinate;
import com.company.Domain.Utility.Velocity;
import com.company.Enums.AtomType;

import java.util.Random;

public class GammaDecorator extends AtomDecorator{

    private Random rand = new Random();
    private double stabilityConstant;
    private double protons;
    private double efficiency;
    private double neutrons;

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
