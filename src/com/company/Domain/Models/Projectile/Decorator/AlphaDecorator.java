package com.company.Domain.Models.Projectile.Decorator;

import com.company.Domain.Models.Projectile.Atom;
import com.company.Domain.Models.Projectile.Projectile;
import com.company.Domain.Utility.Coordinate;
import com.company.Domain.Utility.Velocity;
import com.company.Enums.AtomType;

import java.util.Random;

public class AlphaDecorator extends AtomDecorator{

    private Random rand = new Random();
    private double stabilityConstant;
    private double protons;
    private double efficiency;
    private double neutrons;

    public AlphaDecorator(Coordinate coordinate, Velocity velocity, AtomType atomType, boolean isAmmo, int height, int width, Atom atom) {
        super(coordinate, velocity, atomType, isAmmo, height, width, atom);

        stabilityConstant = 0.85;
        protons = 8;

        //randomly have neutrons in the range of 7,8,9
        int randNum = rand.nextInt(3);
        switch (randNum){
            case 0:
                neutrons = 7;
                break;
            case 1:
                neutrons = 8;
                break;
            default://for case 2:
                neutrons = 9;
                break;
        }

        //Efficiency = (1 - (| # of neutrons - # of protons | /  # of protons) ) * Alpha stability constant
        efficiency = (1 - ((neutrons - protons) / protons) * stabilityConstant);

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
